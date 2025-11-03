//Dhanshree balaji ghavas
//123B1F026
//DATE:21/07/25
#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int partition(int ratings[], int low, int high) {
    int pivot = ratings[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
        if (ratings[j] < pivot) {
            i++;
            swap(&ratings[i], &ratings[j]);
        }
    }
    swap(&ratings[i + 1], &ratings[high]);
    return (i + 1);
}

void quickSort(int ratings[], int low, int high) {
    if (low < high) {
        int pi = partition(ratings, low, high);
        quickSort(ratings, low, pi - 1);
        quickSort(ratings, pi + 1, high);
    }
}

int main() {
    int movieRatings[] = {8, 5, 9, 3, 7, 10, 6, 4};
    int n = sizeof(movieRatings) / sizeof(movieRatings[0]);

    printf("Original Movie Ratings:\n");
    for (int i = 0; i < n; i++) {
        printf("%d ", movieRatings[i]);
    }

    quickSort(movieRatings, 0, n - 1);

    printf("\n\nSorted Movie Ratings (Personalized Ranking):\n");
    for (int i = 0; i < n; i++) {
        printf("%d ", movieRatings[i]);
    }

    printf("\n");
    return 0;
}

Original Movie Ratings:
8 5 9 3 7 10 6 4 

Sorted Movie Ratings (Personalized Ranking):
3 4 5 6 7 8 9 10
