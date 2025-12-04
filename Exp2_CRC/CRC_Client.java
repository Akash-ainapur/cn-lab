import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CRC_Client {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 5000);
        Scanner sc = new Scanner(System.in);

        // Setup Streams
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream writer = new PrintStream(s.getOutputStream());

        while(true) {
            System.out.println("\n--- NEW TRANSMISSION ---");
            System.out.print("Enter Message (or 'exit'): ");
            String msg = sc.nextLine();
            if(msg.equalsIgnoreCase("exit")) {
                writer.println("exit");
                break;
            }

            System.out.print("Enter Generator: ");
            String gen = sc.nextLine();

            // 1. Calculate CRC (Sender Logic) to get Encoded String
            String encodedData = calculateCRC(msg, gen);
            System.out.println("Sending Encoded Data: " + encodedData);

            // 2. Send Data AND Generator to Server
            // We send two separate lines so the server knows which is which
            writer.println(encodedData);
            writer.println(gen); 

            // 3. Wait for Server Response
            String result = reader.readLine();
            System.out.println("Server Says: " + result);
        }
        s.close();
    }

    // --- The Logic Helper (Sender Side) ---
    static String calculateCRC(String msg, String gen) {
        int totalLen = msg.length() + gen.length() - 1;
        int[] data = new int[totalLen];
        int[] divisor = new int[gen.length()];

        // Fill Data (Message + Padding)
        for(int i=0; i<msg.length(); i++) data[i] = Integer.parseInt(msg.charAt(i)+"");
        for(int i=0; i<gen.length(); i++) divisor[i] = Integer.parseInt(gen.charAt(i)+"");

        // XOR Logic
        for(int i=0; i<msg.length(); i++) {
            if(data[i] == 1) {
                for(int j=0; j<divisor.length; j++) {
                    data[i+j] ^= divisor[j];
                }
            }
        }

        // Return Original Message + Remainder
        String checksum = "";
        for(int i=msg.length(); i<totalLen; i++) checksum += data[i];
        
        return msg + checksum;
    }
}
