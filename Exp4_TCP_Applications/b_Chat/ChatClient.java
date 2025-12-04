import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String args[]) throws Exception {
        // 1. Connect to the Server (IP address + Port)
        // "localhost" means the server is on the same machine
        Socket s = new Socket("localhost", 4000);
        System.out.println("Connected to Server!");

        // 2. Setup Input (to read from server) and Output (to talk back)
        Scanner serverReader = new Scanner(s.getInputStream()); // Read from network
        PrintStream serverWriter = new PrintStream(s.getOutputStream()); // Write to network
        
        // 3. Setup Keyboard (to type your messages)
        Scanner keyboard = new Scanner(System.in);

        // 4. Chat Loop
        while(true) {
            // Type your message first
            System.out.print("Client: ");
            String myMsg = keyboard.nextLine();
            serverWriter.println(myMsg); // Send to server

            if(myMsg.equalsIgnoreCase("bye")) break; // Stop condition

            // Read reply from Server
            String msgFromServer = serverReader.nextLine();
            System.out.println("Server: " + msgFromServer);
        }
        
        // 5. Close connection
        s.close();
    }
}
