package ec.com.dinersclub.dddmodules.application.logs;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.LoggerFactory;

import ec.com.dinersclub.dddmodules.application.logs.dto.Log;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class Logger implements ILogger {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);
	
	@Override
	public void debug(Log value) {
		log.debug(JsonObject.mapFrom(value).toString());
	}
	
	@Override
	public void info(Log value) {
		log.info(JsonObject.mapFrom(value).toString());
	}

	@Override
	public void warn(Log value) {
		log.warn(JsonObject.mapFrom(value).toString());
	}
	
	@Override
	public void error(Log value) {
		log.error(JsonObject.mapFrom(value).toString());
	}
}
