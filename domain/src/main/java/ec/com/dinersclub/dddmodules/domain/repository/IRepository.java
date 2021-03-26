package ec.com.dinersclub.dddmodules.domain.repository;

import java.util.List;
import java.util.UUID;

import ec.com.dinersclub.dddmodules.domain.model.Tarjeta;

public interface IRepository {
	
	List<Tarjeta> getTarjetas();
	
    Tarjeta getTarjeta(UUID id);

    void createTarjeta(Tarjeta tarjeta);
    
    void deleteTarjeta(UUID id);
    
    void delCache(String key);

    String getCache(String key);

    void setCache(String key, String value);

}