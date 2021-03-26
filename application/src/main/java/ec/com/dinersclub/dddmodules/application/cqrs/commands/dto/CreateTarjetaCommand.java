package ec.com.dinersclub.dddmodules.application.cqrs.commands.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class CreateTarjetaCommand {
	
	//@Digits(integer = 3, fraction = 0)
	@NotBlank(message="id may not be blank")
	private UUID id;
	
	@NotBlank(message="nombre may not be blank")
    private String nombre;
    
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
    
}
