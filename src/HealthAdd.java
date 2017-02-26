import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 用于给坦克加血的血块
 * @author zhushoucheng
 *
 */
public class HealthAdd
{
	/**
	 * 血块的x轴位置
	 */
	private int x;
	/**
	 * 血块的y轴位置
	 */
	private int y;
	TankClient tc;
	
	/**
	 * 血块的存活与否
	 */
	boolean live = true;
	int step = 0;
	/**
	 * 血块移动的位置
	 */
	int [][] pos = {
			{200,200},{400,300},{500,200}
	};
	
	public HealthAdd(TankClient tc)
	{
		x = pos[0][0];
		y = pos[0][1];
		this.tc = tc;
	}
	
	public void draw(Graphics graphics)
	{
		if (!live) return;
		Color color = graphics.getColor();
		graphics.setColor(Color.magenta);
		graphics.fillRect(x, y, 10, 10);
		graphics.setColor(color);
		
		Random random = new Random();
		if (random.nextInt(30) > 28)
			move();
	}
	
	/**
	 * 血块的移动
	 */
	public void move()
	{
		step++;
		if (step == pos.length)
			step = 0;
		x = pos[step][0];
		y = pos[step][1];
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(x, y, 10, 10);
	}
}
