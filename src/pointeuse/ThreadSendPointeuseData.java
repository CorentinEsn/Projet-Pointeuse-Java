package pointeuse;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.*;
import java.util.ArrayList;
import java.util.UUID;

import environnementEntreprise.Pair;

public class ThreadSendPointeuseData implements Runnable {
	
	private SerialPointeuse dataToSend;
	private String address;
	private int port;
	
	private static ArrayList<SerialPointeuse> dataToKeep = readStockedData();
	
	
	public ThreadSendPointeuseData(SerialPointeuse dataToSend, String address, int port) {
		this.dataToSend = dataToSend;
		this.address = address;
		this.port = port;
	}
	
	private static ArrayList<SerialPointeuse> readStockedData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void addStockedData(SerialPointeuse failedData) {
		dataToKeep.add(failedData);
	}

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
	
	public void sendDataStocked(String address, int port) {
		
		for(SerialPointeuse data : dataToKeep) {
			try
	        {
	            //ServerSocket myServerSocket = new ServerSocket(8080);
	    		System.out.println("Initialise Client for failed data");
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
	                System.out.println("Error :  send failed, the data stays in dataToKeep");
	            }
	            catch (IOException e) 
	            {
	                e.printStackTrace();
	            }
	            clientSocket.close();
	            //the data was sent, remove it from the tab
	            dataToKeep.remove(data);
	        }
	    	catch (ConnectException e) {
	        	System.out.println("Error :  connection failed,  the data stays in dataToKeep");
	        }
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
			
			
		}
	}
}
