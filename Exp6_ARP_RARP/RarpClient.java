import java.net.*;
import java.util.Scanner;

public class RarpClient {
    public static void main(String args[]) throws Exception {
        DatagramSocket ds = new DatagramSocket(); // No port needed for client
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter MAC: ");
        String mac = sc.nextLine();

        // 1. Send Packet
        byte[] sendBuf = mac.getBytes();
        InetAddress serverIp = InetAddress.getLocalHost();
        DatagramPacket dpSend = new DatagramPacket(sendBuf, sendBuf.length, serverIp, 7777);
        ds.send(dpSend);

        // 2. Receive Packet
        byte[] receiveBuf = new byte[1024];
        DatagramPacket dpReceive = new DatagramPacket(receiveBuf, 1024);
        ds.receive(dpReceive);

        String response = new String(dpReceive.getData()).trim();
        System.out.println("IP Address is: " + response);
        
        ds.close();
    }
}
