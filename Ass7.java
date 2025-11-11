// Very Simple Program: University Timetable Scheduling using Graph Coloring
// Author: Dhanu

public class Ass7 {

    public static void main(String[] args) {
        // Number of courses
        int n = 5;

        // Graph showing conflicts between courses (1 = same student in both)
        int[][] graph = {
            {0, 1, 1, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 0},
            {0, 1, 1, 0, 1},
            {0, 0, 0, 1, 0}
        };

        // Array to store slot assigned to each course
        int[] slot = new int[n];
        slot[0] = 0; // First course gets first slot

        // Assign slots to remaining courses
        for (int i = 1; i < n; i++) {
            boolean[] used = new boolean[n]; // To track used slots for adjacent courses

            // Check adjacent (conflicting) courses
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1 && slot[j] != -1) {
                    used[slot[j]] = true;
                }
            }

            // Find the first available slot
            int cr;
            for (cr = 0; cr < n; cr++) {
                if (!used[cr]) break;
            }

            slot[i] = cr; // Assign slot
        }

        // Print result
        System.out.println("📅 University Exam Timetable:");
        for (int i = 0; i < n; i++) {
            System.out.println("Course " + i + " → Slot " + slot[i]);
        }

        // Find total slots used
        int maxSlot = 0;
        for (int s : slot) if (s > maxSlot) maxSlot = s;
        System.out.println("\n✅ Minimum slots required: " + (maxSlot + 1));
    }
}
