package ec.com.dinersclub.dddmodules.application.cqrs.queries;

import java.util.List;

import ec.com.dinersclub.dddmodules.application.cqrs.queries.dto.TarjetaQuery;

public interface ITarjetaQueryService {
	
	List<TarjetaQuery> getAll();

}
