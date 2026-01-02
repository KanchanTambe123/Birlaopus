package commonutilities;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import config.ConfigReader;

public class SendMail {

	public static void sendEmailWithAttachment(List<String> recipients, String subject, String body,
			List<String> attachmentPaths) throws MessagingException, IOException {

		ConfigReader config = new ConfigReader();
		

		// Set SMTP properties
		Properties prop = new Properties();
		prop.put(config.getProb("smtpAuthentication"), true);    //Enable SMTP Authentication
		prop.put(config.getProb("tlsEncription"), true);  //Enable TLS encription
		prop.put(config.getProb("smtpHost"), config.getProb("host"));  //Set SMTP host
		prop.put(config.getProb("smtpPort"), config.getProb("port"));  //Set SMTP port
		prop.put(config.getProb("smtpCertificate"), config.getProb("host")); //Trust SMTP host SSL certificate

		// Create a Session with authentication
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(config.getProb("username"), config.getProb("password"));
			}
		});

		// Create the email message
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(config.getProb("username")));

		// Add multiple recipients
		for (String recipient : recipients) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		}

		message.setSubject(subject);

		// Create the message body part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(body);

		// Create the multipart message
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Attach the file
		if (attachmentPaths != null && !attachmentPaths.isEmpty()) {
            for (String attachmentPath : attachmentPaths) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(attachmentPath);
                multipart.addBodyPart(attachmentBodyPart);
            }
        }


		// Set the content of the message to the multipart message
		message.setContent(multipart);
		
		// Send the email
		Transport.send(message);
	}

		
}
