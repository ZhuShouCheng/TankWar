import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import com.sun.javafx.geom.AreaOp.AddOp;
import com.sun.swing.internal.plaf.metal.resources.metal;

public class Missile
{
	private int x;
	private int y;
	private static int Xmove = 10;
	private static int Ymove = 10;
	
	private Dir direction;
	
	public Missile(int x,int y,Dir d)
	{
		this.x = x;
		this.y = y;
		this.direction = d;
	}
	
	public void draw(Graphics g)
	{
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillOval(x, y, 10, 10);
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
				x += Xmove;
				break;
		}
	}
	
	public static void keyAction(KeyEvent arg0,Tank t,Vector<Missile> m)
	{
		int key = arg0.getKeyCode();
		if (KeyEvent.VK_J == key)
		{
			m.add(new Missile(
					t.getX()+ 10,t.getY() + 10,t.getDir()));
		}
	}
}
