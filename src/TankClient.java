import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import  java.awt.Image;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 本类是坦克大战的主体程序，内部调用了各个类的方法，实现了界面的显示
 * @author zhushoucheng
 *
 */
public class TankClient extends JFrame
{
	/**
	 * 整体游戏高度
	 */
	public static final int GAME_WIDTH = 800;
	/**
	 * 整体游戏宽度
	 */
	public static final int GAME_HEIGHT = 600;
	Image OffScreenImage = null;
	Tank myTank = new Tank(50, 50,true,Dir.STOP,this);
	private JPanel contentPane;
	Well w1 = new Well(300, 100, 200, 50);
	Well w2 = new Well(100, 200, 50, 200);
	HealthAdd ha = new HealthAdd(this);
	
	Vector<Missile> m = new Vector<>();
	Vector<Explode> e = new Vector<>();
	Vector<Tank> enemyTanks = new Vector<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					TankClient frame = new TankClient("TankWar");
					frame.start();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 界面的构造方法
	 * @param s 界面的标题名
	 */
	public TankClient(String s)
	{
		super(s);
		addKeyListener(new KeyAdapter() {				//键盘事件要添加在JFrame才可以
			@Override
			public void keyPressed(KeyEvent arg0) {
				myTank.keyAction(arg0);
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				myTank.keyReset(arg0);
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, GAME_WIDTH, GAME_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	/**
	 * 用于敌方坦克的添加 与线程的开始
	 */
	public void start()
	{
		int initTankCount = Property.getProperty("initTankcount");
		for (int i = 0; i < initTankCount; i++)
		{
			enemyTanks.add(new Tank(50, 100 + i * 50,false,Dir.D,this));
		}
		setVisible(true);
		
		new Thread(new RePaint()).start();
	}
	/**
	 * 依次画出左上角的提示信息
	 * 		血块
	 * 		玩家坦克
	 * 		敌方坦克
	 * 		子弹
	 * 		爆炸
	 * @param g
	 */
	public void paint(Graphics g)
	{
		super.paint(g);           //不加这句不会清除以前的图像
		if (enemyTanks.size() == 0)
		{
			g.drawString("WIN!!!",400, 300);
			return;
		}
		g.drawString("tank count : " + enemyTanks.size() ,10, 70);
		
		ha.draw(g);
		myTank.eat(ha);
		myTank.draw(g);
		
		
		g.drawString("number : " + m.size(), 10, 50);		//设置位置太小会被上面的框挡住
		
		g.drawString("tank life: " + myTank.getLife(), 10, 90);
		
		
		for (int k = 0; k < enemyTanks.size(); k++)
		{
			Tank enemyTank = enemyTanks.get(k);
			enemyTank.hitWell(w1);
			enemyTank.hitWell(w2);
			enemyTank.hitOtherTanks(enemyTanks);
			enemyTank.draw(g);
		}
		for (int i = 0; i < m.size(); i++)
		{
			Missile missile = m.get(i);
			missile.hitTanks(enemyTanks);
			missile.hitTank(myTank);
			missile.hitWell(w1);
			missile.hitWell(w2);
			missile.draw(g, m);
		}
		
		for (int j = 0; j < e.size(); j++)
		{
			Explode explode = e.get(j);
			explode.draw(g);
		}
		
		w1.draw(g);
		w2.draw(g);
	}
	
	public void update(Graphics g)		//双缓存技术
	{
		if (OffScreenImage == null)
			OffScreenImage = createImage(GAME_WIDTH, GAME_HEIGHT);
		Graphics gImage = OffScreenImage.getGraphics();
		paint(gImage);
		g.drawImage(OffScreenImage, 0, 0, null);
	}
	
	
	class RePaint implements Runnable
	{

		@Override
		public void run()
		{
			while (true)
			{
				try
				{
					Thread.sleep(100);
					repaint();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
}
