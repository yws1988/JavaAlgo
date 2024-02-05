package graph.connectivity;

import java.util.List;
import graph.utils.GraphListHelper;

public class StronglyConnectedGraph
{
    public static boolean isStronglyConnected(List<Integer>[] graph)
    {
        int v = graph.length;
        boolean[] vs = new boolean[v];

        DFSUtil(graph, 0, vs);

        for (var tmp : vs) {
            if(!tmp) return false;
        }

        var tGraph = GraphListHelper.getTransposeGraph(graph);

        for (int i = 0; i < v; i++)
        {
            vs[i] = false;
        }

        DFSUtil(tGraph, 0, vs);

        for (var tmp : vs) {
            if(!tmp) return false;
        }

        return true;
    }

    static void DFSUtil(List<Integer>[] g, int i, boolean[] vs)
    {
        vs[i] = true;

        for (var c : g[i])
        {
            if (!vs[c])
            {
                DFSUtil(g, c, vs);
            }
        }
    }
}