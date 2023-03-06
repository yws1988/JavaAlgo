/*
 Give a symmetric cost matrix, get the maximum sum of the matrix sub elements, every two of the sub elements matrix[a, b], matrix[c, d]
 should satisfy one of the following condition:
 1. a < b < c < d
 2. c < d < a < b
 3. a < c < d < b

 Example matrix:
   0 2 6 1
   2 0 8 9
   6 8 0 3
   1 9 3 0

 The maximum sum should be 9 elements(0, 3), (1, 2) or element (1, 3)

 */

package dp.graph;

public class MaximumSumOfNonIntersectionElementsInMatrix
{
    public static int getMaximumSum(int[][] matrix)
    {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int distance = 1; distance < n; distance++)
        {
            for (int left = 0, right = left + distance; right < n; left++, right++)
            {
                if (distance == 1)
                {
                    dp[left][right] = matrix[left][right];
                }
                else
                {
                    for (int middle = left + 1; middle < right; middle++)
                    {
                        dp[left][right] = Math.max(Math.max(dp[left][right], matrix[middle][right] + dp[left][middle - 1] + dp[middle + 1][right - 1]), dp[left][right - 1]);
                    }

                    dp[left][right] = Math.max(dp[left][right], matrix[left][right] + dp[left + 1][right - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
