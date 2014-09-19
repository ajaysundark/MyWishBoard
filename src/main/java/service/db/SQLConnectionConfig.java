package main.java.service.db;

import main.java.ifs.ConnectionConfig;

/**
 * @author asundark
 *
 */
public class SQLConnectionConfig implements ConnectionConfig{
	
		private String driver;
    	private String fileName = "wishboard.db";
    	private String tableName = "birthday";

		private final String connUrlPrefx = "jdbc:sqlite";

        private String connectionURL = connUrlPrefx+":"+fileName;;

        public SQLConnectionConfig(String driver) {
        	this.driver = driver;
        }

        public String getConnectionURL() {
                return connectionURL;
        }

		public String getDriver() {
			return driver;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
		public String getFileName() {
			return fileName;
		}

        public String getTableName()
		{
			return tableName;
		}

		public void setTableName(String tablename)
		{
			this.tableName = tablename;
		}

		public String getUser() {
			// is not applicable for SQLite3
			return null;
		}

		public String getPassword() {
			// is not applicable for SQLite3
			return null;
		}

}