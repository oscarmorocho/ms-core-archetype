package ec.com.dinersclub.dddmodules.application.grpc;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import ec.com.dinersclub.dddmodules.application.cqrs.commands.ITarjetaCommandService;
import ec.com.dinersclub.dddmodules.application.cqrs.commands.dto.CreateTarjetaCommand;
import ec.com.dinersclub.dddmodules.application.grpc.MutinyTarjetaGrpc;
import ec.com.dinersclub.dddmodules.application.grpc.TarjetaResponse;
import ec.com.dinersclub.dddmodules.application.grpc.TarjetaRequest;


@Singleton                                                                                   
public class TarjetaResource extends MutinyTarjetaGrpc.TarjetaImplBase {      
	
	@Inject
	ITarjetaCommandService writeService;

    @Override
    @Blocking
    public Uni<TarjetaResponse> createTarjetaCommand(TarjetaRequest request) { 
    	String id = request.getId();
    	String nombre = request.getNombre();
        CreateTarjetaCommand command = new CreateTarjetaCommand();
        command.setId(UUID.fromString(id));
        command.setNombre(nombre);
        writeService.createTarjetaCommand(command);
        return Uni.createFrom().item(TarjetaResponse.newBuilder().setId(id).build());
        
    }
}