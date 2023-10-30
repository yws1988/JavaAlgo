package graph.tree.minimumSpanningTree;

/*
 Given a graph with n vetex, the cost between vetex u and v is costs[u, v],
 if costs[u, v] is 0, it means it is impossible to establish a connection between u and v.
 Give a edge (s, d), check if this edge is in the minimum spanning tree of this graph.
 */

import java.util.List;

public class EdgeBelongsToMinimumSpanningTree
{
    public static boolean isEdgeInMST(int s, int d, List<Integer>[] graph, int[][] costs)
    {
        int n = graph.length;
        var vs = new boolean[n];
        return costs[s][d] != 0 && dfs(s, costs[s][d], vs, d, costs, graph);
    }

    static boolean dfs(int u, int cost, boolean[] vs, int des, int[][] costs, List<Integer>[] graph)
    {
        vs[u] = true;
        if (u == des)
        {
            return false;
        }

        for (var c : graph[u])
        {
            if (!vs[c] && costs[u][c] < cost)
            {
                if (!dfs(c, cost, vs, des, costs, graph))
                {
                    return false;
                }
            }
        }

        return true;
    }
}
