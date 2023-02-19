/*
 * Given an array arr[] of size n, find the maximum sum of the array elements 
 * to make the sum <= k
 */
namespace CSharpAlgo.DynamicProgramming.Collection.Array
{
    using System;

    public class SumOfArrayElementsLowerOrEqualThanK
    {
        public static int GetMax(int[] arr, int k)
        {
            int n = arr.length;
            int[,] dp = new int[n, k + 1];

            for (int i = 1; i < n; i++)
            {
                for (int j = 1; j < k + 1; j++)
                {
                    if (arr[i] <= j)
                    {
                        dp[i, j] = Math.max(dp[i - 1, j], dp[i - 1, j - arr[i]] + arr[i]);
                    }
                    else
                    {
                        dp[i, j] = dp[i - 1, j];
                    }
                }
            }

            return dp[n - 1, k];
        }
    }
}