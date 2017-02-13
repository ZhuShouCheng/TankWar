import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

enum Dir{
	W,D,S,A,WD,SD,SA,WA,STOP
};


public class Tank
{
	private final int Xmove = 5;
	private final int Ymove = 5;
	
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	
	private int x;
	private int y;
	private Dir dir = Dir.STOP;
	Dir ptdir = Dir.D;
	
	boolean BW = false,BD = false,BS = false,BA =false;
	public Tank(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g)
	{
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);

		switch (ptdir)
		{
			case W:
				g.drawLine(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, this.x + Tank.WIDTH/2, this.y);
				break;
			case S:
				g.drawLine(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT);
				break;
			case A:
				g.drawLine(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, this.x, this.y + Tank.HEIGHT/2);
				break;
			case D:
				g.drawLine(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, this.x + Tank.WIDTH, this.y + Tank.HEIGHT/2);
				break;
			case WD:
				g.drawLine(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, this.x + Tank.WIDTH, this.y);
				break;
			case WA:
				g.drawLine(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, this.x, this.y);
				break;
			case SD:
				g.drawLine(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, this.x + Tank.WIDTH, this.y + Tank.HEIGHT);
				break;
			case SA:
				g.drawLine(this.x + Tank.WIDTH/2, this.y + Tank.HEIGHT/2, this.x, this.y + Tank.HEIGHT);
				break;
		}
		move();
	}
	public void move()
	{
		switch (dir)
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
		
		if (dir != Dir.STOP)
		{
			ptdir = dir;
		}
	}
	
	public void locationDirection()
	{
		if (BW && !BA && !BS && !BD)
			dir = Dir.W;
		else if (!BW && !BA && !BS && BD)
			dir = Dir.D;
		else if (!BW && !BA && BS && !BD)
			dir = Dir.S;
		else if (!BW && BA && !BS && !BD)
			dir = Dir.A;
		else if (BW && !BA && !BS && BD)
			dir = Dir.WD;
		else if (BW && BA && !BS && !BD)
			dir = Dir.WA;
		else if (!BW && !BA && BS && BD)
			dir = Dir.SD;
		else if (!BW && BA && BS && !BD)
			dir = Dir.SA;
		else {
			dir = Dir.STOP;
		}
	}
	
	public void keyAction(KeyEvent arg0)
	{
		int key = arg0.getKeyCode();
		if (key == KeyEvent.VK_W)
		{
			BW = true;

		}
		else if (key == KeyEvent.VK_S)
		{
			BS = true;

		}
		else if (key == KeyEvent.VK_D)
		{
			BD = true;

		}
		else if (key == KeyEvent.VK_A)
		{

			BA = true;
		}
		
		locationDirection();
	}
	
	public void keyReset(KeyEvent arg0)
	{
		int key = arg0.getKeyCode();
		if (key == KeyEvent.VK_W)
		{
			BW = false;

		}
		else if (key == KeyEvent.VK_S)
		{
			BS = false;

		}
		else if (key == KeyEvent.VK_D)
		{
			BD = false;

		}
		else if (key == KeyEvent.VK_A)
		{

			BA = false;
		}
		
		locationDirection();
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public Dir getDir()
	{
		return dir;
	}

	public void setDir(Dir dir)
	{
		this.dir = dir;
	}
	
/*	public void Show()
	{
		System.out.println(dir);
	}*/
	
}
