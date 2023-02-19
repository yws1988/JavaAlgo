// Longest decreasing and increasing Sequence length for every element
// Input { 10, 2, 1, 7, 9, 25, 12, 2 }
// Output
// For element 6 is { 10, 2, 1, 7, 9, 12 }

package collections.array;

import java.util.Arrays;

public class LongestDecreasingIncreasingSequence
{
    public static int GetLongestLength(int[] nums)
    {
        int n = nums.length;

        var dpdescreasing = new int[n];
        dpdescreasing[0] = 1;

        for (int i = 1; i < n; i++)
        {
            for (int j = i - 1; j >= 0; j--)
            {
                if (nums[i] < nums[j])
                {
                    dpdescreasing[i] = dpdescreasing[j] + 1;
                    break;
                }

                if (j == 0)
                {
                    dpdescreasing[i] = 1;
                    break;
                }
            }
        }

        var dpincreasing = new int[n];
        dpincreasing[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (nums[i] < nums[j])
                {
                    dpincreasing[i] = dpincreasing[j] + 1;
                    break;
                }

                if (j == n - 1)
                {
                    dpincreasing[i] = 1;
                    break;
                }
            }
        }

        var res = new int[n];

        for (int i = 1; i < n; i++)
        {
            res[i] = dpdescreasing[i] + dpincreasing[i] - 1;
        }

        return Arrays.stream(res).max().getAsInt();
    }
}
