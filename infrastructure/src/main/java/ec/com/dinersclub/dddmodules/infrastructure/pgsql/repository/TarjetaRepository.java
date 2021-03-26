package ec.com.dinersclub.dddmodules.infrastructure.pgsql.repository;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import ec.com.dinersclub.dddmodules.infrastructure.pgsql.entities.TarjetaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TarjetaRepository implements PanacheRepository<TarjetaEntity> {
	
	public TarjetaEntity findById(UUID id){
		return find("id", id).firstResult();
	}
	   
}
