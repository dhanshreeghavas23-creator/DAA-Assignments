// Simple Program: University Timetable Scheduling using Graph Coloring
// Author: Dhanu

public class Ass8 {

    public static void main(String[] args) {
        // Number of courses
        int n = 5;

        // Graph showing conflicts between courses
        // 1 means the same student is enrolled in both courses
        int[][] graph = {
            {0, 1, 1, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 0},
            {0, 1, 1, 0, 1},
            {0, 0, 0, 1, 0}
        };

        // Room names available for each slot
        String[] rooms = {"Room A", "Room B", "Room C"};

        // To store the slot assigned to each course
        int[] slot = new int[n];
        slot[0] = 0;  // first course → first slot

        // Assign slots to remaining courses
        for (int i = 1; i < n; i++) {
            boolean[] used = new boolean[n]; // track used slots by adjacent (conflicting) courses

            // check for conflicts
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1 && slot[j] != -1) {
                    used[slot[j]] = true;
                }
            }

            // assign the first available slot (color)
            int cr;
            for (cr = 0; cr < n; cr++) {
                if (!used[cr]) break;
            }
            slot[i] = cr;
        }

        // Print timetable with slot and room
        System.out.println("📅 UNIVERSITY EXAM TIMETABLE");
        System.out.println("-------------------------------");

        for (int i = 0; i < n; i++) {
            String room = rooms[slot[i] % rooms.length]; // assign room based on slot number
            System.out.println("Course " + i + " → Slot " + slot[i] + " → " + room);
        }

        // find minimum slots used
        int maxSlot = 0;
        for (int s : slot)
            if (s > maxSlot) maxSlot = s;

        System.out.println("\n✅ Minimum Exam Slots Needed: " + (maxSlot + 1));
    }
}
