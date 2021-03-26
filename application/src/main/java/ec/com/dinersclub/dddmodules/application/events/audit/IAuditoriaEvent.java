package ec.com.dinersclub.dddmodules.application.events.audit;

import ec.com.dinersclub.dddmodules.application.events.audit.dto.Auditoria;

public interface IAuditoriaEvent {

	void generateEventHandler(Auditoria audit);
	
}
