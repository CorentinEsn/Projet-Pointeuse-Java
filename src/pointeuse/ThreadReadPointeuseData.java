package pointeuse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

import core.ThreadDataTreatment;
import environnementEntreprise.Company;
/*import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;*/

public class ThreadReadPointeuseData implements Runnable {

	private SerialPointeuse dataToRead;
	private Company company;
	private int port;
	
	public ThreadReadPointeuseData(Company company, int port) {
		this.company = company;
		this.port = port;
	}
	
	protected void onPacketReceived(SerialPointeuse packet) {
        
//        switch (packet.getPacketType()) {
//            case EMPLOYEE_POINT: {
//                App.getInstance().checkin(((PacketEmployeePoint) packet).getEmployeeId());
//                break;
//            }
//        }
    }
	
	@Override
	public void run() {
		try {
    		System.out.println("Initialise server");
    		while(true) {
			ServerSocket myServerSocket = new ServerSocket(port);
			myServerSocket.setSoTimeout(0);
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
						Thread t = new Thread(new ThreadDataTreatment(dataToRead, company));
						t.start();
						servSocket.close();
						myServerSocket.close();
						System.out.println("Connexion closed");
						//return dataToRead;
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
