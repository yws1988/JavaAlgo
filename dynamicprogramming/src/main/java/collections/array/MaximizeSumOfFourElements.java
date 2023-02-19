/*
 * Maximize arr[j] – arr[i] + arr[l] – arr[k], such that i < j < k < l.
 * Find the maximum value of arr[j] – arr[i] + arr[l] – arr[k], such that i < j < k < l
 * Array {4, 8, 9, 2, 20}
 * Then the maximum such value is 23 (9 - 4 + 20 - 2)
 */
package collections.array;

public class MaximizeSumOfFourElements
{
    public static double getMax(double[] array)
    {
        int len = array.length;
        double[][] dp = new double[4][len];

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < len; j++)
            {
                dp[i][j] = Double.MIN_VALUE;
            }
        }

        dp[0][0] = -array[0];

        for (int i = 1; i < len; i++)
        {
            dp[0][i] = Math.max(dp[0][i - 1], -array[i]);
        }

        for (int i = 1; i < 4; i++)
        {
            for (int j = i; j < len; j++)
            {
                double addValue = i % 2 == 1 ? array[j] : -array[j];
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + addValue);
            }
        }

        return dp[3][len - 1];
    }
}