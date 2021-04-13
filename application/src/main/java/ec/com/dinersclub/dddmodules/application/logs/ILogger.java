package ec.com.dinersclub.dddmodules.application.logs;

import ec.com.dinersclub.dddmodules.application.logs.dto.Log;

public interface ILogger {
	
	void debug(Log value);
	
	void info(Log value);
	
	void warn(Log value);
	
	void error(Log value);

}
