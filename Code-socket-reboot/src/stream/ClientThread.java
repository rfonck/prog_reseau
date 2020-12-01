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
	 **/
	@Override
	public void run() {
		try (BufferedReader socIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			while (!clientSocket.isClosed()) {
				System.out.println(socIn.readLine());
			}
		} catch (Exception e) {
			System.err.println("Error in EchoServer:" + e);
		}
	}
}
