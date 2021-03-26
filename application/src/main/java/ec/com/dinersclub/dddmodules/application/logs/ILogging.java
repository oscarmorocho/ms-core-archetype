package ec.com.dinersclub.dddmodules.application.logs;

public interface ILogging {
	
	void debug(String message);
	
	void info(String message);
	
	void warn(String message);
	
	void error(String message);

}
