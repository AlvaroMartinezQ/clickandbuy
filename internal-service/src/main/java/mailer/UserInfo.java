package mailer;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserInfo {
	
	// Properties file
	ReadPropertiesValue appConf = new ReadPropertiesValue();
	
	// Sender's email ID needs to be mentioned
    private String from;
    private String password;
    
    // Recipient's email ID needs to be mentioned
    private String to="";
    // User data
    private String data;
    
    private final String host = "smtp.gmail.com";
    
    public UserInfo(String email, String data) {
    	super();
    	this.to=email;
    	this.data=data;
    }
    
    public void setUp() {
    	try {
			this.from = appConf.getEmailHost();
			this.password = appConf.getPasswordHost();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	// Bad credentials return immediately
    	if(this.from==""||this.password=="") {
    		System.out.println("\nEmail host credentials. Fix your config.properties file.\n");
    		return;
    	}
    	// Get system properties
        Properties properties = System.getProperties();
        
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        // Get the Session object.
        // and pass user name and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        
        // Used to debug SMTP issues. Set to true if you want to DEBUG
        session.setDebug(false);
        
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Correo de datos personales | ClickAndBuy Co.");

            // Now set the actual message
            message.setText(this.data);

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
