import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import  java.awt.Image;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankClient extends JFrame
{
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	Image OffScreenImage = null;
	Tank myTank = new Tank(50, 50);
	private JPanel contentPane;

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
	 * Create the frame.
	 */
	public TankClient(String s)
	{
		super(s);
		addKeyListener(new KeyAdapter() {				//键盘事件要添加在JFrame才可以
			@Override
			public void keyPressed(KeyEvent arg0) {
				myTank.keyAction(arg0);
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

	public void start()
	{
		setVisible(true);
		
		new Thread(new RePaint()).start();
	}
	public void paint(Graphics g)
	{
		super.paint(g);           //不加这句不会清除以前的图像
		myTank.draw(g);
		
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
