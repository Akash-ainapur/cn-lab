import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String args[]) throws Exception {
        // 1. Connect
        Socket s = new Socket("localhost", 5000);

        // 2. Setup the "Pipe"
        // Read from the network
        InputStream is = s.getInputStream(); 
        // Write to your hard drive
        FileOutputStream fos = new FileOutputStream("receive.txt");

        // 3. The Transfer Loop
        int b;
        while ((b = is.read()) != -1) { // Read 1 byte from network
            fos.write(b);               // Write 1 byte to file
        }

        // 4. Cleanup
        System.out.println("File Received!");
        fos.close();
        is.close();
        s.close();
    }
}
