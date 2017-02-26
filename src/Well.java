import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 障碍物墙
 * @author zhushoucheng
 *
 */
public class Well
{
	/**
	 * 墙的x轴位置
	 */
	private int x;
	/**
	 * 墙的y轴位置
	 */
	private int y;
	/**
	 * 墙的宽度
	 */
	private int width;
	/**
	 * 墙的高度
	 */
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
