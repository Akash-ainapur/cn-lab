import java.net.*;
import java.util.Scanner;

public class DnsClient {
    public static void main(String args[]) throws Exception {
        DatagramSocket ds = new DatagramSocket(); // Client needs no specific port
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Hostname (e.g., google.com): ");
        String hostname = sc.nextLine();

        // 1. Send Request
        byte[] sendBuf = hostname.getBytes();
        InetAddress serverIp = InetAddress.getLocalHost(); // Server is on same machine
        // Send to Server Port 1362
        DatagramPacket dpSend = new DatagramPacket(sendBuf, sendBuf.length, serverIp, 1362);
        ds.send(dpSend);

        // 2. Receive Response
        byte[] receiveBuf = new byte[1024];
        DatagramPacket dpReceive = new DatagramPacket(receiveBuf, 1024);
        ds.receive(dpReceive);

        String ip = new String(dpReceive.getData()).trim();
        System.out.println("IP Address: " + ip);
        
        ds.close();
    }
}
