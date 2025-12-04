import java.io.*;
import java.net.*;

public class CRC_Server {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("CRC Validator Server Ready...");
        Socket s = ss.accept(); // 1. Connection Established

        // 2. Setup Streams
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream writer = new PrintStream(s.getOutputStream());

        // 3. The Conversation Loop
        while(true) {
            // A. Wait for Encoded Data from Client
            String receivedData = reader.readLine();
            
            // Exit condition
            if (receivedData == null || receivedData.equalsIgnoreCase("exit")) break;

            System.out.println("Received Code: " + receivedData);

            // B. Wait for Generator (or hardcode it if you prefer)
            String generator = reader.readLine(); 
            System.out.println("Using Generator: " + generator);

            // C. Perform CRC Check (Receiver Logic)
            boolean isValid = checkCRC(receivedData, generator);

            // D. Send Result back to Client
            if(isValid) writer.println("SUCCESS: Data is Valid.");
            else writer.println("ERROR: Corrupted Data detected.");
        }
        s.close();
    }

    // --- The Logic Helper (Copied from your Exp 2) ---
    static boolean checkCRC(String msg, String gen) {
        int[] data = new int[msg.length()];
        int[] divisor = new int[gen.length()];

        // Convert String to Int Array
        for(int i=0; i<msg.length(); i++) data[i] = Integer.parseInt(msg.charAt(i)+"");
        for(int i=0; i<gen.length(); i++) divisor[i] = Integer.parseInt(gen.charAt(i)+"");

        // XOR Logic
        for(int i=0; i < msg.length() - (gen.length() - 1); i++) {
            if(data[i] == 1) {
                for(int j=0; j<divisor.length; j++) {
                    data[i+j] ^= divisor[j];
                }
            }
        }
        
        // Check for Remainder
        for(int i=0; i<data.length; i++) {
            if(data[i] == 1) return false; // Found a remainder -> Invalid
        }
        return true;
    }
}
