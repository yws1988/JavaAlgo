/*
 * Given an array of integers. A subsequence of arr[] is called Bitonic if
 * it is first increasing, then decreasing.
 * Input : arr[] = {1, 15, 51, 45, 33, 100, 12, 18, 9}
 * Output Maximum Sum: 194
 * Maximum sum Bi-tonic sub-sequence is 1 + 15 + 51 + 100 + 18 + 9 = 194
 * Input : arr[] = {1, 11, 2, 10, 4, 5, 2, 1}
 * Output longest length: 6 (1, 2, 10, 4, 2, 1)
 */

package dp.collections.array;

public class LongestAndMaximumSumBitonicSequence
{
    public static int getLongestLength(int[] nums)
    {
        int length = nums.length;
        int[] dpIncreasing = new int[length];
        dpIncreasing[0] = 1;
        for (int i = 1; i < length; i++)
        {
            dpIncreasing[i] = 1;

            for (int j = 0; j < i; j++)
            {
                if (nums[i] >= nums[j])
                {
                    dpIncreasing[i] = Math.max(dpIncreasing[i], dpIncreasing[j] + 1);
                }
            }
        }

        int[] dpDecreasing = new int[length];
        dpDecreasing[length-1] = 1;
        for (int i = length-2; i >= 0; i--)
        {
            dpDecreasing[i] = 1;

            for (int j = length-1; j > i; j--)
            {
                if (nums[i] >= nums[j])
                {
                    dpDecreasing[i] = Math.max(dpDecreasing[i], dpDecreasing[j] + 1);
                }
            }
        }

        int max = 0;

        for (int i = 1; i < length-1; i++)
        {
            max = Math.max(dpIncreasing[i] + dpDecreasing[i] - 1, max);
        }

        return max;
    }

    public static int GetMaxSum(int[] array)
    {
        int len = array.length;
        // increasing and descrising
        int[] dpIncreasing = new int[len];
        int[] dpDescreasing = new int[len];

        for (int i = 0; i < len; i++)
        {
            dpIncreasing[i] = dpDescreasing[i] = array[i];
        }

        dpIncreasing[0] = array[0];
        for (int i = 1; i < len; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (array[j] < array[i])
                {
                    dpIncreasing[i] = Math.max(dpIncreasing[i], dpIncreasing[j] + array[i]);
                }
            }
        }

        dpDescreasing[len - 1] = array[len - 1];
        for (int i = len - 2; i >= 0; i--)
        {
            for (int j = i + 1; j < len; j++)
            {
                if (array[i] > array[j])
                {
                    dpDescreasing[i] = Math.max(dpDescreasing[i], dpDescreasing[j] + array[i]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++)
        {
            max = Math.max(max, dpIncreasing[i] + dpDescreasing[i] - array[i]);
        }

        return max;
    }
}
