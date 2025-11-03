#include <stdio.h>
#define N 4
#define INF 9999

int finalPath[N + 1];
int visited[N];
int finalRes = INF;

void copyToFinal(int currPath[]) {
    for (int i = 0; i < N; i++)
        finalPath[i] = currPath[i];
    finalPath[N] = currPath[0];
}

int firstMin(int cost[N][N], int i) {
    int min = INF;
    for (int k = 0; k < N; k++)
        if (cost[i][k] < min && i != k)
            min = cost[i][k];
    return min;
}

int secondMin(int cost[N][N], int i) {
    int first = INF, second = INF;
    for (int j = 0; j < N; j++) {
        if (i == j)
            continue;
        if (cost[i][j] <= first) {
            second = first;
            first = cost[i][j];
        } else if (cost[i][j] <= second && cost[i][j] != first)
            second = cost[i][j];
    }
    return second;
}

void TSPRec(int cost[N][N], int currBound, int currWeight, int level, int currPath[]) {
    if (level == N) {
        if (cost[currPath[level - 1]][currPath[0]] != 0) {
            int currRes = currWeight + cost[currPath[level - 1]][currPath[0]];
            if (currRes < finalRes) {
                copyToFinal(currPath);
                finalRes = currRes;
            }
        }
        return;
    }

    for (int i = 0; i < N; i++) {
        if (cost[currPath[level - 1]][i] != 0 && visited[i] == 0) {
            int temp = currBound;
            currWeight += cost[currPath[level - 1]][i];
            if (level == 1)
                currBound -= ((firstMin(cost, currPath[level - 1]) + firstMin(cost, i)) / 2);
            else
                currBound -= ((secondMin(cost, currPath[level - 1]) + firstMin(cost, i)) / 2);
            if (currBound + currWeight < finalRes) {
                currPath[level] = i;
                visited[i] = 1;
                TSPRec(cost, currBound, currWeight, level + 1, currPath);
            }
            currWeight -= cost[currPath[level - 1]][i];
            currBound = temp;
            for (int j = 0; j < N; j++)
                visited[j] = 0;
            for (int j = 0; j <= level - 1; j++)
                visited[currPath[j]] = 1;
        }
    }
}

void TSP(int cost[N][N]) {
    int currPath[N + 1];
    int currBound = 0;
    for (int i = 0; i < N; i++)
        currBound += (firstMin(cost, i) + secondMin(cost, i));
    currBound = (currBound & 1) ? currBound / 2 + 1 : currBound / 2;
    visited[0] = 1;
    currPath[0] = 0;
    TSPRec(cost, currBound, 0, 1, currPath);
}

int main() {
    int cost[N][N] = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };
    TSP(cost);
    printf("Minimum Delivery Route Cost: %d\n", finalRes);
    printf("Optimal Route: ");
    for (int i = 0; i <= N; i++)
        printf("%d ", finalPath[i]);
    printf("\n");
    return 0;
}

OUTPUT:
Minimum Delivery Route Cost: 80
Optimal Route: 0 1 3 2 0
