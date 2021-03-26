package ec.com.dinersclub.dddmodules.application.rest;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.com.dinersclub.dddmodules.application.cqrs.commands.ITarjetaCommandService;
import ec.com.dinersclub.dddmodules.application.cqrs.commands.dto.CreateTarjetaCommand;
import ec.com.dinersclub.dddmodules.application.cqrs.queries.ITarjetaQueryService;
import ec.com.dinersclub.dddmodules.application.cqrs.queries.dto.TarjetaQuery;

@Path("/tarjetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarjetaResource {

	@Inject
	ITarjetaQueryService readService;
	
	@Inject
	ITarjetaCommandService writeService;

    @GET
    public List<TarjetaQuery> list() {
        return readService.getAll();
    }

    @POST
    public Response add(@Valid CreateTarjetaCommand command) {
    	writeService.createTarjetaCommand(command);
    	return Response.status(201).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@NotBlank(message="id may not be blank") @PathParam(value = "id") UUID id) {
    	writeService.removeTarjetaCommand(id);
    	return Response.status(200).build();
    }
}