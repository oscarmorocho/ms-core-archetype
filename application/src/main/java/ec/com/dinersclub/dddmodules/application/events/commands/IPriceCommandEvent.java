package ec.com.dinersclub.dddmodules.application.events.commands;

import io.smallrye.mutiny.Multi;

public interface IPriceCommandEvent {

	Multi<Integer> generateEventHandler();
	
}
