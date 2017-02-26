import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.VolatileImage;

/**
 * 炮弹击中坦克的爆炸
 * @author zhushoucheng
 *
 */
public class Explode
{
	/**
	 * 爆炸的x轴位置
	 */
	private int x;
	/**
	 * 爆炸的y轴位置
	 */
	private int y;
	
	private TankClient tc;
	/**
	 * 爆炸的存活与否
	 */
	boolean live = true;
	static boolean init = false;
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit(); 
	
	Image[] imgs = {
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/0.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/1.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/2.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/3.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/4.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/5.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/6.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/7.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/8.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/9.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("imgs/10.gif"))	
			};
	int step = 0;
	
	public Explode(int x,int y,TankClient tc)
	{
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g)
	{
		
		if (!init)
		{
			for (int j = 0; j < imgs.length; j++)
			{
				g.drawImage(imgs[j], -100, -100, null);
			}
		}
		if (!live)
		{
			tc.e.remove(this);
			return;
		}
		if (step == imgs.length)
		{
			live = false;
			return;
		}
		g.drawImage(imgs[step], x, y, null);
		
		step++;
	}
}
