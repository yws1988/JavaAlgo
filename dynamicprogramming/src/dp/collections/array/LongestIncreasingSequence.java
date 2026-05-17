// Longest increasing Sequence length
// Input { 10, 2, 36, 7, 9, 25, 12, 2 }
// Output 4 {2, 7, 9, 12 }

package dp.collections.array;

import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSequence
{
    // Complexity O(N*N)
    public static int getLongestLength(int[] nums)
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

    // Complexity O(NlogN)
    public static int getLongestLengthWithBinarySearch(List<Integer> values) {
        int n = values.size();
        if(n<=1) return n;

        int[] tails = new int[n];
        for (int i = 0; i < tails.length; i++) {
            tails[i]=Integer.MAX_VALUE;
        }
        int result = 0;
        for (int value : values) {
            int left = 0, right=n-1;
            while(left<=right){
                int mid=(left+right)>>1;
                if(tails[mid]<value){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }

            if(left==n) return n;
            tails[left]=value;
            result=Math.max(result, left+1);
        }

        return result;
    }
}
