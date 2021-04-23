package mailer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesValue {

	String result = "";
	InputStream inputStream;
	
	public String getEmailHost() throws IOException {
		String email = "";
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			email = prop.getProperty("email_host");
		} catch (Exception e) {
			System.out.println("Exception while reading the email host");
		} finally {
			inputStream.close();
		}
		return email;
	}
	
	public String getPasswordHost() throws IOException {
		String password = "";
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			password = prop.getProperty("email_password");
		} catch (Exception e) {
			System.out.println("Exception while reading the email host");
		} finally {
			inputStream.close();
		}
		return password;
	}

	public String getInternalServiceHost() throws IOException {
		String url = "";
		try {
			Properties prop = new Properties();
			String propFileName = "application.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			url = prop.getProperty("internal.service.path");
		} catch (Exception e) {
			System.out.println("Exception while reading the email host");
		} finally {
			inputStream.close();
		}
		return url;
	}

	// Get both host email and password
	public String getPropValues() throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath.");
			}

			// get the property value and print it out
			String email = prop.getProperty("email_host");
			String pass = prop.getProperty("email_password");

			result = email + " " + pass;
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
	
}
