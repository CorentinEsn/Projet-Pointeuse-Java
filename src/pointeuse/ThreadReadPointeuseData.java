package pointeuse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ThreadReadPointeuseData implements Runnable {

	private SerialPointeuse dataToRead;
	private int port;
	
	public ThreadReadPointeuseData(SerialPointeuse dataToRead, int port) {
		this.dataToRead = dataToRead;
		this.port = port;
	}
	
	public void run() {
		try {
    		System.out.println("Initialise server");
			ServerSocket myServerSocket = new ServerSocket(port);
			myServerSocket.setSoTimeout(10000);
			System.out.println("server waiting for connexion");
			Socket servSocket = myServerSocket.accept();
			System.out.println("server connected");
			//servSocket.setSoTimeout(10000);
			
			try {
				ObjectInputStream objectInput = new ObjectInputStream(servSocket.getInputStream());
				try {
					SerialPointeuse dataToRead =(SerialPointeuse) objectInput.readObject();
					//
					System.out.println("data received");
					System.out.println(dataToRead);
					servSocket.close();
					myServerSocket.close();
					//return dataToRead;
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
