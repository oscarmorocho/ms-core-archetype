package ec.com.dinersclub.dddmodules.application.events.audit.dto;

import java.util.Date;

public class Auditoria {
	
	public String microservice;
	public String method;
	private String date;
	public String request;
	public String response;
	
	public Auditoria() {
		this.date = new Date().toString();
	}

	public String getDate() {
		return date;
	}
	
}
