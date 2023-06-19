package graph.flow;
/*
Maximum Bipartite Matching
A matching in a Bipartite Graph is a set of the edges chosen in such a way that no two edges
share an endpoint. A maximum matching is a matching of maximum size (maximum number of edges).
In a maximum matching, if any edge is added to it, it is no longer a matching.
There can be more than one maximum matchings for a given Bipartite Graph.
 */

import java.util.List;

public class MaximumBiPartiteGraph
{
    public static int getMaximumBitPartiteMatching(int[][] bp, int[] match)
    {
        int m = bp.length;
        int n = bp[0].length;

        match = new int[n];
        for (int i = 0; i < n; i++)
        {
            match[i] = -1;
        }

        int result = 0;
        for (int i = 0; i < m; i++)
        {
            boolean[] seen = new boolean[n];
            if (bitPartiteMatching(bp, i, match, seen, n))
            {
                result++;
            }

            if (result == n)
            {
                break;
            }
        }

        return result;
    }

    static boolean bitPartiteMatching(int[][] bp, int u, int[] match, boolean[] seen, int n)
    {
        for (int v = 0; v < n; v++)
        {
            if (bp[u][v] == 1 && !seen[v])
            {
                seen[v] = true;
                if (match[v] == -1 || bitPartiteMatching(bp, match[v], match, seen, n))
                {
                    match[v] = u;
                    return true;
                }
            }
        }

        return false;
    }

    public static int getMaximumBitPartiteMatching(List<Integer>[] bp, int[] match)
    {
        int n = bp.length;

        match = new int[n];
        for (int i = 0; i < n; i++)
        {
            match[i] = -1;
        }

        int result = 0;
        for (int i = 0; i < n; i++)
        {
            boolean[] seen = new boolean[n];
            if (bitPartiteMatching(bp, i, match, seen, n))
            {
                result++;
            }

            if (result == n)
            {
                break;
            }
        }

        return result;
    }

    static boolean bitPartiteMatching(List<Integer>[] bp, int u, int[] match, boolean[] seen, int n)
    {
        for (var v : bp[u])
        {
            if (!seen[v])
            {
                seen[v] = true;
                if (match[v] == -1 || bitPartiteMatching(bp, match[v], match, seen, n))
                {
                    match[v] = u;
                    return true;
                }
            }
        }

        return false;
    }
}

