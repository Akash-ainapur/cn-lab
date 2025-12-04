import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String args[]) throws Exception {
        // 1. Open Port
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Waiting for client to take the file...");
        Socket s = ss.accept(); // Wait for connection

        // 2. Setup the "Pipe"
        // Read from your hard drive
        FileInputStream fis = new FileInputStream("send.txt"); 
        // Write to the network
        OutputStream os = s.getOutputStream(); 

        // 3. The Transfer Loop (The Magic)
        int b;
        while ((b = fis.read()) != -1) { // Read 1 byte from file
            os.write(b);                 // Write 1 byte to network
        }

        // 4. Cleanup
        System.out.println("File Sent!");
        fis.close();
        os.close();
        s.close();
    }
}
