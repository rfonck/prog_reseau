/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread {

	private Socket clientSocket;

	ClientThread(Socket s) {
		this.clientSocket = s;
	}

	/**
	 * receives a request from client then sends an echo to the client
	 * 
	 * @param clientSocket the client socket
	 **/
	@Override
	public void run() {
		try {
			while (!clientSocket.isClosed()) {
				ObjectInputStream ois = new  ObjectInputStream(clientSocket.getInputStream()); 
  			    Message mes = (Message) ois.readObject();
  			    System.out.println(mes.toString());

			}
		} catch (Exception e) {
			System.err.println("Error in EchoServer:" + e);
		}
	}
}
