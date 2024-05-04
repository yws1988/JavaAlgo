package dp.collections.array;

/*
Given an array with n elements.
For each element of the array, you can convert array[i] to the following values:
array[i] convert to -array[i]
array[i] add value 1
array[i] minus value 1

Check after the conversion if it is possible that the sum of the array equals to K
 */


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfArrayConversionEqualsToK {
    public static int[] array;
    public static int n;
    public static Map<Integer, Boolean>[] dp;

    public boolean canBeEqualToK(int k){
        n = array.length;
        dp = new HashMap[n];

        Arrays.fill(dp, new HashMap<Integer, Boolean>());

        return solve(0, k);
    }

    static boolean solve(int i, int s)
    {
        if (i == n && s == 0) return true;

        if (i>=n) return false;

        if (dp[i].containsKey(s)) return dp[i].get(s);

        return dp[i].put(s, solve(i + 1, s - array[i]) || solve(i + 1, s + array[i]) || solve(i + 1, s - (array[i]+i+1)) || solve(i + 1, s - (array[i]-i-1)));
    }
}
