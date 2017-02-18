import java.awt.Color;
import java.awt.Graphics;

public class Explode
{
	private int x;
	private int y;
	private TankClient tc;
	boolean live = true;
	
	int[] diameter = {2,4,7,13,20,10,6,1};
	int step = 0;
	
	public Explode(int x,int y,TankClient tc)
	{
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g)
	{
		if (!live)
		{
			tc.e.remove(this);
			return;
		}
		if (step == diameter.length)
		{
			live = false;
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.orange);
		g.fillOval(x, y,diameter[step], diameter[step]);
		g.setColor(c);
		
		step++;
	}
}
