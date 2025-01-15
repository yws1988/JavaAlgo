package graph.traversal;
/*
 Given a directed acyclic graph with n vertex, calculate the number of different path from vertex src to des.
 */

import java.util.List;

public class NumberOfPathsInDAG {
    static int[] ws;

    public static int getNumberOfPath(List<Integer>[] graph, int src, int des){
        int n = graph.length;
        ws = new int[n];
        ws[des]=1;
        boolean[] vs = new boolean[n];

        dfs(graph, src, vs);

        return ws[src];
    }

    static void dfs(List<Integer>[] graph, int src, boolean[] vs) {
        vs[src] = true;
        for (var child : graph[src]) {
            if (!vs[child]) {
                dfs(graph, child, vs);
            }

            ws[src]+=ws[child];
        }
    }
}
