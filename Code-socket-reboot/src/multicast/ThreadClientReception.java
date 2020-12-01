package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class ThreadClientReception extends Thread {
	
	private MulticastSocket s;
	private String name;
	
	/*
	 * ThreadClientReception constructor
	 */
	public ThreadClientReception(MulticastSocket s, String name) {
		super();
		this.s = s;
		this.name = name;
	}

	public MulticastSocket getS() {
		return s;
	}

	public void setS(MulticastSocket s) {
		this.s = s;
	}

	@Override
	public void run() {
		
		while(true) {
			byte[] buf = new byte[1000];
			/*
			 * Datagram Packet creation that will receive the data : recv
			 */
			DatagramPacket recv = new  DatagramPacket(buf, buf.length);
			try {
				/*
				 * Data reception into recv trough s (s being the multicast socket that joined the group in the main of Client)
				 */
				s.receive(recv);
				/*
				 * Reads the message entered into recv
				 */
				String message = new String(recv.getData(), 0, recv.getLength());
				/*
				 * If it is the ThreadClientReception of the user sending the message, it will not be sent to the user
				 */
				if(!message.startsWith(this.name)) {
					System.out.println(message);
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
