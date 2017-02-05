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
	
	private int x;
	private int y;
	private Dir dir = Dir.STOP;
	
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
	
/*	public void Show()
	{
		System.out.println(dir);
	}*/
	
}
