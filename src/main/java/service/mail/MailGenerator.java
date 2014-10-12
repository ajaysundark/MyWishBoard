package main.java.service.mail;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailGenerator {

        private ApplicationContext mailFactory;
        public MailGenerator() {
                this.mailFactory = new ClassPathXmlApplicationContext("WishBoard-Mailbeanfactory.xml");
        }

        public static Message generateWish() {
                Session mailsession = MailSession.getWishSession();
                Message msg = new MimeMessage(mailsession);
                return msg;
        }

                /*
                 * Use Beans to get from, cc and Subject from properties
                 * Build the MimeMessage.
                 * generateWish() should return its parent Message
                 *
                 * */
}