package pointeuse;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.UUID;

public class SerialPointeuse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UUID userUUID;
	private String sDateCheckInOut;
	private LocalDateTime dateCheckInOut;


	public SerialPointeuse(UUID userUUID, String sDateCheckInOut, LocalDateTime dateCheckInOut) {
		this.userUUID 			= userUUID;
		this.sDateCheckInOut 	= sDateCheckInOut;
		this.dateCheckInOut 	= dateCheckInOut;
	}

	/**
	 * give the UUID
	 * @return the UUID of the current data
	 */
	public UUID getUserUUID() {
		return userUUID;
	}

	/**
	 * give the date in string format
	 * @return the date in string format
	 */
	public String getStrDate() {
		return sDateCheckInOut;
	}


	/**
	 * give the date in LocalDateTime format
	 * @return the date in LocalDateTime format
	 */
	public LocalDateTime getDate() {
		return dateCheckInOut;
	}

	/**
	 * transform the data into a string, for test purposes
	 * @return the data in a string format
	 */
	@Override
	public String toString() {
		return 	"\nUUID : "
				+userUUID
				+"\nsDateCheckInOut = "
				+sDateCheckInOut
				+"\ndateCheckInOut = "
				+dateCheckInOut
				+"\n";
	}

}