import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServer {
    public static void main(String args[]) throws Exception {
        // 1. Open a port (like opening a door)
        ServerSocket ss = new ServerSocket(4000);
        System.out.println("Server ready. Waiting for client...");

        // 2. Wait for client to connect
        Socket s = ss.accept();

        // 3. Setup Input (to read from client) and Output (to talk back)
        Scanner clientReader = new Scanner(s.getInputStream()); // Read from network
        PrintStream clientWriter = new PrintStream(s.getOutputStream()); // Write to network
        
        // 4. Setup Keyboard (to type your replies)
        Scanner keyboard = new Scanner(System.in);

        // 5. Chat Loop
        while(true) {
            // Read message from Client
            String msgFromClient = clientReader.nextLine();
            System.out.println("Client: " + msgFromClient);
            
            if(msgFromClient.equalsIgnoreCase("bye")) break; // Stop condition

            // Type your reply
            System.out.print("Server: ");
            String myReply = keyboard.nextLine();
            clientWriter.println(myReply); // Send to client
        }
        
        // 6. Close connection
        ss.close();
        s.close();
    }
}
