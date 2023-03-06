/*
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each
 * of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change?
 * The order of coins doesn’t matter.
 * For example, for N = 4 and S = {1,2,3}, there are four solutions:
 * {1,1,1,1},{1,1,2},{2,2},{1,3}
 * So output should be 4.
 */

package dp.others;

public class NumOfSolutionsInfiniteCoinChange {
    public static int[] dp;

    public static int getNumOfSolutions(int sum, int[] coins)
    {
        dp = new int[sum+1];
        int n = coins.length;
        dp[0] = 1;
        for(int i = 0; i < n; i++)
        {
            for (int j = coins[i]; j < sum + 1; j++)
            {
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[sum];
    }
}
