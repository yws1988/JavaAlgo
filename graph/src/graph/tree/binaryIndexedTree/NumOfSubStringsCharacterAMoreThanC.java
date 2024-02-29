package graph.tree.binaryIndexedTree;
/*
You are given a string S of length N. The string contains only 'a', 'b', and 'c'.
Your task is to find the count of substrings in string S that
the frequency of occurrence of character 'a' is strictly more than occurrence of 'c' in the string.
*/

import datastructures.trees.BinaryIndexedTree;

import java.util.Arrays;
import java.util.Set;

public class NumOfSubStringsCharacterAMoreThanC  {
    public static final int mod = (int)(1e9 + 7);

    public static int getNum(String str)
    {
        int n = str.length();

        int[] sum = new int[n];

        sum[0] = str.charAt(0) == 'a' ? 1 : str.charAt(0) == 'c' ? -1 : 0;

        for (int i = 1; i < n; i++)
        {
            int value = str.charAt(i) == 'a' ? 1 : str.charAt(i) == 'c' ? -1 : 0;
            sum[i] += sum[i - 1] + value;
        }

        var sortedSum = (Integer[]) Set.of(sum).toArray();
        Arrays.sort(sortedSum);
        int size = sortedSum.length;

        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(size);

        int res = 0;
        for (int i = 0; i < n; i++)
        {
            int idx = Arrays.binarySearch(sortedSum, sum[i]);
            int tsum = sum[i] > 0 ? 1 : 0;

            if (idx > 0)
            {
                tsum = (tsum + binaryIndexedTree.query(idx - 1)) % mod;
            }

            res = (res + tsum) % mod;

            binaryIndexedTree.update(idx, 1);
        }

        return res;
    }
}
