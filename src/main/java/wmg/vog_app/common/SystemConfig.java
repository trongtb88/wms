package wmg.vog_app.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Reading config properties file from resources folder
 * @author tran-binh-trong
 *
 */
public class SystemConfig {

	final static Logger logger = Logger.getLogger(SystemConfig.class);

	private static Properties prop;

	public static Properties getProperties() {
		if (prop == null) {
			prop = loadConfig();
		}
		return prop;
	}

	private SystemConfig() {
		prop = null;
	}

	private static Properties loadConfig() {

		InputStream input = null;
		String filename = "/config.properties";
		try {
			prop = new Properties();
			input = SystemConfig.class.getResourceAsStream(filename);
			if (input == null) {
				logger.info("Sorry, unable to find " + filename);
			}
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Sorry, unable to find " + filename, ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

}
