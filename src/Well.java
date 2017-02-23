import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Well
{
	private int x;
	private int y;
	private int width;
	private int height;
	private TankClient tc;
	public Well(int x,int y,int width,int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Well(int x,int y,int width,int height,TankClient tc)
	{
		this(x, y,width,height);
		this.tc = tc;
	}
	
	public void draw(Graphics graphics)
	{
		Color color = graphics.getColor();
		graphics.setColor(Color.lightGray);
		graphics.fill3DRect(x, y, width, height,true);
		graphics.setColor(color);
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(x, y, width, height);
	}
}
