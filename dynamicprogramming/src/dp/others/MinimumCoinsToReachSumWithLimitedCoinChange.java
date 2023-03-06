/*
 * Given a value N, if we want to make change for N cents, and we have limited supply {N1, N2, .. , Nm} of each
 * of S = { S1, S2, .. , Sm} valued coins, what is the minimum number of coins we need to give N?
 * For example, for N = 4 and N = {3,2,1} S = {1,2,3}, there are three solutions:
 * {1,1,2},{2,2},{1,3}
 * So output should be 2.
 */
package dp.others;

import java.util.Arrays;

public class MinimumCoinsToReachSumWithLimitedCoinChange {
    public static int[][] dp;

    public static int getMinimumNumOfCoins(int sum, int[] nums, int[] coins)
    {
        int totalNum = Arrays.stream(nums).sum();
        int[] values = new int[totalNum];
        int idx = 0;
        for (int i = 0; i < nums.length; i++)
        {
            for (int j = 0; j < nums[i]; j++)
            {
                values[idx] = coins[i];
                idx++;
            }
        }

        dp = new int[sum+1][totalNum];

        for (int i = 0; i <= sum; i++)
        {
            for (int j = 0; j < totalNum; j++)
            {
                if (i == 0)
                {
                    dp[i][j] = 0;
                }
                else
                {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        if (values[0] <= sum)
        {
            dp[values[0]][0] = 1;
        }

        for(int i = 1; i <= sum; i++)
        {
            for (int j = 1; j < totalNum; j++)
            {
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);

                if (i >= values[j] && dp[i - values[j]][j - 1] != Integer.MAX_VALUE)
                {
                    dp[i][j] = Math.min(dp[i][j], dp[i - values[j]][j - 1]+1);
                }
            }
        }

        return dp[sum][totalNum-1];
    }

}
