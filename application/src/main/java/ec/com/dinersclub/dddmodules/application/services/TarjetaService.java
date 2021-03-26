package ec.com.dinersclub.dddmodules.application.services;

import ec.com.dinersclub.dddmodules.application.cqrs.commands.dto.CreateTarjetaCommand;
import ec.com.dinersclub.dddmodules.application.grpc.MutinyTarjetaGrpc;
import ec.com.dinersclub.dddmodules.application.grpc.TarjetaRequest;
import ec.com.dinersclub.dddmodules.application.grpc.TarjetaResponse;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped  
public class TarjetaService {

	@Inject
    @GrpcService("tarjeta")                     
	MutinyTarjetaGrpc.MutinyTarjetaStub grpc;
	
	public void createTarjetaCommand(CreateTarjetaCommand command) {
		TarjetaResponse response = grpc.createTarjetaCommand(TarjetaRequest.newBuilder().setId(command.getId().toString()).setNombre(command.getNombre()).build()).await().indefinitely();
		System.out.println(response.getId());
	}
	
}
