// Very Simple Program: Emergency Relief Supply using Fractional Knapsack
// Author: Dhanu

public class Ass3 {

    public static void main(String[] args) {
        // Boat maximum capacity
        double capacity = 50;

        // Items: name, weight, value
        String[] name = {"Medicine", "Food", "Water"};
        double[] weight = {10, 40, 20};
        double[] value = {200, 100, 60};
        boolean[] divisible = {false, true, true}; // can we take part?

        double totalValue = 0, totalWeight = 0;

        System.out.println("Boat Capacity: " + capacity + " kg\n");
        System.out.println("Items selected for transport:");

        // Loop through each item
        for (int i = 0; i < name.length; i++) {
            if (totalWeight + weight[i] <= capacity) {
                // Take the whole item
                totalWeight += weight[i];
                totalValue += value[i];
                System.out.println(name[i] + " (Full) - Weight: " + weight[i] + " kg, Value: " + value[i]);
            } else {
                // Take part of it if divisible
                double remaining = capacity - totalWeight;
                if (divisible[i] && remaining > 0) {
                    double fraction = remaining / weight[i];
                    totalValue += value[i] * fraction;
                    System.out.printf("%s (%.0f%% taken) - Weight: %.1f kg, Value: %.1f\n",
                            name[i], fraction * 100, remaining, value[i] * fraction);
                    totalWeight += remaining;
                }
                break; // Boat full
            }
        }

        System.out.println("\nTotal Weight Loaded: " + totalWeight + " kg");
        System.out.println("Total Utility Value: " + totalValue);
    }
}
