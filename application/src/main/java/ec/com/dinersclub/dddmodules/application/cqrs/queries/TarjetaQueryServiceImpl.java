package ec.com.dinersclub.dddmodules.application.cqrs.queries;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ec.com.dinersclub.dddmodules.application.cqrs.queries.dto.TarjetaQuery;
import ec.com.dinersclub.dddmodules.domain.model.Tarjeta;
import ec.com.dinersclub.dddmodules.domain.repository.IRepository;

@ApplicationScoped
public class TarjetaQueryServiceImpl implements ITarjetaQueryService{
	
	@Inject
	IRepository repository;
	
	public List<TarjetaQuery> getAll() {
        return map(repository.getTarjetas());
    }
	
    public List<TarjetaQuery> map(List<Tarjeta> tarjetas) {
        if (tarjetas.isEmpty()) {
            return null;
        }

        List<TarjetaQuery> list = new ArrayList<TarjetaQuery>(tarjetas.size());
        for (Tarjeta tarjeta : tarjetas) {
            list.add(entityToDto(tarjeta));
        }

        return list;
    }

    protected TarjetaQuery entityToDto(Tarjeta tarjeta) {
        if (tarjeta == null) {
            return null;
        }
        return new TarjetaQuery(tarjeta.getId(), tarjeta.getNombre(), tarjeta.getNumero(), tarjeta.getFecha());
    }

}
