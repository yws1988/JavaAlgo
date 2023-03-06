/*
You are given a permutation of N numbers that are denoted by array A.
You are also given a query (L, R)
For all the subsequences from the subarray A[L, R] such that the length of subarray
is equal to the maximum element present in the subsequence,
find the bitwise XOR of all the elements present in these subsequences.
For example:
Array: [2, 3, 1, 5, 4]
L = 1, R = 5 so the subarray is [2, 3, 1, 5, 4]
Following subsequences satisfy the condition, {2, 1}, {2, 3, 1}, {1}, {2, 3, 1, 5, 4}, {2, 3, 1, 4}.
So, the required answer is 2^1^2^3^1^1^2^3^1^5^4^2^3^1^4 = 7
 */
/* Tag: MinimumExclusionNumber Permutations */

package dp.collections.array;

public class AllSubsequencesOfTheMaximumNumberEqualToSequenceLength {
    public static int calculateXorOfAllSubsequence(int[] ns, int l, int r)
    {
        int n = ns.length;
        int[] lmin = new int[n];
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++)
        {
            min = Math.min(min, ns[j]);
            lmin[j] = min;
        }

        int[] rmin = new int[n];
        min = Integer.MAX_VALUE;
        for (int j = n - 1; j >= 0; j--)
        {
            min = Math.min(min, ns[j]);
            rmin[j] = min;
        }

        int[] xor = new int[n + 1];
        int[] xordp = new int[n + 1];

        xor[1] = 1;
        xordp[1] = 1;

        for (int j = 2; j <= n; j++)
        {
            xor[j] = xor[j - 1] ^ j;
            xordp[j] = xordp[j - 1] ^ xor[j];
        }

        min = n + 1;

        if (l > 1)
        {
            min = Math.min(min, lmin[l - 2]);
        }

        if (r < n)
        {
            min = Math.min(min, rmin[r]);
        }

        return min == 1 ? 0 : xordp[min - 1];
    }
}
