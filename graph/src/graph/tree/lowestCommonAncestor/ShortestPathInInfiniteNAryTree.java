package graph.tree.lowestCommonAncestor;

/*
    You're given a N-ary infinite tree rooted at a vertex numbered 1. All its edges are weighted 1 initially.
    Any node  will have exactly  children numbered as:
    N*X, N*X+1, ..., N*X+K-1
    You are given q queries to answer which will be of the following two types:

    query type 1 : Print the shortest distance between nodes u and v.
    query type 2 : Increase the weight of all edges on the shortest path between u and v by w.

    For each query of type u v, print a single integer denoting the shortest distance between u and v.
*/

import datastructures.tuple.PairL;

import java.util.HashMap;
import java.util.Map;

public class ShortestPathInInfiniteNAryTree {
    public static int n, q;
    public static long[] ns;
    public static Map<PairL, Long> weights;

    public static void getShortestPath(Long[] queries)
    {
        weights = new HashMap<>();

        for (int i = 0; i < q; i++)
        {
            long queryType = queries[0];
            long u = queries[1];
            long v = queries[2];

            long lca = getLowestCommonAncestor(u, v);

            if (queryType == 1)
            {
                System.out.println(getCosts(lca, u) + getCosts(lca, v));
            }
            else
            {
                long w = ns[3];

                updateCosts(lca, u, w);
                updateCosts(lca, v, w);
            }
        }
    }

    static long getCosts(long parent, long child)
    {
        if (parent == child) return 0;

        long cost = 0;

        while (child != parent)
        {
            long cparent = child / n;
            var path = new PairL(cparent, child);
            if (weights.containsKey(path))
            {
                cost += weights.get(path);
            }
            else
            {
                weights.put(path, 1L);
                cost += 1L;
            }

            child = cparent;
        }

        return cost;
    }

    static void updateCosts(long parent, long child, long weight)
    {
        if (parent == child) return;

        while (child != parent)
        {
            long cparent = child / n;
            var path = new PairL(cparent, child);

            if (!weights.containsKey(path))
            {
                weights.put(path, 1L);
            }

            weights.put(path, weights.get(path)+weight);

            child = cparent;
        }
    }

    public static long getLowestCommonAncestor(long u, long v)
    {
        long max = Math.max(u, v);
        long min = Math.min(u, v);

        int maxd = (int)(Math.log(max)/Math.log(n));
        int mind = (int)(Math.log(min)/Math.log(n));

        while (maxd != mind)
        {
            max = max / n;
            maxd--;
        }

        if (max == min)
        {
            return max;
        }

        while (max != min)
        {
            max /= n;
            min /= n;
        }

        return max;
    }
}
