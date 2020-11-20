package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class ThreadClientReception extends Thread {
	
	private MulticastSocket s;
	
	public ThreadClientReception(MulticastSocket s) {
		super();
		this.s = s;
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
			DatagramPacket recv = new  DatagramPacket(buf, buf.length);
			try {
				s.receive(recv);
				String message = new String(recv.getData(), 0, recv.getLength());
				System.out.println(message);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
