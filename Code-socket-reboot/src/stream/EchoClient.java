/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */
package stream;

import java.io.*;
import java.net.*;
import java.util.Date;
    
public class EchoClient {

  /**
   * main method accepts a connection, receives a message from client then sends
   * an echo to the client
   * @param args contains EchoServer host and EchoServer port
   **/
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Usage: java EchoClient <EchoServer host> <EchoServer port>");
      System.exit(1);
    }

    try (Socket echoSocket = new Socket(args[0], Integer.parseInt(args[1]));
        BufferedReader socIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        PrintStream socOut = new PrintStream(echoSocket.getOutputStream());
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));) {
      ClientThread ct = new ClientThread(echoSocket);
      String name = "";
      
      while (name.length() < 1) {
        System.out.println("Entrez un nom d'utilisateur");
        name = stdIn.readLine();
      }


      ct.start();
      String line;
      

      while (true) {
        line = stdIn.readLine();
        
        String message = "\n" + name + " a écrit : \n	" + line + "\nà :" + new Date().toString();  
        
        if (line.equals("."))
          break;
        socOut.println(message);
      }
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host:" + args[0]);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for " + "the connection to:" + args[0]);
      System.exit(1);
    }
  }
}
