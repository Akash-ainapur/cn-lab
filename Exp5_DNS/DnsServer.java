import java.net.*;

public class DnsServer {
    public static void main(String args[]) throws Exception {
        // 1. UDP Socket (Port 1362 is used in the manual, but any port works)
        DatagramSocket ds = new DatagramSocket(1362);
        System.out.println("DNS Server Ready...");

        // 2. The "Phonebook" Data
        String[] hosts = {"yahoo.com", "gmail.com", "google.com", "facebook.com"};
        String[] ip = {"68.180.206.184", "209.85.148.19", "8.8.8.8", "69.63.189.16"};

        while(true) {
            // 3. Receive Request
            byte[] receiveBuf = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(receiveBuf, 1024);
            ds.receive(dpReceive);
            
            String reqDomain = new String(dpReceive.getData()).trim(); // Client asks for this domain
            
            // 4. Search Logic (Linear Search)
            String response = "Host Not Found";
            for(int i=0; i<hosts.length; i++) {
                if(reqDomain.equalsIgnoreCase(hosts[i])) {
                    response = ip[i]; // Found the IP!
                    break;
                }
            }

            // 5. Send Response (Extract IP/Port from sender)
            InetAddress clientIp = dpReceive.getAddress();
            int clientPort = dpReceive.getPort();
            
            byte[] sendBuf = response.getBytes();
            DatagramPacket dpSend = new DatagramPacket(sendBuf, sendBuf.length, clientIp, clientPort);
            ds.send(dpSend);
        }
    }
}
