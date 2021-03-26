package ec.com.dinersclub.dddmodules.application.logs;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

@ApplicationScoped
public class Logging implements ILogging {
	
	private static final Logger logger = Logger.getLogger(Logging.class);
	
	@Override
	public void debug(String message) {
		logger.debug(message);
	}
	
	@Override
	public void info(String message) {
		logger.info(message);
	}

	@Override
	public void warn(String message) {
		logger.warn(message);
		
	}

	@Override
	public void error(String message) {
		logger.error(message);
	}
}
