/// Given two integer arrays val[0..n-1] and wt[0..n-1] that represent values and weights associated with n items
/// respectively. Find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal
/// to Knapsack capacity cap.

package others;

public class MaximumValueToReachAtMostWeight {
    public static int getMaxValue(Pair[] arr, int capacity)
    {
        int n = arr.length;

        int[][] dp = new int[capacity + 1][n];

        for (int i = 1; i <= capacity; i++)
        {
            if (i >= arr[0].weight)
            {
                dp[i][0] = arr[0].value;
            }
        }

        for (int i = 1; i <= capacity; i++)
        {
            for (int j = 1; j < n; j++)
            {
                if (arr[j].weight <= i)
                {
                    dp[i][j] = Math.max(dp[i][j], dp[i - arr[j].weight][j - 1] + arr[j].value);
                }

                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
            }
        }

        return dp[capacity][n - 1];
    }

    public static class Pair{
        public int weight;
        public int value;

        public Pair(int x, int y) {
            this.weight = x;
            this.value = y;
        }
    }
}
