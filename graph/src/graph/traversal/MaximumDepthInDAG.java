package graph.traversal;

/*
Give a directed acyclic graph, calculate le maximum length of the path from one vertex to another vertex
Example: graph 1->2->3->4
The maximum length is 4
 */

import java.util.Arrays;
import java.util.List;

public class MaximumDepthInDAG
{
    public static int max;
    public static int solve(List<Integer>[] graph){
        int n = graph.length;
        boolean[] vs = new boolean[n];
        depth = new int[n];
        Arrays.fill(depth, 1);

        for (int i = 0; i < n; i++) {
            if(!vs[i]){
                dfs(graph, i, vs);
            }
        }

        return max;
    }

    static int[] depth;

    static void dfs(List<Integer>[] graph, int src, boolean[] vs) {
        vs[src] = true;
        int maxChild = 0;
        for (var child : graph[src]) {
            if (!vs[child]) {
                dfs(graph, child, vs);
            }

            maxChild = Math.max(depth[child], maxChild);
        }

        depth[src]+=maxChild;
        max = Math.max(depth[src], max);
    }
}

