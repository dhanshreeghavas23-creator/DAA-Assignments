// Easy Program to Sort Customer Orders using Merge Sort

class Order {
    String id;
    long time;   // order timestamp

    Order(String id, long time) {
        this.id = id;
        this.time = time;
    }
}

public class Ass1 {

    // Merge two sorted parts of the array
    static void merge(Order arr[], int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        Order leftPart[] = new Order[n1];
        Order rightPart[] = new Order[n2];

        // Copy data
        for (int i = 0; i < n1; i++)
            leftPart[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightPart[j] = arr[mid + 1 + j];

        // Merge logic
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftPart[i].time <= rightPart[j].time) {
                arr[k] = leftPart[i];
                i++;
            } else {
                arr[k] = rightPart[j];
                j++;
            }
            k++;
        }

        // Copy leftover elements
        while (i < n1) {
            arr[k] = leftPart[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightPart[j];
            j++;
            k++;
        }
    }

    // Recursive Merge Sort
    static void mergeSort(Order arr[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort both halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge sorted halves
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        // Sample Orders
        Order orders[] = {
            new Order("A101", 1700000000L),
            new Order("A105", 1700000050L),
            new Order("A102", 1699999999L),
            new Order("A104", 1700000030L),
            new Order("A103", 1700000010L)
        };

        System.out.println("Before Sorting (by time):");
        for (Order o : orders)
            System.out.println(o.id + " - " + o.time);

        // Sort using Merge Sort
        mergeSort(orders, 0, orders.length - 1);

        System.out.println("\nAfter Sorting (by time):");
        for (Order o : orders)
            System.out.println(o.id + " - " + o.time);
    }
}
