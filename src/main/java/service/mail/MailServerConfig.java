package service.mail;

import java.util.Properties;

/**
 * @author asundark
 *
 */
public class MailServerConfig {

        private Properties mailserverProps = null;

        public Properties getMailserverProps() {
                return mailserverProps;
        }

        public void setMailConfig(Properties serverProps) {
                this.mailserverProps = serverProps;
        }


}