package cycle;

import java.util.List;

public class CycleInUnDirectedGraph {
    public static boolean doesGraphContainCycle(List<Integer>[] graph)
    {
        int v = graph.length;

        var vs = new boolean[v];

        for (int i = 0; i < v; i++)
        {
            if (!vs[i] && DFSUtilDetectCycle(i, i, vs, graph))
            {
                return true;
            }
        }

        return false;
    }

    private static boolean DFSUtilDetectCycle(int i, int parent, boolean[] vs, List<Integer>[] graph)
    {
        vs[i] = true;

        for (int child : graph[i])
        {
            if (child!=parent && vs[child])
            {
                return true;
            }

            if (!vs[child] && DFSUtilDetectCycle(child, i, vs, graph))
            {
                return true;
            }
        }

        return false;
    }

    // Count all the cyle with length n in an undirected graph
    public static int num;

    public static int countNLengthCycle(List<Integer>[] graph, int n)
    {
        int v = graph.length;
        boolean[] vs = new boolean[v];
        num = 0;
        for (int i = 0; i < v - (n - 1); i++)
        {
            DFSUtilCountCycle(i, vs, n - 1, i, graph);
            vs[i] = true;
        }

        return (num / 2);
    }

    private static void DFSUtilCountCycle(int s, boolean[] vs, int n, int src, List<Integer>[] graph)
    {
        if (n == 0)
        {
            if (graph[s].contains(src))
            {
                num++;
            }
            return;
        }

        vs[s] = true;

        for (var child : graph[s])
        {
            if (!vs[child])
            {
                DFSUtilCountCycle(child, vs, n - 1, src, graph);
            }
        }

        vs[s] = false;
    }
}
