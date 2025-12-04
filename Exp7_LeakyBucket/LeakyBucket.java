import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Configuration
        System.out.print("Enter Bucket Capacity: ");
        int capacity = sc.nextInt();
        System.out.print("Enter Output Rate: ");
        int rate = sc.nextInt();
        System.out.print("Enter number of packets: ");
        int n = sc.nextInt();
        
        int[] packets = new int[n];
        System.out.println("Enter packet sizes:");
        for(int i=0; i<n; i++) packets[i] = sc.nextInt();
        
        int currentStorage = 0;
        
        // 2. Simulation Loop
        System.out.println("Time\tPacket\tState\tSent\tRemaining");
        for(int i=0; i<n; i++) {
            int incoming = packets[i];
            
            // Add packet to bucket
            if(currentStorage + incoming > capacity) {
                System.out.println((i+1) + "\t" + incoming + "\tDrop\t0\t" + currentStorage);
            } else {
                currentStorage += incoming;
                
                // Send Logic (Leak)
                int sent = 0;
                if(currentStorage >= rate) {
                    sent = rate;
                    currentStorage -= rate;
                } else {
                    sent = currentStorage; // Send whatever is left
                    currentStorage = 0;
                }
                
                System.out.println((i+1) + "\t" + incoming + "\tAccept\t" + sent + "\t" + currentStorage);
            }
        }
    }
}
