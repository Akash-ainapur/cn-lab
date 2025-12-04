import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 4000);
        Scanner reader = new Scanner(s.getInputStream());
        PrintStream writer = new PrintStream(s.getOutputStream());
        Scanner keyboard = new Scanner(System.in);

        while(true) {
            System.out.print("Enter msg: ");
            String msg = keyboard.nextLine();
            writer.println(msg); // Send
            if(msg.equalsIgnoreCase("bye")) break;

            String reply = reader.nextLine(); // Read Echo
            System.out.println("Server echoed: " + reply);
        }
        s.close();
    }
}
