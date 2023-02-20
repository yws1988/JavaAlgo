/*
 * Given an array arr[] of size n, find the maximum sum of the array elements 
 * to make the sum <= k
 */
package collections.array;

public class SumOfArrayElementsLowerOrEqualThanK
{
    public static int getMax(int[] nums, int k)
    {
        int n = nums.length;
        var dp = new boolean[n][k + 1];

        for (int i = 0; i < n; i++)
        {
            dp[i][0] = true;
        }

        dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j <= k; j++)
            {
                dp[i][j] = dp[i][j] || dp[i - 1][j];
                if (j - nums[i] >= 0)
                {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        for (int i = k; i >= 0; i--) {
            if(dp[n-1][i]){
                return i;
            }
        }

        return 0;
    }
}