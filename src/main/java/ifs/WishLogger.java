package main.java.ifs;

public abstract class WishLogger {

        public boolean _DEBUG = false;

        public WishLogger(boolean debug) {
                this._DEBUG=debug;
        }

        public void logError(String log, Exception e) {
                System.err.println("------------[Error]------------");
                System.err.println(log);
                e.printStackTrace();
                System.err.println("------------[Error]------------");
        }

        public void logError(String log) {
                System.err.println("------------[Error]------------");
                System.err.println(log);
                System.err.println("------------[Error]------------");
        }

        public void logDebug(String log) {
                if (_DEBUG) {
                        System.out.println("------------[Debug]------------");
                        System.out.println(log);
                        System.out.println("------------[Debug]------------");
                }
        }

        public void log(String log) {
                System.out.println(log);
        }
}