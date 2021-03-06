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
	private List<Message> listeMessage;

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
//            ObjectOutputStream oosNouveauClient = new  ObjectOutputStream(clientSocket.getOutputStream());
//    		  for (Message mesNouveauClient: listeMessage) {
//    			  oosNouveauClient.writeObject(mesNouveauClient);
//    		  }
    		  
    		  while (true) {
    			  ObjectInputStream ois = new  ObjectInputStream(clientSocket.getInputStream()); 
    			  Message mes = (Message) ois.readObject();
   
    			  for(Socket soc : listeclient) {
    				  if(soc.equals(clientSocket)) { 
    					  
    				  }else {
      					try {
      						ObjectOutputStream oos = new  ObjectOutputStream(soc.getOutputStream());
      						oos.writeObject(mes);
      					} catch (IOException e) {
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

	public ServeurThread(List<Socket> listeclient, Socket clientSocket, List<Message> listeMessage) {
		super();
		this.listeclient = listeclient;
		this.clientSocket = clientSocket;
		this.listeMessage = listeMessage;
	}





	
  }

  
