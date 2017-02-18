import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

enum Dir{
	W,D,S,A,WD,SD,SA,WA,STOP
};


public class Tank
{
	private final int Xmove = 5;
	private final int Ymove = 5;
	static final int WIDTH = 30;
	static final int HEIGHT = 30;
	static Random random = new Random();
	
	private int x;
	private int y;
	private Dir dir = Dir.STOP;
	private boolean good;
	public boolean isGood()
	{
		return good;
	}

	TankClient tc = null;
	Dir ptDir = Dir.D;
	private int step = random.nextInt(12) + 3;
	
	boolean live = true;
	boolean BW = false,BD = false,BS = false,BA = false;
	public Tank(int x,int y,boolean good)
	{
		this.x = x;
		this.y = y;
		this.good = good;
	}
	
	public Tank(int x,int y,boolean good,Dir dir,TankClient tc)
	{
		this(x, y,good);
		this.dir = dir;
		this.tc = tc;
	}
	
	public void draw(Graphics g)
	{
		if (!live) 
		{
			if (!good)
				tc.enemyTanks.remove(this);
		}
		Color c = g.getColor();
		if (good)
		g.setColor(Color.red);
		else {
			g.setColor(Color.green);
		}
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		switch (ptDir)
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
			case STOP:
				break;
		}
//		locationDirection();
//		Show();
		move();
	}
	public void move()
	{
		switch (dir)
		{
			case W:
				y -= Ymove;
				if (x < 0 || x > TankClient.GAME_WIDTH - 50 || y > TankClient.GAME_HEIGHT - 50 || y < 0)
				{
					y += Ymove;
				}
				break;
			case S:
				y += Ymove;
				if (x < 0 || x > TankClient.GAME_WIDTH - 50 || y > TankClient.GAME_HEIGHT - 50 || y < 0)
				{
					y -= Ymove;
				}
				break;
			case A:
				x -= Xmove;
				if (x < 0 || x > TankClient.GAME_WIDTH - 50 || y > TankClient.GAME_HEIGHT - 50  || y < 0)
				{
					x += Xmove;
				}
				break;
			case D:
				x += Xmove;
				if (x < 0 || x > TankClient.GAME_WIDTH - 50  || y > TankClient.GAME_HEIGHT - 50  || y < 0)
				{
					x -= Xmove;
				}
				break;
			case WD:
				x += Xmove;
				y -= Ymove;
				if (x < 0 || x > TankClient.GAME_WIDTH - 50  || y > TankClient.GAME_HEIGHT - 50  || y < 0)
				{
					x -= Xmove;
					y += Ymove;
				}
				break;
			case WA:
				x -= Xmove;
				y -= Ymove;
				if (x < 0 || x > TankClient.GAME_WIDTH - 50  || y > TankClient.GAME_HEIGHT - 50  || y < 0)
				{
					x += Xmove;
					y += Ymove;
				}
				break;
			case SD:
				x += Xmove;
				y += Ymove;
				if (x < 0 || x > TankClient.GAME_WIDTH - 50  || y > TankClient.GAME_HEIGHT - 50  || y < 0)
				{
					x -= Xmove;
					y -= Ymove;
				}
				break;
			case SA:
				x -= Xmove;
				y += Ymove;
				if (x < 0 || x > TankClient.GAME_WIDTH - 50  || y > TankClient.GAME_HEIGHT - 50  || y < 0)
				{
					x += Xmove;
					y -= Ymove;
				}
				break;
			case STOP:
				break;
		}
		
		if (dir != Dir.STOP)
		{
			ptDir = dir;
		}
		
		if (!good)
		{
			Dir[] d = Dir.values();
			
			if (step == 0)
			{
				step = random.nextInt(12) + 3;
				int r = random.nextInt(8);
				dir = d[r];
			}
			step--;
			
			if (random.nextInt(40) > 38)
				this.fire();
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
		else if (key == KeyEvent.VK_J)
		{
			tc.m.add(fire());
		}
		
		locationDirection();
	}
	
	public Missile fire()
	{
		if (!live) return null;
		
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		return new Missile(x, y, ptDir,good,tc);
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(x,y,WIDTH,HEIGHT);	
	}
	
/*	public void Show()
	{
		System.out.println(dir);
	}*/
	
}
