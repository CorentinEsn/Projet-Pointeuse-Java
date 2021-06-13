package core;

import java.io.Serializable;

public class ConfigCore implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the name that the company will have
	 */
	private String companyName;
	/**
	 * the port to be saved
	 */
	private int port;
	
	
	public ConfigCore(String companyName, int port) {
		this.companyName = companyName;
		this.port = port;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
