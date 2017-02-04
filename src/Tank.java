import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Tank
{
	private int x;
	private int y;
	
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
	}
	
	public void keyAction(KeyEvent arg0)
	{
		int key = arg0.getKeyCode();
		if (key == KeyEvent.VK_W)
		{
			y -= 5;
		}
		else if (key == KeyEvent.VK_S)
		{
			y += 5;
		}
		else if (key == KeyEvent.VK_D)
		{
			x += 5;
		}
		else if (key == KeyEvent.VK_A)
		{
			x -= 5;
		}
	}
	
}
