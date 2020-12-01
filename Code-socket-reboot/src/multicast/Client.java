package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.net.*;

import stream.ClientThread;

public class Client {

	public static void main(String[] args) throws IOException {
		/* Affichage de l'erreur si l'utilisateur oublie d'entrer l'adresse du groupe ou du port */
	    if (args.length != 2) {
	      System.out.println("Usage: java EchoClient <GroupAdress> <GroupPort>");
	      System.exit(1);
	    }

	    /* Set the group IP address */
	    InetAddress groupAddr = InetAddress.getByName(args[0]); 
	    Integer groupPort = Integer.parseInt(args[1]); 
	    
	    /* Create a multicast socket**/
	    MulticastSocket s = new MulticastSocket(groupPort); 
	    
	    /* Multisocket joins the group */
	    s.joinGroup(groupAddr);
	    
	    /*
	     * Creation of the buffered reader 
	     */
	    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: "); 
        /*
         * Input the name of the user
         */
        String name = stdIn.readLine();

        /*
         * Creation of the thread for reading messages
         */
	    ThreadClientReception ct = new ThreadClientReception(s, name);
	    ct.start();
	    
	    /*
	     * msg will contain the message if the user wants to send a message
	     */
	    String msg;
	    
	    while(true) {
	    	/*
	    	 * Set of msg when the user enters a message 
	    	 */
	    	msg = name + " a ecrit : \n	" + stdIn.readLine() + "\na :" + new Date().toString() + "\n";
	    	/*
	    	 * Datagram packet creation that will then be sent to all of the members of the group
	    	 */
	    	DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(), groupAddr, groupPort);
	    	s.send(hi);
	    }
	    
	  }
	
}
