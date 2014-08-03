package main.java.service.mail;

import java.util.Properties;
import javax.mail.Session;

public class MailSession {
        private MailServerConfig config;
        private static Properties sysProps;

        public void setConfig(MailServerConfig config) {
                this.config = config;
        }

        public void establishSession() {
                Properties serverProps=config.getMailserverProps();

                String key_host = "smtphost";
                if(serverProps.containsKey(key_host)) {
                        String host = serverProps.getProperty(key_host);

                        //Get System properties
                        sysProps = System.getProperties();

                        //Setup Mail Server
                        sysProps.put("mail.smtp.starttls.enable", "true");
                        sysProps.put("mail.smtp.host", host);
                }
        }

        public static Session getWishSession() {
                return Session.getDefaultInstance(sysProps,null);
        }
}