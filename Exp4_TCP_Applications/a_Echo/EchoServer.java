import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoServer {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(4000);
        System.out.println("Echo Server Ready...");
        Socket s = ss.accept(); // Wait for client

        Scanner reader = new Scanner(s.getInputStream());
        PrintStream writer = new PrintStream(s.getOutputStream());

        while(true) {
            String msg = reader.nextLine();
            if(msg.equalsIgnoreCase("bye")) break;
            
            // ECHO LOGIC: Just send the same message back!
            System.out.println("Echoing: " + msg);
            writer.println(msg); 
        }
        ss.close();
    }
}
