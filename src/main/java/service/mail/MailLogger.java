package service.mail;

import ifs.WishLogger;

class MailLogger extends WishLogger
{
	public MailLogger(boolean debug) {
		super._DEBUG = debug;
	}

	@Override
	public void logDebug(String log)
	{
		if (_DEBUG) {
                System.out.println("------------[Mail Director Debug]------------");
                System.out.println(log);
                System.out.println("------------[Mail Director Debug]------------");
        }
	}
}
