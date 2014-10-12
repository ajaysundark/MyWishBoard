package service.mail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import ifs.WishLogger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailGenerator {	
	private ApplicationContext mailFactory;
	private WishLogger myLogger;
	private MailSession wisherObj;
	private static File file = null;

	public MailGenerator() {
		this.mailFactory = new ClassPathXmlApplicationContext("WishBoard-Mailbeanfactory.xml");
		this.wisherObj = (MailSession) mailFactory.getBean("mailsession-bean");
		this.myLogger = (MailLogger) mailFactory.getBean("log-bean");
	}

	private void renderImage()
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(new URL(wisherObj.getImgUrl()));
			myLogger.logDebug("Fetching Image from " + wisherObj.getImgUrl());
			file = new File("downloaded.jpg");
			ImageIO.write(img, "jpg", file);
		} catch (MalformedURLException e)
		{
			myLogger.logError("Check the URL of the image : " + wisherObj.getImgUrl(), e);
		} catch (IOException e)
		{
			myLogger.logError("Check File permissions/ Image could not be processed as file!", e);
		}
	}

	public Message generateWish(String wishReceiverName) throws MessagingException
	{
		Session mailsession = wisherObj.getWishSession();
		mailsession.setDebug(myLogger._DEBUG);

		Message msg = new MimeMessage(mailsession);
		msg.setFrom(new InternetAddress(wisherObj.getFrom()));
		msg.addRecipient(Message.RecipientType.CC, new InternetAddress(wisherObj.getCc()));
		return formatMessageBody(wisherObj.getRegards(), wishReceiverName, msg);
	}

	private Message formatMessageBody(String wisherName, String wishReceiverName, Message msg) throws MessagingException
	{
		if(file==null) { renderImage(); }
		String subj = "Happy Birthday dear " + wishReceiverName + "!";
		msg.setSubject(subj);
		String html = "<html>"
				+"<head> <title> Hearty Wishes! </title> </head>"
				+ "<body> <H3>Happy Birthday.. </H3> <p> <img src=\"cid:image\"></body>" +
				"</html>";

		Multipart mp = new MimeMultipart("related");
		BodyPart textPart = new MimeBodyPart();
		textPart.setText("\n\nWish you many more happy returns of the day, " + wishReceiverName + ".\n\nRegards,\n" + wisherName);

		BodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(html, "text/html");
		mp.addBodyPart(htmlPart);

		// second part (the image)
		htmlPart = new MimeBodyPart();
		DataSource fds = new FileDataSource (file);
		htmlPart.setDataHandler(new DataHandler(fds));
		htmlPart.setHeader("Content-ID","<image>");

		// add it
		mp.addBodyPart(htmlPart);
		mp.addBodyPart(textPart);

		// put everything together
		msg.setContent(mp);

		return msg;
	}

	protected WishLogger getLogger() {
		return myLogger;
	}
}