package math;

/*
 * Bell Numbers (Number of ways to Partition a Set)
 * Input:  n = 3
 * Output: Number of ways = 5
 * Explanation: Let the set be {1, 2, 3}
    { {1}, {2}, {3} }
    { {1}, {2, 3} }
    { {2}, {1, 3} }
    { {3}, {1, 2} }
    { {1, 2, 3} }
 */

public class BellNumber
{
    // S(n, k) represents n numbers to be separated into k sets
    // S(n+1, k) = S(n, k-1) + k*S(n, k)

    public static int getNumOfSets(int n)
    {
        int[][] nums = new int[n + 1][n + 1];
        nums[1][1] = 1;

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= i; j++)
            {
                nums[i][j] = nums[i - 1][j - 1] + j * nums[i - 1][j];
            }
        }

        int sum = 0;
        for (int i = 1; i <= n; i++)
        {
            sum += nums[n][i];
        }

        return sum;
    }
}
