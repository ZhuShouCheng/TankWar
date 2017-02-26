import java.io.IOException;
import java.util.Properties;

public class Property
{
	static Properties props = new Properties();
	static{
		try
		{
			props.load(Property.class.getClassLoader().getResourceAsStream("config/tankproperties"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static int getProperty(String key)
	{
		return Integer.parseInt(props.getProperty(key));
	}
}
