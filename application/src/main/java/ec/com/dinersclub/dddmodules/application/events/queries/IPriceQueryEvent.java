package ec.com.dinersclub.dddmodules.application.events.queries;

public interface IPriceQueryEvent {
	
	double process(int priceInUsd);

}
