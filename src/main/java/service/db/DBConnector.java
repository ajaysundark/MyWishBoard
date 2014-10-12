package main.java.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.java.ifs.ConnectionConfig;
import main.java.ifs.WishLogger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBConnector
{

	private WishLogger myLogger = null;

	private ApplicationContext dbContainer = null;
	private ConnectionConfig config = null;
	private Connection dbConnection = null;

	public DBConnector() {
		dbContainer = new ClassPathXmlApplicationContext("WishBoard-DBbeanfactory.xml");
		config = (SQLConnectionConfig) dbContainer.getBean("dbconnection-bean");
		myLogger = (DBLogger) dbContainer.getBean("log-bean");
	}

	private boolean initConnection()
	{
		myLogger.logDebug("Trying to establish Connection to DB :");
		
		String driver = config.getDriver();
		String connectionURL = config.getConnectionURL();

		/**
		 * No username and password since we changed to SQLite now!
		 */

		// String user = config.getUser();
		// String password = config.getPassword();

		if (!connectionURL.isEmpty())
		{
			try
			{
				Class.forName(driver);
				myLogger.logDebug("Database Driver loaded.");
				myLogger.logDebug("Connecting to DB...");
				dbConnection = DriverManager.getConnection(connectionURL);
				myLogger.log("Connected to DB.");
				return true;
			} catch (ClassNotFoundException NotFound)
			{
				myLogger.logError("JDBC Driver not found : [ " + driver + " ]", NotFound);
				return false;
			} catch (SQLException sqlEx)
			{
				myLogger.logError("Connection to " + connectionURL + "failed!", sqlEx);
				return false;
			}

		} else
		{
			myLogger.logError("[Error :] Connection URL is EMPTY!");

			return false;
		}
	}

	public Connection getDBConnection()
	{
		synchronized(this)
		{
			if(dbConnection==null) { initConnection(); }
			return dbConnection;
		}
	}

	public boolean terminateConnection()
	{
		Connection UsedDBSession = this.getDBConnection();
		try
		{
			UsedDBSession.close();
			myLogger.log("DB Session ended - connection closed.");
		} catch (SQLException e)
		{
			myLogger.logError("DB Connection problem!", e);
		}
		return true;
	}

	public String getTableName()
	{
		return config.getTableName();
	}
	
	public WishLogger getMyLogger()
	{
		return myLogger;
	}

	private class DBLogger extends WishLogger
	{

		public DBLogger(boolean debug) {
			super._DEBUG = debug;
		}

		@Override
		public void logDebug(String log)
		{
			if (_DEBUG) {
                    System.out.println("------------[DBConnector Debug]------------");
                    System.out.println(log);
                    System.out.println("------------[DBConnector Debug]------------");
            }
		}
	}
}
