package pointeuse.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

import javax.swing.*;

import pointeuse.SerialPointeuse;
import pointeuse.ThreadReadPointeuseData;
import pointeuse.ThreadSendPointeuseData;
import pointeuse.view.PointeuseView;

import java.io.Serializable;


public class PointeuseController{

	/**
	 * formatter to show the time in an Hours:Minutes format
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH':'mm");
	/**
	 * refresh delay variable
	 */
	public static final int delay = 1000; //milliseconds

	public PointeuseController() {

	}

	public static void main(String [] args){
		@SuppressWarnings("unused")
		JFrame frame = new PointeuseView();     
	}


	/**
	 * give the current date with a specified format
	 * @return the date with a string format of "Month Day(st/nd/rd/th) , Year"
	 */
	public static String getFullDateFromCurrentDateTime() {

		LocalDateTime ldtCurrent = LocalDateTime.now();

		DateTimeFormatter formatterFull;


		switch (ldtCurrent.getDayOfMonth() % 10) {
		case 1:
			formatterFull = DateTimeFormatter.ofPattern("MMM d'st' , u");
			formatterFull = formatterFull.withLocale(Locale.ENGLISH);
			break;
		case 2:
			formatterFull = DateTimeFormatter.ofPattern("MMM d'nd' , u");
			formatterFull = formatterFull.withLocale(Locale.ENGLISH);
			break;
		case 3:
			formatterFull = DateTimeFormatter.ofPattern("MMM d'rd' , u");
			formatterFull = formatterFull.withLocale(Locale.ENGLISH);
			break;
		default:
			formatterFull = DateTimeFormatter.ofPattern("MMM d'th' , u");
			formatterFull = formatterFull.withLocale(Locale.ENGLISH);
		}


		return ldtCurrent.format(formatterFull);
	}

	/**
	 * give the current time with a specified format, rounded to by 15 min
	 * @return the date with a LocalDateTime
	 */
	public static LocalDateTime getFullTimeFromCurrentDateTime() {

		LocalDateTime ldtCurrent = LocalDateTime.now();	    
		int currentMin = ldtCurrent.getMinute() % 15;

		if(currentMin > 7) {
			ldtCurrent = ldtCurrent.plusMinutes(15-currentMin);
		}else {
			ldtCurrent = ldtCurrent.minusMinutes(currentMin);
		}

		return ldtCurrent;
	}


	/**
	 * create a thread that will send the data, as well as send the older unsent data, and stock them if it fails
	 * @param dataToSend the data to send
	 * @param address the IP address to which the data will be sent
	 * @param port the port to which the data will be sent
	 */
	public static void sendPointeuseDataTCP(SerialPointeuse dataToSend, String address, int port) {

		Thread t = new Thread(new ThreadSendPointeuseData(dataToSend, address, port));
		t.start();

	}

	/**
	 * create a thread that will recieve the data, currently used to test the sending
	 * @param port the port to which the data will be sent
	 * @return a SerialPointeuse object containing the data
	 */
	public SerialPointeuse readPointeuseDataTCP(int port) {

		SerialPointeuse dataToRead = new SerialPointeuse(null, "", null);

		Thread t = new Thread(new ThreadReadPointeuseData(dataToRead, port));
		t.start();



		return dataToRead;
	}

}

