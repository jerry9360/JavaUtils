package redis;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 配置文件�?
 * @author mrpod2g
 *
 */
public class ConfigUtil {
	
	private static PropertiesConfiguration config = null;
	
	static 
	{
			try {
				config = new PropertiesConfiguration("conf/config.properties");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static String getValue(String key) {
		return config.getString(key,"unknown");
	}
    	
	public static void setValue(String key, Object value) {
		config.setProperty(key, value);
	}
    
	public static void save(){
		try {
			config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getValue("start_hour"));
	}
}