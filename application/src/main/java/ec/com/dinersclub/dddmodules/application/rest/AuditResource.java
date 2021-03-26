package ec.com.dinersclub.dddmodules.application.rest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ec.com.dinersclub.dddmodules.application.cqrs.commands.dto.CreateTarjetaCommand;
import ec.com.dinersclub.dddmodules.application.events.audit.IAuditoriaEvent;
import ec.com.dinersclub.dddmodules.application.events.audit.dto.Auditoria;

@Path("/audit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuditResource {
	
	@ConfigProperty(name = "audit.microservice.name")
	private String microservice;

	@Inject
	IAuditoriaEvent auditService;


    @POST
    public Response add(@Valid CreateTarjetaCommand command) {
    	
    	Auditoria aud = new Auditoria();
    	aud.microservice = microservice;
    	aud.method = "POST";
    	aud.request = "entrada";
    	aud.response = "salida";
    	
    	auditService.generateEventHandler(aud);
    	
    	return Response.status(201).build();
    }

}
