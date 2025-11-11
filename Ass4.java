// Very Simple Program: Smart Traffic Management for Ambulance using Dijkstra’s Algorithm
// Author: Dhanu

public class Ass4{

    static final int V = 4; // number of intersections (nodes)

    // Function to find the node with the minimum distance
    static int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Dijkstra’s algorithm to find shortest travel times
    static void dijkstra(int graph[][], int src) {
        int dist[] = new int[V];       // stores shortest time from source
        boolean visited[] = new boolean[V]; // visited nodes

        // Initialize all distances as infinity
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Distance to source is always 0
        dist[src] = 0;

        // Find shortest paths for all nodes
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            // Update distance values for adjacent nodes
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && 
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the shortest travel times
        System.out.println("\nShortest travel time from ambulance (Node " + src + "):");
        for (int i = 0; i < V; i++) {
            System.out.println("To Node " + i + " : " + dist[i] + " minutes");
        }
    }

    public static void main(String[] args) {
        // Graph representation (matrix form)
        // 0 means no direct road between intersections
        int graph[][] = {
            {0, 5, 9, 0},
            {5, 0, 3, 7},
            {9, 3, 0, 2},
            {0, 7, 2, 0}
        };

        System.out.println("🚨 Smart Traffic Management System 🚨");
        System.out.println("Ambulance is at intersection (Node 0)");

        // Run Dijkstra’s Algorithm
        dijkstra(graph, 0);

        System.out.println("\n✅ Suggested quickest routes based on current traffic conditions.");
    }
}
