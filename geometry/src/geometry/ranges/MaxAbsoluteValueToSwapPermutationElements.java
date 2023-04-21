package geometry.ranges;

/*
A permutation of numbers permutations[] from 1 to n, swap two elements of the permutation at most once,
what is the maximum absolute value
sum(|permutations[i]-i|) where i from 1 to n
 */

public class MaxAbsoluteValueToSwapPermutationElements {
    public static long getMaxAbsoluteValue(int[] permutations)
    {
        int n = permutations.length;
        int leftMax = 0;
        int rightMin = Integer.MAX_VALUE;

        int leftMaxMin = Integer.MAX_VALUE;
        int rightMinMax = 0;

        long result=0;

        for (int i = 0; i < n; i++) {
            result+=Math.abs(i+1-permutations[i]);

            leftMax = Math.max(i+1, permutations[i]);
            leftMaxMin = Math.min(leftMaxMin, leftMax);

            rightMin = Math.min(i+1, permutations[i]);
            rightMinMax = Math.max(rightMinMax, rightMin);
        }

        if(rightMinMax > leftMaxMin){
            result+=2*(rightMinMax-leftMaxMin);
        }

        return result;
    }
}
