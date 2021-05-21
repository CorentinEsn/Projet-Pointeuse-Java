package pointeuse;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

public class SerialPointeuse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userUUID;
	private String sDateCheckInOut;
	private LocalDateTime dateCheckInOut;
	
	public SerialPointeuse(String userUUID, String sDateCheckInOut, LocalDateTime dateCheckInOut) {
		this.userUUID 			= userUUID;
		this.sDateCheckInOut 	= sDateCheckInOut;
		this.dateCheckInOut 	= dateCheckInOut;
	}
	
	public String getUserUUID() {
		return userUUID;
	}

	public String getStrDate() {
		return sDateCheckInOut;
	}
	
	public LocalDateTime getDate() {
		return dateCheckInOut;
	}
	
	public String toString() {
		return 	"UUID : "
				+userUUID
				+"\nsDateCheckInOut = "
				+sDateCheckInOut
				+"\ndateCheckInOut = "
				+dateCheckInOut;
	}
	
	private void writeObject(final ObjectOutputStream oos) throws IOException {
		oos.writeObject(this.userUUID);
		oos.writeObject(this.sDateCheckInOut);
	    oos.writeObject(this.dateCheckInOut);
	}
	
	private void readObject(final ObjectInputStream ois) throws IOException, 
    ClassNotFoundException {
		this.userUUID = (String) ois.readObject();
		this.sDateCheckInOut = (String) ois.readObject();
		this.dateCheckInOut = (LocalDateTime) ois.readObject();
	}
}
