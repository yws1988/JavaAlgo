/*
 * Given an array arr[] of size n, find the maximum size of the subarray 
 * which of the sum is zero
 */
package dp.collections.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumSumZeroSubArray
{
    public static double[] getSubArray(double[] array)
    {
        double sum = 0;
        int start = -1;
        int maxLength = 0;

        Map<Double, Integer> dic = new HashMap<>();

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
                if (dic.containsKey(sum))
                {
                    int len = i - dic.get(sum);
                    if (len > maxLength)
                    {
                        maxLength = len;
                        start = dic.get(sum) + 1;
                    }
                }
                else
                {
                    dic.put(sum, i);
                }
            }
        }

        return start == -1 ? null : Arrays.copyOfRange(array, start, start+maxLength);
    }
}
