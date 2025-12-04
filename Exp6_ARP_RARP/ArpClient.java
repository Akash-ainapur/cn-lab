import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ArpClient {
    public static void main(String args[]) throws Exception {
        // 1. Connect
        Socket s = new Socket("localhost", 7777);

        // 2. Setup Streams
        Scanner serverReader = new Scanner(s.getInputStream());
        PrintStream serverWriter = new PrintStream(s.getOutputStream());
        Scanner keyboard = new Scanner(System.in);

        // 3. Ask User for Input
        System.out.print("Enter IP Address: ");
        String myIp = keyboard.nextLine();

        // 4. Send to Server
        serverWriter.println(myIp);

        // 5. Read Reply
        String response = serverReader.nextLine();
        System.out.println("MAC Address is: " + response);

        s.close();
    }
}
