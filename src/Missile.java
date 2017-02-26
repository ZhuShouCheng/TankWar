import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 炮弹类
 * @author zhushoucheng
 *
 */
public class Missile
{
	/**
	 * 炮弹的x轴位置
	 */
	private int x;
	/**
	 * 炮弹的y轴位置
	 */
	private int y;
	/**
	 * 炮弹的x轴移动距离
	 */
	private static int Xmove = 10;
	/**
	 * 炮弹的y轴移动距离
	 */
	private static int Ymove = 10;
	/**
	 * 炮弹的宽度
	 */
	public static int WIDTH = 10;
	/**
	 * 炮弹的高度
	 */
	public static int HEIGHT = 10;
	
	/**
	 * 炮弹的方向
	 */
	private Dir direction;
	private TankClient tc;
	/**
	 * 炮弹的好坏
	 */
	private boolean good;
	boolean live = true;
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Image[] missileImage = null;
	private static Map<String, Image> imgs = new HashMap<>();
	
	static{
		missileImage = new Image[]
		{
			toolkit.getImage(Missile.class.getClassLoader().getResource("imgs/missileD.gif")),
			toolkit.getImage(Missile.class.getClassLoader().getResource("imgs/missileL.gif")),
			toolkit.getImage(Missile.class.getClassLoader().getResource("imgs/missileLD.gif")),
			toolkit.getImage(Missile.class.getClassLoader().getResource("imgs/missileLU.gif")),
			toolkit.getImage(Missile.class.getClassLoader().getResource("imgs/missileR.gif")),
			toolkit.getImage(Missile.class.getClassLoader().getResource("imgs/missileRD.gif")),
			toolkit.getImage(Missile.class.getClassLoader().getResource("imgs/missileRU.gif")),
			toolkit.getImage(Missile.class.getClassLoader().getResource("imgs/missileU.gif"))
		};
		imgs.put("U",missileImage[0]);
		imgs.put("L",missileImage[1]);
		imgs.put("LD",missileImage[2]);
		imgs.put("LU",missileImage[3]);
		imgs.put("R",missileImage[4]);
		imgs.put("RD",missileImage[5]);
		imgs.put("RU",missileImage[6]);
		imgs.put("D",missileImage[7]);
	}
	
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
		switch (direction)
		{
			case W:
				g.drawImage(imgs.get("U"),x,y,null);
				break;
			case S:
				g.drawImage(imgs.get("D"),x,y,null);
				break;
			case A:
				g.drawImage(imgs.get("L"),x,y,null);
				break;
			case D:
				g.drawImage(imgs.get("R"),x,y,null);
				break;
			case WD:
				g.drawImage(imgs.get("RU"),x,y,null);
				break;
			case WA:
				g.drawImage(imgs.get("LU"),x,y,null);
				break;
			case SD:
				g.drawImage(imgs.get("RD"),x,y,null);
				break;
			case SA:
				g.drawImage(imgs.get("LD"),x,y,null);
				break;
			case STOP:
				break;
		}
		move();
	}
	/**
	 * 炮弹的移动 根据方向 进行一定距离的移动
	 */
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
	
	/**
	 * 击中坦克
	 * @param tank
	 */
	public void hitTank(Tank tank)
	{
		if (this.live && this.getRect().intersects(tank.getRect()) 
				&& tank.live && this.good != tank.isGood())
		{
			if (tank.isGood())
			{
				tank.setLife(tank.getLife() - 20);
				if (tank.getLife() <= 0)
					tank.live = false;
			}
			else {
				tank.live = false;
			}
			live = false;
			Explode explode = new Explode(x,y,tc);
			tc.e.add(explode);
			
		}
		
	}
	/**
	 * 对所以坦克进行击中判定
	 * @param tanks
	 */
	public void hitTanks(Vector<Tank> tanks)
	{
		for (int i = 0; i < tanks.size(); i ++)
		{
			hitTank(tanks.get(i));
		}
	}
	/**
	 * 炮弹撞墙
	 * @param w 墙
	 */
	public void hitWell(Well w)
	{
		if (this.live && this.getRect().intersects(w.getRect()))
		{
			this.live = false;
		}
	}
	
}
