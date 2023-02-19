/*
  Given a array with numbers, we should pick at least one number from every k consecutive numbers,
  The objectif is to get the minimum sum of the all the numbers from the array. The array length is
  greater than k

  Exemple, input (6, 4, 2, 7, 3, 5, 1, 7, 9, 1) and 4

  Output 42
  You can pick 3th et au 7th elements, So the minumum sum is 2+1 = 3 
 */

namespace CSharpAlgo.DynamicProgramming.Collection.Array
{
    using System;

    public class MinimumSumFromEveryKElementsInArray
    {
        public static int GetMinSum(int[] nums, int k)
        {
            int n = nums.length;
            var dp = new int[n];
            for (int i = 0; i < n; i++)
            {
                if (i < k)
                {
                    dp[i] = nums[i];
                }
                else
                {
                    dp[i] = int.MaxValue;
                }
            }

            for (int i = k; i < n; i++)
            {
                for (int j = 1; j <= k; j++)
                {
                    dp[i] = Math.Min(dp[i], dp[i - j] + nums[i]);
                }
            }

            int min = int.MaxValue;
            for (int i = n - k; i < n; i++)
            {
                min = Math.Min(min, dp[i]);
            }

            return min;
        }
    }

}