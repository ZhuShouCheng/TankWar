import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;



public class Missile
{
	private int x;
	private int y;
	private static int Xmove = 10;
	private static int Ymove = 10;
	
	public static int WIDTH = 10;
	public static int HEIGHT = 10;
	
	private Dir direction;
	private TankClient tc;
	private boolean good;
	boolean live = true;
	
	
	public Missile(int x,int y,Dir d,boolean good)
	{
		this.x = x;
		this.y = y;
		this.good = good;
		this.direction = d;
	}
	
	public Missile(int x,int y,Dir d,boolean good,TankClient tc)
	{
		this(x,y,d,good);
		this.tc = tc;
	}
	public void draw(Graphics g,Vector<Missile> m)
	{
		if (!live) 
		{
			m.remove(this);
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		move();
	}
	
	public void move()
	{
		switch (direction)
		{
			case W:
				y -= Ymove;
				break;
			case S:
				y += Ymove;
				break;
			case A:
				x -= Xmove;
				break;
			case D:
				x += Xmove;
				break;
			case WD:
				x += Xmove;
				y -= Ymove;
				break;
			case WA:
				x -= Xmove;
				y -= Ymove;
				break;
			case SD:
				x += Xmove;
				y += Ymove;
				break;
			case SA:
				x -= Xmove;
				y += Ymove;
				break;
			case STOP:
				break;
		}
		
		if (this.x > 800 || this.y > 600 || this.x < 0 || this.y < 0)
			live = false;
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(x,y,WIDTH,HEIGHT);	
	}
	
	public void hitTank(Tank tank)
	{
		if (this.live && this.getRect().intersects(tank.getRect()) 
				&& tank.live && this.good != tank.isGood())
		{
			tank.live = false;
			live = false;
			Explode explode = new Explode(x,y,tc);
			tc.e.add(explode);
		}
	}
	
	public void hitTanks(Vector<Tank> tanks)
	{
		for (int i = 0; i < tanks.size(); i ++)
		{
			hitTank(tanks.get(i));
		}
	}
	
}
