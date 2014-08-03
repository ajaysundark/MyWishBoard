package main.java.service.db;

import main.java.ifs.ConnectionConfig;

/**
 * @author asundark
 *
 */
public class SQLConnectionConfig implements ConnectionConfig{

        private static final String connUrlPrefx = "jdbc:oracle:thin";
        private String host = new String();
        private String port = new String();
        private String instance = new String();
        private String sqlUser = new String();
        private String sqlPassword = new String();

        private String connectionURL = new String();

        public SQLConnectionConfig() {
                connectionURL=connUrlPrefx+"@"+host+":"+port+":"+instance;
        }

        public String getConnectionURL() {
                return connectionURL;
        }

        public void setConnectionURL(String connectionURL) {
                this.connectionURL = connectionURL;
        }

        public static String getConnurlprefx() {
                return connUrlPrefx;
        }

        public void setHost(String host) {
                this.host = host;
        }

        public void setPort(String port) {
                this.port = port;
        }

        public void setInstance(String instance) {
                this.instance = instance;
        }

        public void setSqlUser(String sqlUser) {
                this.sqlUser = sqlUser;
        }

        public String getUser() {
                return sqlUser;
        }

        public void setSqlPassword(String sqlPassword) {
                this.sqlPassword = sqlPassword;
        }

        public String getPassword() {
                return sqlPassword;
        }

}