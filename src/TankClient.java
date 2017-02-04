import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import  java.awt.Image;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends JFrame
{
	int x = 50, y = 50;
	Image OffScreenImage = null;
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		
		y += 5;
	}
	
	public void update(Graphics g)		//双缓存技术
	{
		if (OffScreenImage == null)
			OffScreenImage = createImage(800, 600);
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
