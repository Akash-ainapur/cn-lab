import java.util.Scanner;

public class CRC {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Input Data
        System.out.print("Enter message bits: ");
        String msg = sc.nextLine();
        System.out.print("Enter generator bits: ");
        String gen = sc.nextLine();
        
        // 2. Prepare Data Array (Message + Appended Zeros)
        int totalLen = msg.length() + gen.length() - 1;
        int[] data = new int[totalLen];
        int[] divisor = new int[gen.length()];
        
        // Fill data array
        for(int i=0; i<msg.length(); i++) data[i] = Integer.parseInt(msg.charAt(i)+"");
        for(int i=0; i<gen.length(); i++) divisor[i] = Integer.parseInt(gen.charAt(i)+"");
        
        // 3. CALCULATION (The Magic Loop)
        // We only loop up to msg.length because we don't process the padding
        for(int i=0; i<msg.length(); i++) {
            if(data[i] == 1) { // If bit is 1, perform XOR
                for(int j=0; j<divisor.length; j++) {
                    data[i+j] ^= divisor[j]; // The XOR Trick
                }
            }
        }
        
        // 4. Display Checksum (The remainder is now left in the 'data' array)
        System.out.print("Checksum code is: " + msg); // Original Msg
        for(int i=msg.length(); i<totalLen; i++) System.out.print(data[i]); // + Remainder
        System.out.println();
        
        // 5. RECEIVER SIDE (Verification)
        System.out.print("Enter received checksum code: ");
        msg = sc.nextLine(); // User types the output from step 4
        // Reset data array for receiver
        data = new int[msg.length() + gen.length() - 1]; 
        for(int i=0; i<msg.length(); i++) data[i] = Integer.parseInt(msg.charAt(i)+"");
        
        // REPEAT CALCULATION (Exact same loop)
        for(int i=0; i<msg.length() - (gen.length()-1); i++) {
            if(data[i] == 1) {
                for(int j=0; j<divisor.length; j++) {
                    data[i+j] ^= divisor[j];
                }
            }
        }
        
        // 6. Check Validity (If any bit is 1, error exists)
        boolean valid = true;
        for(int i=0; i<data.length; i++) {
            if(data[i] == 1) { valid = false; break; }
        }
        
        if(valid) System.out.println("Data Stream is Valid.");
        else System.out.println("Data Stream is Invalid (CRC Error).");
    }
}
