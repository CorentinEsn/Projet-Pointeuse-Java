package pointeuse;

import java.io.Serializable;

public class ConfigPointeuse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ipAddress;
	private int port;
	
	
	public ConfigPointeuse(String ipAddress, int port) {
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
