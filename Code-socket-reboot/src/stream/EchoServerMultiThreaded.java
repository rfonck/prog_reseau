package stream;

/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */


import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EchoServerMultiThreaded  {
	
 	/**
  	* main method
	* @param EchoServer port 
  	* 
  	**/
       public static void main(String args[]){ 
        ServerSocket listenSocket;
		List<Socket> listeClient = Collections.synchronizedList(new ArrayList<Socket>());
		List<Message> listeMessage = Collections.synchronizedList(new ArrayList<Message>());
  	if (args.length != 1) {
          System.out.println("Usage: java EchoServer <EchoServer port>");
          System.exit(1);
  	}
	try {
		listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
		System.out.println("Server ready..."); 

		while (true) {
			Socket clientSocket = listenSocket.accept();
			System.out.println("Connexion from:" + clientSocket.getInetAddress());
			ServeurThread ct = new ServeurThread(listeClient, clientSocket, listeMessage);
			ct.start();
			
        }
	}catch (Exception e) {
        System.err.println("Error in EchoServer:" + e);
    }
  }
}

  
