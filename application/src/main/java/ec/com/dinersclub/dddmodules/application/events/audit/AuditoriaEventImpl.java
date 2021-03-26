package ec.com.dinersclub.dddmodules.application.events.audit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import ec.com.dinersclub.dddmodules.application.events.audit.dto.Auditoria;

@ApplicationScoped
public class AuditoriaEventImpl implements IAuditoriaEvent{
	
	@Inject @Channel("generated-audit") Emitter<Auditoria> auditEmitter;
	
    public void generateEventHandler(Auditoria audit) {
    	auditEmitter.send(Message.of(audit));
    }

}
