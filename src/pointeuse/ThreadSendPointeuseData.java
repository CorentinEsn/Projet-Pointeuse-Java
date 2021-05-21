package pointeuse;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ThreadSendPointeuseData implements Runnable {
	
	private SerialPointeuse dataToSend;
	private String address;
	private int port;
	
	private static String dataToKeep; 
	
	public ThreadSendPointeuseData(SerialPointeuse dataToSend, String address, int port) {
		this.dataToSend = dataToSend;
		this.address = address;
		this.port = port;
	}
	
	public void run() {
    	
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
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            clientSocket.close();
        }
    	catch (ConnectException e) {
        	System.out.println("Error :  connection failed, stocking the data for now");
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
