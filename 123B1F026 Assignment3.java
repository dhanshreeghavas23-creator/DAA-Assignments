import java.util.*;

class Item {
    double value, weight;
    boolean divisible;

    Item(double value, double weight, boolean divisible) {
        this.value = value;
        this.weight = weight;
        this.divisible = divisible;
    }
}

public class FractionalKnapsack {

    // Function to calculate maximum total utility value
    public static double getMaxValue(List<Item> items, double capacity) {
        // Sort items by value-to-weight ratio in descending order
        items.sort((a, b) -> Double.compare(b.value / b.weight, a.value / a.weight));

        double totalValue = 0.0;
        double currentWeight = 0.0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // Take the whole item
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                // Take fraction only if item is divisible
                if (item.divisible) {
                    double remaining = capacity - currentWeight;
                    totalValue += (item.value / item.weight) * remaining;
                    currentWeight += remaining;
                }
                // Capacity full — stop loading
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total weight capacity of the boat (kg): ");
        double W = sc.nextDouble();

        System.out.print("Enter number of relief items: ");
        int n = sc.nextInt();

        List<Item> items = new ArrayList<>();

        // Taking input for each item
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for item " + (i + 1) + ":");
            System.out.print("Utility value: ");
            double value = sc.nextDouble();
            System.out.print("Weight (kg): ");
            double weight = sc.nextDouble();
            System.out.print("Is the item divisible? (1 = Yes, 0 = No): ");
            int div = sc.nextInt();
            boolean divisible = (div == 1);

            items.add(new Item(value, weight, divisible));
        }

        double maxValue = getMaxValue(items, W);
        System.out.printf("\nMaximum total utility value that can be carried: %.2f\n", maxValue);

        sc.close();
    }
}

// ------Sample Input/Output------

// Enter total weight capacity of the boat (kg): 50
// Enter number of relief items: 3

// Enter details for item 1:
// Utility value: 60
// Weight (kg): 10
// Is the item divisible? (1 = Yes, 0 = No): 1

// Enter details for item 2:
// Utility value: 100
// Weight (kg): 20
// Is the item divisible? (1 = Yes, 0 = No): 0

// Enter details for item 3:
// Utility value: 120
// Weight (kg): 30
// Is the item divisible? (1 = Yes, 0 = No): 1
