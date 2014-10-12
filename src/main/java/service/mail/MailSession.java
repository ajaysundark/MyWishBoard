package service.mail;

import java.util.Properties;
import javax.mail.Session;

public class MailSession {
        private MailServerConfig config;
        private static Properties sysProps;
        
        private String from;
        private String cc;
        private String user;
        private String pwd;
        private String regards;
        private String imgurl;
        
        private final String key_host = "smtphost";
        private final String key_from = "from";
        private final String key_cc = "cc";
        private final String key_user = "user";
        private final String key_pwd = "pwd";
        private final String key_regards = "regards";
        private final String key_img = "img";

        public void setConfig(MailServerConfig config) {
                this.config = config;
        }

        public void establishSession() {
                Properties serverProps=config.getMailserverProps();

                
                if(serverProps.containsKey(key_host)) {
                        String host = serverProps.getProperty(key_host);

                        //Get System properties
                        sysProps = System.getProperties();

                        //Setup Mail Server
                        sysProps.put("mail.smtp.starttls.enable", "true");
                        sysProps.put("mail.smtp.host", host);
                }
                
                initialize(serverProps);
        }

        private void initialize(Properties serverProps)
		{
        	this.from = serverProps.getProperty(key_from);
        	this.cc = serverProps.getProperty(key_cc);
        	this.regards = serverProps.getProperty(key_regards);
        	this.user = serverProps.getProperty(key_user);
        	this.pwd = serverProps.getProperty(key_pwd);
        	this.imgurl = serverProps.getProperty(key_img);
		}

		protected String getFrom()
		{
			return this.from;
		}

		protected String getCc()
		{
			return this.cc;
		}
		
		protected String getUser()
		{
			return this.user;
		}
		
		protected String getPwd()
		{
			return this.pwd;
		}

		protected String getRegards()
		{
			return this.regards;
		}
		
		protected String getImgUrl()
		{
			return this.imgurl;
		}

		protected Session getWishSession() {
                return Session.getInstance(sysProps,null);
        }
}