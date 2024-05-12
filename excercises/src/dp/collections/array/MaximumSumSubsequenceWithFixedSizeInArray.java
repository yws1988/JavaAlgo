package dp.collections.array;
/*
Given an array with n elements, given an integer size defined the maximum subsequence size.
Given an integer interval which means each interval continuous elements, you can do the two
following operations:
Add the element array[i] into the sum of subsequence
Add the sum of elements array[i]+array[i+1] into the sum of subsequence

Each index of the elements in the sequence has a difference of interval

Calculate the maximum sum of the subsequence with size equal or less than size.
 */


public class MaximumSumSubsequenceWithFixedSizeInArray {
    public static long getMaximumSum(int[] array, int size, int interval)
    {
        int n = array.length;

        long[][] dp = new long[n][size + 1];

        for (int i = 0; i < interval; i++)
        {
            for (int j = 1; j <= size; j++)
            {
                dp[i][j] = Math.max(dp[i][j], array[i]);

                if (i>0)
                {
                    if(j >= 2)
                    {
                        dp[i][j] = Math.max(dp[i][j], array[i] + array[i - 1]);
                    }

                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }

        for (int i = interval; i < n; i++)
        {
            long sum = array[i] + array[i - 1];
            for (int j = 1; j <= size; j++)
            {
                dp[i][j] = Math.max(dp[i][j], dp[i - interval - 1][j - 1] + array[i]);

                if (j >= 2)
                {
                    dp[i][j] = Math.max(dp[i][j], i==interval+1 ? sum : dp[i - interval - 2][j - 2] + sum);
                }

                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        return dp[n-1][size];
    }
}
