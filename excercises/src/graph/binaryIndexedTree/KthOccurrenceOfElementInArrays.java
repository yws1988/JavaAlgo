package graph.binaryIndexedTree;

import datastructures.trees.BinaryIndexedTree;

import java.util.ArrayList;
import java.util.List;

public class KthOccurrenceOfElementInArrays
{
    public static List<Integer> getResult(int[] ns, int x, int[][] queries)
    {
        int n = ns.length;

        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(n);

        for (int i = 0; i < n; i++)
        {
            if (ns[i] == x)
            {
                binaryIndexedTree.update(i, 1);
            }
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < queries.length; i++)
        {
            int[] tmp = queries[i];
            int type = tmp[0];
            if (type == 1)
            {
                int l = tmp[1];
                int r = tmp[2];
                int k = tmp[3];
                int numL = binaryIndexedTree.query(l-1);
                int numR = binaryIndexedTree.query(r);

                if (numR >= numL + k)
                {
                    int low = l;
                    int high = r;

                    int sum = numL + k;

                    int mid=0;

                    while (low < high)
                    {
                        mid = (low + high) >> 1;

                        if (binaryIndexedTree.query(mid) >= sum)
                        {
                            high = mid;
                        }
                        else
                        {
                            low = mid + 1;
                        }
                    }

                    res.add(low);
                }
                else
                {
                    res.add(-1);
                }
            }
            else
            {
                int index = tmp[1] - 1;
                int value = tmp[2];

                if (ns[index] == x && value != x)
                {
                    binaryIndexedTree.update(index, -1);
                }

                if (ns[index] != x && value == x)
                {
                    binaryIndexedTree.update(index, 1);
                }

                ns[index] = value;
            }
        }

        return res;
    }
}
