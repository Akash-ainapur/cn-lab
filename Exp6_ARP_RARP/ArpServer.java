import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ArpServer {
    public static void main(String args[]) throws Exception {
        // 1. Open Port
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ARP Server is Ready...");

        // 2. Wait for connection
        Socket s = ss.accept();
        
        // 3. Setup Streams
        Scanner clientReader = new Scanner(s.getInputStream());
        PrintStream clientWriter = new PrintStream(s.getOutputStream());

        // 4. The "Database"
        String[] ips = {"192.168.0.1", "192.168.0.2"};
        String[] macs = {"AA:BB:CC", "DD:EE:FF"};

        // 5. Read Request
        String ipRequest = clientReader.nextLine(); // Client sends IP
        String macResponse = "Not Found"; // Default answer

        // 6. Search Logic
        for(int i=0; i<ips.length; i++) {
            if(ipRequest.equals(ips[i])) {
                macResponse = macs[i];
                break;
            }
        }

        // 7. Send Response
        clientWriter.println(macResponse);
        
        s.close();
    }
}
