import java.util.*;

public class Ass5 {
    static void findMinCost(Map<Integer, List<int[]>> graph, int source, int destination) {

        // Step 1: Find maximum node number (for array sizing)
        int n = 6; // Since we know nodes range from 1 to 6

        
        int[] dist = new int[n + 1];
        int[] path = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);

        dist[destination] = 0; // Distance to destination = 0

        // Step 2: Dynamic Programming - Backward traversal
        for (int i = destination - 1; i >= source; i--) {
            if (graph.containsKey(i)) {
                for (int[] edge : graph.get(i)) {
                    int next = edge[0];
                    int cost = edge[1];
                    if (dist[next] != Integer.MAX_VALUE && cost + dist[next] < dist[i]) {
                        dist[i] = cost + dist[next];
                        path[i] = next;
                    }
                }
            }
        }

        // Step 3: Reconstruct optimal route
        List<Integer> route = new ArrayList<>();
        int node = source;
        route.add(node);
        while (path[node] != -1) {
            node = path[node];
            route.add(node);
            
        }

    
        System.out.println("--------------------------------------------------");
        System.out.println("Optimal Delivery Route and Cost for SwiftCargo");
        System.out.println("--------------------------------------------------");
        System.out.println("Minimum Delivery Cost: " + dist[source]);
        System.out.print("Optimal Route: ");
        for (int i = 0; i < route.size(); i++) {
            System.out.print(route.get(i));
           // if (i < route.size() - 1) System.out.print(" -> ");
        }
        System.out.println("\n--------------------------------------------------");
    }

    public static void main(String[] args) {

        // Step 1: Define the multistage graph
        // Stage 1: Warehouses
        // Stage 2: Transit Hubs
        // Stage 3: Final Delivery Points

        Map<Integer, List<int[]>> graph = new HashMap<>();

        // Warehouses → Hubs
        graph.put(1, Arrays.asList(new int[]{3, 4}, new int[]{4, 6})); // Pune
        graph.put(2, Arrays.asList(new int[]{3, 5}, new int[]{4, 3})); // Mumbai

        // Hubs → Final Delivery Points
        graph.put(3, Arrays.asList(new int[]{5, 4}, new int[]{6, 7})); // Nagpur
        graph.put(4, Arrays.asList(new int[]{5, 6}, new int[]{6, 5})); // Indore

        // Step 2: Run for both warehouses
        findMinCost(graph, 1, 5); // Pune → Delhi
        //findMinCost(graph, 2, 6); // Mumbai → Kolkata
    }
}