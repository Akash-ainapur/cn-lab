import java.util.Scanner;

public class BellmanFord {
    public static void main(String[] args) {
        int MAX = 999; // Represents Infinity
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        
        int[][] adj = new int[n+1][n+1];
        System.out.println("Enter Adjacency Matrix:");
        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
                adj[i][j] = sc.nextInt(); // Enter 999 for no edge
        
        System.out.print("Enter Source Vertex: ");
        int source = sc.nextInt();
        
        // 1. Initialize Distances
        int[] dist = new int[n+1];
        for(int i=1; i<=n; i++) dist[i] = MAX;
        dist[source] = 0; // Distance to self is 0
        
        // 2. RELAXATION LOOP (Run V-1 times)
        // Loop 'k' runs n-1 times
        for(int k=1; k<=n-1; k++) {
            // Iterate over every edge (u -> v)
            for(int u=1; u<=n; u++) {
                for(int v=1; v<=n; v++) {
                    if(adj[u][v] != MAX) { // If edge exists
                        // The Relaxation Formula
                        if(dist[v] > dist[u] + adj[u][v]) {
                            dist[v] = dist[u] + adj[u][v];
                        }
                    }
                }
            }
        }
        
        // 3. Negative Cycle Check (Optional, but good for full marks)
        // Run one more time. If distance changes, there is a negative cycle.
        for(int u=1; u<=n; u++) {
            for(int v=1; v<=n; v++) {
                 if(adj[u][v] != MAX && dist[v] > dist[u] + adj[u][v]) {
                     System.out.println("Negative Edge Cycle Detected!");
                     return;
                 }
            }
        }
        
        // 4. Output
        for(int i=1; i<=n; i++) 
            System.out.println("Distance from " + source + " to " + i + " is " + dist[i]);
    }
}
