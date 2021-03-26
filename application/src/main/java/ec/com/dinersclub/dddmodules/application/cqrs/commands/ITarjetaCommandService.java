package ec.com.dinersclub.dddmodules.application.cqrs.commands;

import java.util.UUID;

import ec.com.dinersclub.dddmodules.application.cqrs.commands.dto.CreateTarjetaCommand;

public interface ITarjetaCommandService {
	
	void createTarjetaCommand(CreateTarjetaCommand command);
	
	void removeTarjetaCommand(UUID id);

}
