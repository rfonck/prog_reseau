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
  
	static void doService(Socket clientSocket) {
  	  try {
  		BufferedReader socIn = null;
  		socIn = new BufferedReader(
  			new InputStreamReader(clientSocket.getInputStream()));    
  		PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
  		while (true) {
  			String line = socIn.readLine();
  			System.out.println("echo: " + line);
	    		
	    		socOut.println(line);
  		}
  	} catch (Exception e) {
      	System.err.println("Error in EchoServer:" + e); 
      }
     }
	
 	/**
  	* main method
	* @param args contains EchoServer port
  	* 
  	**/
       public static void main(String args[]){ 
        ServerSocket listenSocket;
		List<Socket> listeclient = Collections.synchronizedList(new ArrayList<Socket>());
		List<String> listeMessages = Collections.synchronizedList(new ArrayList<String>());
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
			ServeurThread ct = new ServeurThread(listeclient, clientSocket, listeMessages);
			ct.start();
			
        }
	}catch (Exception e) {
        System.err.println("Error in EchoServer:" + e);
    }
  }
}

  
