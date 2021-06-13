package pointeuse;

import java.io.Serializable;

/**
 * Save class to serialize the configuration of the pointeuse
 * @author Lilian
 *
 */
public class ConfigPointeuse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the IP Address to be saved
	 */
	private String ipAddress;
	/**
	 * the port to be saved
	 */
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
