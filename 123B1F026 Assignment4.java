import java.util.*;

class Edge {
    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Graph {
    int V; // Number of nodes
    List<List<Edge>> adj;

    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Edge(v, weight));
        adj.get(v).add(new Edge(u, weight)); // Assuming bidirectional roads
    }

    // Update edge weight dynamically
    void updateEdgeWeight(int u, int v, int newWeight) {
        for (Edge e : adj.get(u)) {
            if (e.to == v) e.weight = newWeight;
        }
        for (Edge e : adj.get(v)) { // For bidirectional edge
            if (e.to == u) e.weight = newWeight;
        }
    }

    // Dijkstra's algorithm
    void dijkstra(int src, Set<Integer> hospitals) {
        int[] dist = new int[V];
        int[] parent = new int[V]; // To store path
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];

            if (d > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.to, w = edge.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        // Find nearest hospital
        int minDist = Integer.MAX_VALUE;
        int nearestHospital = -1;
        for (int h : hospitals) {
            if (dist[h] < minDist) {
                minDist = dist[h];
                nearestHospital = h;
            }
        }

        if (nearestHospital == -1) {
            System.out.println("No hospital reachable!");
            return;
        }

        System.out.println("Nearest hospital: Node " + nearestHospital);
        System.out.println("Shortest travel time: " + minDist + " minutes");

        // Print path
        List<Integer> path = new ArrayList<>();
        for (int at = nearestHospital; at != -1; at = parent[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.println("Optimal path: " + path);
    }
}

public class SmartTrafficManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of intersections (nodes): ");
        int V = sc.nextInt();
        Graph g = new Graph(V);

        System.out.print("Enter number of roads: ");
        int E = sc.nextInt();

        System.out.println("Enter road details (u v travelTime):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(u, v, w);
        }

        System.out.print("Enter ambulance source node: ");
        int src = sc.nextInt();

        System.out.print("Enter number of hospitals: ");
        int hCount = sc.nextInt();
        Set<Integer> hospitals = new HashSet<>();
        System.out.println("Enter hospital node indices:");
        for (int i = 0; i < hCount; i++) {
            hospitals.add(sc.nextInt());
        }

        // Run Dijkstra
        g.dijkstra(src, hospitals);

        // Example: Dynamic weight update (traffic changes)
        System.out.println("\nUpdate traffic on road (u v newTravelTime):");
        System.out.println("Enter u v newWeight:");
        int u = sc.nextInt();
        int v = sc.nextInt();
        int newW = sc.nextInt();
        g.updateEdgeWeight(u, v, newW);

        System.out.println("Recalculating shortest path after traffic update...");
        g.dijkstra(src, hospitals);

        sc.close();
    }
}

// Sample Input/Output:
// Enter number of intersections (nodes): 6
// Enter number of roads: 7
// Enter road details (u v travelTime):
// 0 1 4
// 0 2 2
// 1 2 5
// 1 3 10
// 2 4 3
// 4 3 4
// 3 5 11
// Enter ambulance source node: 0
// Enter number of hospitals: 2
// Enter hospital node indices:
// 3
// 5

// Update traffic on road (u v newTravelTime):
// Enter u v newWeight:
// 2 4 6
