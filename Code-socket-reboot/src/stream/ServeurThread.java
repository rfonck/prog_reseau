package stream;

/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */




import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class ServeurThread
	extends Thread {
	private List<Socket> listeclient;
	private Socket clientSocket;

	ServeurThread(Socket s) {
		this.clientSocket = s;
	}

 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	public void run() {
    	  try {
              synchronized (listeclient) {
            	  listeclient.add(clientSocket);
              }
              
        		
        		
    		  System.out.println("Révolution"); 
    		  BufferedReader socIn = null;
    		  socIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
    		  PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
    		  while (true) {

	    		  String line = socIn.readLine();
    			  
    			  for(Socket soc : listeclient) {
    				  // Ici ça serait cool d'exclure le cas ou le client s'envoie un message à lui même 
    				  if(soc.equals(clientSocket)) {
    					  
    				  }else {
	        				System.out.println("j'écris à" + soc.toString());
	      					try {
	      						// note pour JJ ici faut modifier 
	      						PrintWriter printWriter = new PrintWriter(soc.getOutputStream(), true);
	      						printWriter.println(line); 
	      					} catch (IOException e) {
	      						// TODO Auto-generated catch block
	      						e.printStackTrace();
	      					} 
    				  }

    			  }
	    		  

    		  
    		 
    		}
    	} catch (Exception e) {
        	System.err.println("Error in EchoServer:" + e); 
        }
       }

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}



	public ServeurThread(List<Socket> listeclient, Socket clientSocket) {
		super();
		this.listeclient = listeclient;
		this.clientSocket = clientSocket;
	}





	
  }

  
