package collections.array;
/*
Kevin has a permutation S of N integers 1, 2, ..., N
Kevin wants to get a permutation Q.
There are M good pairs of integers (ai , bi).
Kevin can perform following operation with his permutation:
Swap Px and Py only if (x, y) is a good pair.
Help him and tell if Kevin can obtain permutation D using such operations.
 */

import datastructures.tuple.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermutationSwap {

    public boolean canSwapToTargetPermutation(int[] sPermutation, int[] dPermutation, List<Pair> paris)
    {
        int n = sPermutation.length;
        var graph = new ArrayList[n+1];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (var item : paris)
        {
            int src = item.x;
            int des = item.y;
            graph[src].add(des);
            graph[des].add(src);
        }


        boolean[] vs = new boolean[n+1];
        int num = 1;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++)
        {
            if (!vs[i])
            {
                queue.add(i);
                while (queue.size() > 0)
                {
                    int child = queue.pop();
                    sPermutation[child] = num;
                    vs[child] = true;
                    for (Integer cc : (List<Integer>)graph[child])
                    {
                        if (!vs[cc])
                        {
                            queue.add(cc);
                        }
                    }
                }

                num++;
            }
        }

        for (int i = 0; i < n; i++)
        {
            if (sPermutation[i] != dPermutation[i] && sPermutation[sPermutation[i]] != sPermutation[dPermutation[i]])
            {
               return false;
            }
        }

        return true;
    }
}
