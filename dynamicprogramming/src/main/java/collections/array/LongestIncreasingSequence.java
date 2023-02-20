// Longest increasing Sequence length
// Input { 10, 2, 36, 7, 9, 25, 12, 2 }
// Output 4 {2, 7, 9, 12 }

package collections.array;

import java.util.Arrays;

public class LongestIncreasingSequence
{
    public static int GetLongestLength(int[] nums)
    {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        for (int i = 1; i < length; i++)
        {
            dp[i] = 1;

            for (int j = 0; j < i; j++)
            {
                if (nums[i]>=nums[j])
                {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}
