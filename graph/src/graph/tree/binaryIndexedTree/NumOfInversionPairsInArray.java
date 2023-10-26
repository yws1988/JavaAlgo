package graph.tree.binaryIndexedTree;

/*
Give an array A with n elments, each element is unique, two elements A[i] and A[j] from array A,
if i<j and A[i]>A[j], this is called valid inversion count. Calculate how many pair of inversion
count in a given array.
For example:
3 2 4 6 1

there are 5 pairs of inversion count
 */


import datastructures.trees.BinaryIndexedTree;

import java.util.Arrays;
import java.util.Collections;

public class NumOfInversionPairsInArray
{
    public static long count(Integer[] ns)
    {
        int n = ns.length;
        var sortedNs = Arrays.copyOf(ns, ns.length);;
        Arrays.sort(sortedNs);

        for (int j = 0; j < n; j++)
        {
            ns[j] = Arrays.binarySearch(sortedNs, ns[j]);
        }

        Collections.reverse(Arrays.asList(ns));

        var bit = new BinaryIndexedTree(n);

        long total = 0;
        bit.update(ns[0], 1);

        for (int j = 1; j < n; j++)
        {
            total += bit.query(ns[j] - 1);
            bit.update(ns[j], 1);
        }

        return total;
    }
}
