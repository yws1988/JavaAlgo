/*
 * Given an array arr[] of size n, find the maximum size of the subarray 
 * which of the sum is zero
 */
namespace CSharpAlgo.DynamicProgramming.Collection.Array
{
    using System.Collections.Generic;
    using System.Linq;

    public class MaximumSumZeroSubArray
    {
        public static double[] GetSubArray(double[] array)
        {
            double sum = 0;
            int start = -1;
            int maxLength = 0;

            Dictionary<double, int> dic = new Dictionary<double, int>();

            for (int i = 0; i < array.length; i++)
            {
                sum += array[i];

                if (sum == 0)
                {
                    start = 0;
                    maxLength = i + 1;
                }
                else
                {
                    if (dic.ContainsKey(sum))
                    {
                        int len = i - dic[sum];
                        if (len > maxLength)
                        {
                            maxLength = len;
                            start = dic[sum] + 1;
                        }
                    }
                    else
                    {
                        dic.Add(sum, i);
                    }
                }
            }

            return start == -1 ? null : array.Skip(start).Take(maxLength).ToArray();
        }
    }
}
