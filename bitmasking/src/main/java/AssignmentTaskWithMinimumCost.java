// There are N persons and N tasks, each task is to be alloted to a single person.
// We are also given a matrix of size N* N, where cost[i, j] denotes the cost when person i is assigned to task j
// Get the minimum cost if every person takes one task

import utils.BitOperation;

public class AssignmentTaskWithMinimumCost {
    public static int getMinimumCost(int[][] costs)
    {
        int n = costs.length;
        int max = 1 << n;
        int[] dp = new int[max];
        for (int i = 0; i < max; i++)
        {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for (int i = 0; i < max; i++)
        {
            int k = BitOperation.countBit(i);
            for (int j = 0; j < n; j++)
            {
                if((i & (1 << j)) == 0)
                {
                    dp[i | (1 << j)] = Math.min(dp[i | (1 << j)], dp[i]+costs[k][j]);
                }
            }
        }

        return dp[max-1];
    }
}
