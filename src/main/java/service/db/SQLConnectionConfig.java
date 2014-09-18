package main.java.service.db;

import main.java.ifs.ConnectionConfig;

/**
 * @author asundark
 *
 */
public class SQLConnectionConfig implements ConnectionConfig{
	
		private static final String driver = "org.sqlite.JDBC";
    	private String filename = "wishboard.db";

        private static final String connUrlPrefx = "jdbc:sqlite";
        private String sqlUser = new String();
        private String sqlPassword = new String();

        private String connectionURL = connUrlPrefx+":"+filename;;

        public SQLConnectionConfig() {
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

        public void setSqlUser(String sqlUser) {
                this.sqlUser = sqlUser;
        }

        //User is obsolete now as we are using SQLite3
        public String getUser() {
                return sqlUser;
        }

        public void setSqlPassword(String sqlPassword) {
                this.sqlPassword = sqlPassword;
        }

        //Password is obsolete now as we are using SQLite3
        public String getPassword() {
                return sqlPassword;
        }

		public String getDriver() {
			return driver;
		}

		public void setFileName(String fileName) {
			this.filename = fileName;
		}
		
		public String getFileName() {
			return filename;
		}

}