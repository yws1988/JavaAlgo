package dp.collections.array;

/*
Given three arrays with the same size n (n < 100), do the following shuffling operations:
for the element index k in the array i, j:
if array[i][k]>array[i][k-1] && array[j][k]>array[j][k-1] number of shuffling add 2
if only array[i][k]>array[i][k-1] number of shuffling add 1
if only array[j][k]>array[j][k-1] number of shuffling add 1

return the maximum operations of the shuffling.
Any value of the elements in array is less than 100.

For example the following three arrays with the size 24
62 16 42 87 68 63 98 89 54 51 56 82 36 75 93 17 57 57 35 95 52 45 23 36
15 4 50 82 56 64 20 100 90 92 26 4 24 96 3 3 50 91 33 72 44 83 77 46
54 6 64 56 60 49 22 18 53 63 8 97 15 9 66 11 47 80 8 91 13 40 80 44

 */

import datastructures.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ArrayShuffling {
    public static int[][] arrays = new int[3][];
    public static int N;
    static int[][][] dp;
    static List<Pair> pairs = new ArrayList<>();

    public static int solve()
    {
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 101; j++)
            {
                for (int k = 0; k < 101; k++)
                {
                    dp[i][j][k] = -1;
                }
            }
        }

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (i != j)
                {
                    pairs.add(new Pair(i, j));
                }
            }
        }

        return maxUtil(0, 0, 0);
    }

    static int maxUtil(int pos, int firstValue, int secondValue)
    {
        if (pos >= N) return 0;
        if (dp[pos][firstValue][secondValue] != -1) return dp[pos][firstValue][secondValue];

        int res = maxUtil(pos+1, firstValue, secondValue);

        for (var ps : pairs)
        {
            int ff = ps.x;
            int fs = ps.y;

            if(arrays[ff][pos]>=firstValue && arrays[fs][pos] >= secondValue)
            {
                res = Math.max(res, maxUtil(pos+1, arrays[ff][pos], arrays[fs][pos])+2);
            }

            if (arrays[ff][pos] >= firstValue)
            {
                res = Math.max(res, maxUtil(pos + 1, arrays[ff][pos], secondValue)+1);
            }

            if (arrays[fs][pos] >= secondValue)
            {
                res = Math.max(res, maxUtil(pos + 1, firstValue, arrays[fs][pos])+1);
            }
        }

        return dp[pos][firstValue][secondValue]=res;
    }
}
