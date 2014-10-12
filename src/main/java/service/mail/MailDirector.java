package main.java.service.mail;

import main.java.ifs.WishLogger;

public class MailDirector {

        public WishLogger logger;

        public class MailLogger extends WishLogger {
        	public MailLogger(boolean debug) {
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