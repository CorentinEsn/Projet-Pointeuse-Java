package pointeuse;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.time.*;
import java.util.ArrayList;
import java.util.UUID;

import environnementEntreprise.Pair;
import pointeuse.controller.AffListener;

public class ThreadSendPointeuseData implements Runnable {

	/**
	 * the data to send to the main program
	 */
	private SerialPointeuse dataToSend;
	/**
	 * the IP Address of destination
	 */
	private String address;
	/**
	 * the destination port
	 */
	private int port;

	/**
	 * a static array to store all the data that hasn't been sent for X reasons
	 */
	private static ArrayList<SerialPointeuse> dataToKeep = readStockedData();

	/**
	 * Constructor
	 * 
	 * @param dataToSend {@link ThreadSendPointeuseData#dataToSend}
	 * @param address {@link ThreadSendPointeuseData#address}
	 * @param port {@link ThreadSendPointeuseData#port}
	 */
	public ThreadSendPointeuseData(SerialPointeuse dataToSend, String address, int port) {
		this.dataToSend = dataToSend;
		this.address = address;
		this.port = port;
	}

	/**
	 * Read data from .dat file
	 * 
	 * usually called when there's a new data to sent
	 * 
	 * @return the Arraylist with all the data read from the .dat
	 */
	public static ArrayList<SerialPointeuse> readStockedData() {
		saving.Serializer readingData = new saving.Serializer();
		ArrayList<SerialPointeuse> gatheredData = readingData.serializeReadPointeuseData();
		if(gatheredData == null) {
			gatheredData = new ArrayList<SerialPointeuse>();
		}
		System.out.println(gatheredData);
		return gatheredData;
	}

	/**
	 * Write the ArrayList {@link ThreadSendPointeuseData#dataToKeep} to a .dat file
	 */
	public static void writeStockedData() {
		saving.Serializer writingData = new saving.Serializer();
		//		SerialPointeuse tabData[] = new SerialPointeuse[dataToKeep.size()];
		//		int i = 0;
		//		for(SerialPointeuse data : dataToKeep) {
		//			tabData[i] = data;
		//		}
		writingData.serializeWritePointeuseData(dataToKeep);
	}

	/**
	 * Function to fill the array with new data to stock
	 *
	 * @param failedData the data to add to the array
	 */
	private void addStockedData(SerialPointeuse failedData) {
		dataToKeep.add(failedData);
	}

	/**
	 * The main function, will connect to the server with the given info, and stock the data if the transfer fails
	 */
	public void run() {

		sendDataStocked(address, port);
		try
		{
			//ServerSocket myServerSocket = new ServerSocket(8080);
			System.out.println("Initialise Client");
			Socket clientSocket = new Socket(address, port);
			clientSocket.setSoTimeout(5000);
			try 
			{
				ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
				System.out.println("Sending data");
				objectOutput.writeObject(dataToSend);                
			} 
			catch (SocketTimeoutException exception) {
				// Output expected SocketTimeoutExceptions.
				System.out.println("Error :  send failed, stocking the data for now");
				addStockedData(dataToSend);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			clientSocket.close();
		}
		catch (ConnectException e) {
			System.out.println("Error :  connection failed, stocking the data for now");
			addStockedData(dataToSend);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sends data that is stocked in dataToKeep
	 * @param address {@link ThreadSendPointeuseData#address}
	 * @param port {@link ThreadSendPointeuseData#port}
	 */
	public void sendDataStocked(String address, int port) {

		ArrayList<SerialPointeuse> dataToRemove = new ArrayList<SerialPointeuse>();
		
		System.out.println(dataToKeep);
		for(SerialPointeuse data : dataToKeep) {
			try
			{
				//ServerSocket myServerSocket = new ServerSocket(8080);
				System.out.println("Initialise Client for failed data");
				Socket clientSocket = new Socket();
				clientSocket.connect(new InetSocketAddress(8080),2000);
				//clientSocket.setSoTimeout(5000);
				try 
				{
					ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
					System.out.println("Sending data");
					objectOutput.writeObject(dataToSend);                
				} 
				catch (SocketTimeoutException exception) {
					// Output expected SocketTimeoutExceptions.
					System.out.println("Error :  send failed, the data stays in dataToKeep");
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				clientSocket.close();
				//the data was sent, remove it from the tab
				//this would cause concurrentModificationException
				//dataToKeep.remove(data);
				//so instead we add the data to another arraylist in the meantime
				dataToRemove.add(data);
			}
			catch (ConnectException e) {
				System.out.println("Error :  connection failed,  the data stays in dataToKeep");
			}
			catch (SocketTimeoutException exception) {
				// Output expected SocketTimeoutExceptions.
				System.out.println("Error :  connection timeout, the data stays in dataToKeep");
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			


		}
		
		//all the data that have been sent are removed
		dataToKeep.removeAll(dataToRemove);
	}
	
	
}