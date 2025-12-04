import java.net.*;

public class RarpServer {
    public static void main(String args[]) throws Exception {
        // 1. UDP Socket (No ServerSocket!)
        DatagramSocket ds = new DatagramSocket(7777);
        
        System.out.println("RARP Server (UDP) Ready...");

        String[] macs = {"AA:BB:CC", "DD:EE:FF"};
        String[] ips = {"192.168.0.1", "192.168.0.2"};

        while(true) {
            // 2. Prepare empty packet to receive data
            byte[] receiveBuf = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(receiveBuf, 1024);
            
            // 3. Receive
            ds.receive(dpReceive);
            String macRequest = new String(dpReceive.getData()).trim(); // .trim() is important!
            
            // 4. Search Logic (Same as TCP)
            String response = "Not Found";
            for(int i=0; i<macs.length; i++) {
                if(macRequest.equalsIgnoreCase(macs[i])) {
                    response = ips[i];
                    break;
                }
            }

            // 5. Send Reply (The "Hard" Part of UDP)
            // You must extract the Client's IP and Port from the received packet
            InetAddress clientIp = dpReceive.getAddress();
            int clientPort = dpReceive.getPort();
            
            byte[] sendBuf = response.getBytes();
            DatagramPacket dpSend = new DatagramPacket(sendBuf, sendBuf.length, clientIp, clientPort);
            
            ds.send(dpSend);
        }
    }
}
