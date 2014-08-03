package main.java.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.java.ifs.ConnectionConfig;
import main.java.ifs.WishLogger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBConnector {
        private final String oradriver = "oracle.jdbc.driver.OracleDriver";
        private WishLogger myLogger = null;

        private ApplicationContext dbContainer = null;

        private Connection dbConnection;

        public DBConnector() {
                dbContainer = new ClassPathXmlApplicationContext("WishBoard-DBbeanfactory.xml");
                myLogger = (WishLogger) dbContainer.getBean("log-bean");
        }

        public boolean initConnection() {
                myLogger.logDebug("Trying to establish Connection to DB :");
                ConnectionConfig config = (ConnectionConfig) dbContainer.getBean
("dbconnection-bean");

                String connectionURL = config.getConnectionURL();
                String user = config.getUser();
                String password = config.getPassword();

                if(!connectionURL.isEmpty()) {
                        try {
                                Class.forName(oradriver);
                                myLogger.logDebug("Oracle Driver loaded.");
                                myLogger.logDebug("Connecting to DB...");
                                dbConnection = DriverManager.getConnection(connectionURL, user, password);
                                myLogger.log("Connected to DB.");
                                return true;
                        } catch (ClassNotFoundException NotFound) {
                                myLogger.logError("Oracle Driver not found", NotFound);
                                return false;
                        } catch (SQLException sqlEx) {
                                myLogger.logError("Connection to "+connectionURL
+"failed!", sqlEx);
                                return false;
                        }

                } else {
                        myLogger.logError("[Error :] Connection URL is EMPTY!");

                        return false;
                }
        }

        public Connection getDBConnection() {
                return dbConnection;
        }

        public boolean terminateConnection(Connection UsedDBSession) {
                try {
                        UsedDBSession.close();
                        myLogger.log("DB Session ended - connection closed.");
                } catch (SQLException e) {
                        myLogger.logError("DB Connection problem!", e);
                }
                return true;
        }

        public WishLogger getMyLogger() {
                return myLogger;
        }

        public class DBLogger extends WishLogger {
                public DBLogger(boolean debug) {
                        super(debug);
                }
        }
}