package ec.com.dinersclub.dddmodules.infrastructure.pgsql.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ec.com.dinersclub.dddmodules.domain.model.Tarjeta;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "tarjeta")
public class TarjetaEntity extends PanacheEntityBase {
	
	@Id
	private UUID id; 
    private String nombre;
    private String numero;
    private String fecha;
    
    public TarjetaEntity() {
    }
    
    public TarjetaEntity(UUID id, String nombre, String numero, String fecha) {
    	this.setId(id);
        this.setNombre(nombre);
        this.setNumero(numero);
        this.setFecha(fecha);
    }

    public TarjetaEntity(Tarjeta tarjeta) {
    	this.setId(tarjeta.getId());
        this.setNombre(tarjeta.getNombre());
        this.setNumero(tarjeta.getNumero());
        this.setFecha(tarjeta.getFecha());
    }
    
    public static List<Tarjeta> map(List<TarjetaEntity> tarjetas) {
        if (tarjetas == null) {
            return null;
        }

        List<Tarjeta> list = new ArrayList<Tarjeta>(tarjetas.size());
        for (TarjetaEntity tarjeta : tarjetas) {
            list.add(tarjeta.toTarjeta());
        }

        return list;
    }

    public Tarjeta toTarjeta() {
        return new Tarjeta(getId(), getNombre(), getNumero(), getFecha());
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
    
}