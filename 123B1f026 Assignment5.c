//Dhanshree balaji ghavas
//DATE: 25/09/2025
//PRN:123B1F026
#include <stdio.h>
#define INF 9999
#define N 4

int minCost(int cost[N][N], int mask, int pos, int dp[N][1<<N]) {
    if (mask == (1<<N) - 1)
        return cost[pos][0];
    if (dp[pos][mask] != -1)
        return dp[pos][mask];
    int ans = INF;
    for (int city = 0; city < N; city++) {
        if ((mask & (1<<city)) == 0) {
            int newAns = cost[pos][city] + minCost(cost, mask | (1<<city), city, dp);
            if (newAns < ans)
                ans = newAns;
        }
    }
    dp[pos][mask] = ans;
    return ans;
}

int main() {
    int cost[N][N] = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };
    int dp[N][1<<N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < (1<<N); j++)
            dp[i][j] = -1;

    int result = minCost(cost, 1, 0, dp);
    printf("Minimum Delivery Cost Path: %d\n", result);
    return 0;
}

output:
Minimum Delivery Cost Path: 80
