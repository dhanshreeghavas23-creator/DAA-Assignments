// Simple QuickSort Program for Sorting Indian Movies
// Author: Dhanu

class Movie {
    String name;
    double rating;

    Movie(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }
}

public class Ass2 {

    // Function to swap movies
    static void swap(Movie[] movies, int i, int j) {
        Movie temp = movies[i];
        movies[i] = movies[j];
        movies[j] = temp;
    }

    // Partition function (core logic of QuickSort)
    static int partition(Movie[] movies, int low, int high) {
        double pivot = movies[high].rating;  // choose last movie's rating as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (movies[j].rating > pivot) {  // sorting in descending order
                i++;
                swap(movies, i, j);
            }
        }

        swap(movies, i + 1, high);
        return i + 1;
    }

    // QuickSort recursive function
    static void quickSort(Movie[] movies, int low, int high) {
        if (low < high) {
            int pi = partition(movies, low, high);

            quickSort(movies, low, pi - 1);   // sort left part
            quickSort(movies, pi + 1, high);  // sort right part
        }
    }

    public static void main(String[] args) {
        // Sample Indian movies
        Movie[] movies = {
            new Movie("3 Idiots", 8.4),
            new Movie("Dangal", 8.3),
            new Movie("KGF Chapter 2", 8.5),
            new Movie("RRR", 8.0),
            new Movie("Pathaan", 7.0),
            new Movie("Jawan", 7.5),
            new Movie("Baahubali 2", 8.2)
        };

        System.out.println("Before Sorting (by rating):");
        for (Movie m : movies)
            System.out.println(m.name + " - Rating: " + m.rating);

        quickSort(movies, 0, movies.length - 1);

        System.out.println("\nAfter Sorting (by rating):");
        for (Movie m : movies)
            System.out.println(m.name + " - Rating: " + m.rating);
    }
}
