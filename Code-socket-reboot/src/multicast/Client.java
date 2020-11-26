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
	    if (args.length != 2) {
	      System.out.println("Usage: java EchoClient <GroupAdress> <GroupPort>");
	      System.exit(1);
	    }

	    // Group IP address 
	    InetAddress groupAddr = InetAddress.getByName(args[0]); 
	    Integer groupPort = Integer.parseInt(args[1]); 
	    
	    // Create a multicast socket 
	    MulticastSocket s = new MulticastSocket(groupPort); 
	    
	    // Join the group 
	    s.joinGroup(groupAddr);
	    
	    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: "); 
        String name = stdIn.readLine();
        
        // Creates a thread for reading messages
	    ThreadClientReception ct = new ThreadClientReception(s, name);
	    ct.start();
	    
	    String msg;
	    
	    while(true) {
	    	msg = name + " a ecrit : \n	" + stdIn.readLine() + "\na :" + new Date().toString() + "\n";
	    	DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(), groupAddr, groupPort);
	    	s.send(hi);
	    }
	    
	  }
	
}
