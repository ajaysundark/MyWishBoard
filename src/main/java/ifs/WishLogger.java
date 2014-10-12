package main.java.ifs;

public abstract class WishLogger
{
        public boolean _DEBUG = false;

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

        public abstract void logDebug(String log);

        public void log(String log) {
                System.out.println(log);
        }
}