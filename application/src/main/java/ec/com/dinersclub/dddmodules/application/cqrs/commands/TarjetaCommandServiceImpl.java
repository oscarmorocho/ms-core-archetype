package ec.com.dinersclub.dddmodules.application.cqrs.commands;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ec.com.dinersclub.dddmodules.application.cqrs.commands.dto.CreateTarjetaCommand;
import ec.com.dinersclub.dddmodules.domain.model.Tarjeta;
import ec.com.dinersclub.dddmodules.domain.repository.IRepository;

@ApplicationScoped
public class TarjetaCommandServiceImpl implements ITarjetaCommandService{
	
	@Inject
	IRepository repository;
	
	
	public void createTarjetaCommand(CreateTarjetaCommand command) {
		Date fecha = new Date();
		Random r = new Random();
		String random = r.nextInt(9999)+" "+r.nextInt(999999)+" "+r.nextInt(9999);
		repository.createTarjeta(new Tarjeta(command.getId(),command.getNombre(),random, fecha.toString()));
		//redisRepository.set(String.valueOf(command.getId()), command.getName());
    }
	
	public void removeTarjetaCommand(UUID id) {
		repository.deleteTarjeta(id);
		//redisRepository.del(String.valueOf(id));
    }
	
}
