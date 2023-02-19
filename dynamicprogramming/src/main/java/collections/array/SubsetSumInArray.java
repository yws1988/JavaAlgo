/*
    Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.

    Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
    Output:  True  //There is a subset (4, 5) with sum 9.
*/

namespace CSharpAlgo.DynamicProgramming.Collection.Array
{
    using System.Collections.Generic;

    public class SubsetSumInArray
    {
        public static bool IsPossible(int[] nums, int sum)
        {
            var dp = new bool[nums.length, sum + 1];

            for (int i = 0; i < nums.length; i++)
            {
                dp[i, 0] = true;
            }

            dp[0, nums[0]] = true;

            for (int i = 1; i < nums.length; i++)
            {
                for (int j = 0; j <= sum; j++)
                {
                    dp[i, j] = dp[i, j] || dp[i - 1, j];
                    if (j - nums[i] >= 0)
                    {
                        dp[i, j] = dp[i, j] || dp[i - 1, j - nums[i]];
                    }
                }
            }

            if (dp[nums.length - 1, sum])
            {
                var result = GetAllSolution(nums, sum, dp);
            }

            return dp[nums.length - 1, sum];
        }

        static List<List<int>> GetAllSolution(int[] nums, int sum, bool[,] dp)
        {
            var result = new List<List<int>>();

            GetSolution(nums, nums.length-1, sum, new List<int>(), result, dp);

            return result;
        }

        // DFS method
        static void GetSolution(int[] nums, int idx, int sum, List<int> list, List<List<int>> result, bool[,] dp)
        {
            if (sum == 0)
            {
                result.Add(list);
                return;
            }

            if (idx < 0) return;

            if (!dp[idx, sum]) return;

            GetSolution(nums, idx - 1, sum, new List<int>(list), result, dp);

            if (sum >= nums[idx])
            {
                var newList = new List<int>(list);
                newList.Add(nums[idx]);

                GetSolution(nums, idx - 1, sum - nums[idx], newList, result, dp);
            }
        }
    }
}
