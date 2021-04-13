package ec.com.dinersclub.dddmodules.application.logs.dto;

public class Log {
	
	private String method;
	private Object data;
	
	public Log() {}
	
	public Log(String method, Object data) {
		this.setMethod(method);
		this.setData(data);
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
