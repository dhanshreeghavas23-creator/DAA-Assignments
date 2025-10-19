import java.util.Scanner;

public class MultistageGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];
        System.out.println("Enter adjacency matrix (0 if no edge, weight otherwise):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        int source = 0;          // Usually first node
        int destination = n - 1; // Usually last node

        int[] cost = new int[n];   // cost[i] = min cost from i to destination
        int[] next = new int[n];   // next[i] = next vertex on shortest path

        // Initialize destination
        cost[destination] = 0;

        // Process vertices from second last to first
        for (int i = n - 2; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            int minVertex = -1;
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] > 0) {
                    int tempCost = graph[i][j] + cost[j];
                    if (tempCost < minCost) {
                        minCost = tempCost;
                        minVertex = j;
                    }
                }
            }
            cost[i] = minCost;
            next[i] = minVertex;
        }

        // Print shortest distance from source to destination
        System.out.println("Shortest distance from " + source + " to " + destination + " = " + cost[source]);

        // Print the path
        System.out.print("Path: ");
        int v = source;
        System.out.print(v);
        while (v != destination) {
            v = next[v];
            System.out.print(" -> " + v);
        }

        sc.close();
    }
}


// ------Sample Input/Output------

// Enter number of vertices: 5
// Enter adjacency matrix (0 if no edge, weight otherwise):
// 0 2 1 0 0
// 0 0 0 2 0
// 0 0 0 3 1
// 0 0 0 0 1
// 0 0 0 0 0
