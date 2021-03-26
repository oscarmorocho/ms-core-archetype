package ec.com.dinersclub.dddmodules.application.cqrs.queries.dto;

import java.util.UUID;

public class TarjetaQuery {
	
	private UUID id;
    private String nombre;
    private String numero;
    private String fecha;
    
    public TarjetaQuery() {
    }

    public TarjetaQuery(UUID id, String nombre, String numero, String fecha) {
        this.setId(id);
        this.setNombre(nombre);
        this.setNumero(numero);
        this.setFecha(fecha);
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
