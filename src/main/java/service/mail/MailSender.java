package main.java.service.mail;

import javax.mail.Message;

public class MailSender extends Thread {

        private Message msg = MailGenerator.generateWish();

        /**
         * Create a thread to send mail for every entry
         * run()
         *      |
         *      v
         * sendBirthdayWish()
         * */

        public void run() {
                synchronized (this) {

                }
        }
}