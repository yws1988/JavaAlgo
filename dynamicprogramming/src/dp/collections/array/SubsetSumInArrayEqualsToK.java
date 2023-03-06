/*
    Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.

    Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
    Output:  True  //There is a subset (4, 5) with sum 9.
*/

package dp.collections.array;

import java.util.ArrayList;

public class SubsetSumInArrayEqualsToK
{
    public static boolean isPossible(int[] nums, int k)
    {
        var dp = new boolean[nums.length][k + 1];

        for (int i = 0; i < nums.length; i++)
        {
            dp[i][0] = true;
        }

        dp[0][nums[0]] = true;

        for (int i = 1; i < nums.length; i++)
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

        if (dp[nums.length - 1][k])
        {
            var result = GetAllSolution(nums, k, dp);
        }

        return dp[nums.length - 1][k];
    }

    static ArrayList<ArrayList<Integer>> GetAllSolution(int[] nums, int k, boolean[][] dp)
    {
        var result = new ArrayList<ArrayList<Integer>>();

        GetSolution(nums, nums.length-1, k, new ArrayList<Integer>(), result, dp);

        return result;
    }

    // DFS method
    static void GetSolution(int[] nums, int idx, int k, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> result, boolean[][] dp)
    {
        if (k == 0)
        {
            result.add(list);
            return;
        }

        if (idx < 0) return;

        if (!dp[idx][k]) return;

        GetSolution(nums, idx - 1, k, new ArrayList<>(list), result, dp);

        if (k >= nums[idx])
        {
            var newList = new ArrayList<>(list);
            newList.add(nums[idx]);

            GetSolution(nums, idx - 1, k - nums[idx], newList, result, dp);
        }
    }
}
