/*
 * Find the maximum sum of a sub array in array
 * For example {3,7,-11,5,4,-2,5}
 * The result is 10
 */

package dp.collections.array;
public class MaximumSumOfSubArray
{
    public static double getMaximumSum(double[] array)
    {
        double cumulativeSum = 0, max = array[0];

        int cumulativeStart = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < array.length; i++)
        {
            cumulativeSum += array[i];

            if (max < cumulativeSum)
            {
                max = cumulativeSum;
                start = cumulativeStart;
                end = i;
            }

            if (cumulativeSum < 0)
            {
                cumulativeSum = 0;
                cumulativeStart = i + 1;
            }
        }

        return max;
    }
}
