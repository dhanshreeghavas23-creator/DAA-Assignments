// Simple Program: Disaster Relief Resource Allocation using 0/1 Knapsack (Dynamic Programming)
// Author: Dhanu

public class Ass6 {

    // Function to solve 0/1 Knapsack problem
    static int knapsack(int W, int wt[], int val[], int n) {
        int dp[][] = new int[n + 1][W + 1]; // dp[i][w] = max value using first i items and capacity w

        // Build table dp[][] in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    // Option 1: include the item
                    // Option 2: exclude the item
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    // If weight exceeds limit, exclude item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W]; // maximum value in the last cell
    }

    public static void main(String[] args) {
        // Example items: (weight in kg, utility value)
        int wt[] = {10, 20, 30, 15};  // weights of items
        int val[] = {60, 100, 120, 90}; // utility values
        String items[] = {"Medicines", "Food Packets", "Water Bottles", "Blankets"};

        int W = 50; // truck capacity
        int n = wt.length;

        System.out.println("🚑 Disaster Relief Resource Allocation");
        System.out.println("-------------------------------------");
        System.out.println("Truck Capacity: " + W + " kg");
        System.out.println("Available Items:");
        for (int i = 0; i < n; i++) {
            System.out.println(items[i] + " - Weight: " + wt[i] + " kg, Utility: " + val[i]);
        }

        int maxUtility = knapsack(W, wt, val, n);

        System.out.println("\n✅ Maximum Utility Value that can be carried: " + maxUtility);
    }
}
