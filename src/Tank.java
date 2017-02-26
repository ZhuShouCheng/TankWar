import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

enum Dir{
	W,D,S,A,WD,SD,SA,WA,STOP
};

/**
 * 坦克类
 * @author zhushoucheng
 *
 */
public class Tank
{
	/**
	 *  x方向上移动距离
	 */
	private final int Xmove = 5;
	/**
	 *  y方向上移动距离
	 */
	private final int Ymove = 5;
	/**
	 * 坦克宽度
	 */
	static final int WIDTH = 30;
	/**
	 * 坦克高度
	 */
	static final int HEIGHT = 30;
	static Random random = new Random();
	
	/**
	 * 坦克的x轴位置
	 */
	private int x;
	/**
	 * 坦克的y轴位置
	 */
	private int y;
	private int oldX;
	private int oldY;
	/**
	 * 坦克生命值
	 */
	private int life = 100;
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Image[] TankImages = null;
	private static Map<String, Image> imgs = new HashMap<>();
	
	static {
		TankImages = new Image[]
		{
			toolkit.getImage(Tank.class.getClassLoader().getResource("imgs/tankD.gif")),
			toolkit.getImage(Tank.class.getClassLoader().getResource("imgs/tankL.gif")),
			toolkit.getImage(Tank.class.getClassLoader().getResource("imgs/tankLD.gif")),
			toolkit.getImage(Tank.class.getClassLoader().getResource("imgs/tankLU.gif")),
			toolkit.getImage(Tank.class.getClassLoader().getResource("imgs/tankR.gif")),
			toolkit.getImage(Tank.class.getClassLoader().getResource("imgs/tankRD.gif")),
			toolkit.getImage(Tank.class.getClassLoader().getResource("imgs/tankRU.gif")),
			toolkit.getImage(Tank.class.getClassLoader().getResource("imgs/tankU.gif"))
		};
		imgs.put("D",TankImages[0]);
		imgs.put("L",TankImages[1]);
		imgs.put("LD",TankImages[2]);
		imgs.put("LU",TankImages[3]);
		imgs.put("R",TankImages[4]);
		imgs.put("RD",TankImages[5]);
		imgs.put("RU",TankImages[6]);
		imgs.put("U",TankImages[7]);
	}
	
	private HealthPoint HP = new HealthPoint();
	
	public int getLife()
	{
		return life;
	}

	public void setLife(int life)
	{
		this.life = life;
	}

	private Dir dir = Dir.STOP;
	/**
	 * 坦克的好坏
	 */
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
		this.oldX = x;
		this.oldY = y;
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
			else 
			{
				this.live = false;
			}
		}
		if (good) HP.draw(g);
		Color c = g.getColor();
/*		if (good)
		g.setColor(Color.red);
		else {
			g.setColor(Color.green);
		}
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);*/
		
		switch (ptDir)
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
	 * 坦克的移动，一共有8个方向可供移动
	 */
	public void move()
	{
		oldX = x;
		oldY = y;
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
				tc.m.add(fire());;
		}
	}
	/**
	 * 坦克方向的判定
	 */
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
	/**
	 * 键盘按下时事件
	 * @param arg0
	 */
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
	/**
	 * 键盘松开时事件
	 * @param arg0
	 */
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
		else if (key == KeyEvent.VK_F2)
		{
			this.live = true;
		}
		
		locationDirection();
	}
	/**
	 * 坦克开火 
	 * @return 返回一个与炮筒方向一致的炮弹
	 */
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
	
	public void stay()
	{
		x = oldX;
		y = oldY;
	}
	/**
	 * 坦克装上墙 碰撞判定
	 * @param w
	 */
	public void hitWell(Well w)
	{
		if (this.live && this.getRect().intersects(w.getRect()))
		{
			stay();
		}
	}
	/**
	 * 坦克撞上其他坦克
	 * @param tanks 敌方坦克的集合
	 */
	public void hitOtherTanks(Vector<Tank> tanks)
	{
		for (int i = 0; i < tanks.size(); i++)
		{
			Tank tank = tanks.get(i);
			if (this != tank)
			{
				if (this.live && tank.live && this.getRect().intersects(tank.getRect()))
				{
					this.stay();
					tank.stay();
				}
			}
		}
	}
	/**
	 * 坦克吃到血块   碰撞判定
	 * @param ha
	 */
	public void eat(HealthAdd ha)
	{
		if (this.live && this.getRect().intersects(ha.getRect()) && ha.live)
		{
			life = 100;
			ha.live = false;
		}
	}
	/**
	 * 血条的显示
	 * @author zhushoucheng
	 *
	 */
	public class HealthPoint
	{
		public void draw(Graphics graphics)
		{
			Color c = graphics.getColor();
			graphics.setColor(Color.red);
			graphics.drawRect(x, y - 15, 30, 10);
			graphics.fillRect(x, y - 15, life / 10 *3, 10);
			graphics.setColor(c);
		}
	}
/*	public void Show()
	{
		System.out.println(dir);
	}*/
	
}
