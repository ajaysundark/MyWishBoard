package service.mail;

import ifs.WishLogger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

public class MailSender extends Thread {

		private Entry e;
        
        protected WishLogger logger;
        
        public MailSender(String name, String mailid) {
        	e = new Entry(name, mailid);
		}

        public void run() {
        	try
			{
				Message msg = new MailGenerator().generateWish(e.getName());
				synchronized (this) {
					sendBirthdayWish(msg, e.getMailId());
				}
			} catch (MessagingException me)
			{
				logger.logError("Screwed up sewing the mail message!", me);
			}
        }
        
        private void sendBirthdayWish(Message msg, String mailId) throws MessagingException
		{
        	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailId));
        	logger.logDebug("Sending mail to " + mailId);
        	Transport.send(msg);
		}

		protected class Entry
        {
			
			/*
			 * Why Entry as an object here?
			 * Okay, the first thought was to create a "RecipientList<Entry>"
			 * which could be filled up by MailManager threads.
			 * so that, Transport can be handled at one go with the list!
			 * 
			 * Then, there are always thread safety concerns (sharing resources! Savvy?)
			 * */
        	private String name;
        	private String mailid;
        	
        	Entry (String name, String mailid) {
        		this.name = name;
        		this.mailid = mailid;
        	}
        	
        	protected String getName() {
        		return this.name;
        	}
        	
        	protected String getMailId() {
        		return this.mailid;
        	}
        }
}