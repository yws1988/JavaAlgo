package cycle;

import java.util.List;

public class CycleInDirectedGraph {
    public static boolean doesGraphContainsCycle(List<Integer>[] graph)
    {
        int v = graph.length;
        var vs = new boolean[v];

        for (int i = 0; i < v; i++)
        {

            if (!vs[i])
            {
                var currentVs =  vs.clone();

                if(DFSUtil(i, vs, currentVs, graph))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean DFSUtil(int i, boolean[] vs, boolean[] currentVs, List<Integer>[] graph)
    {
        vs[i] = true;

        for (int child : graph[i])
        {
            if (vs[child] && !currentVs[child])
            {
                return true;
            }

            if (!vs[child] && DFSUtil(child, vs, currentVs, graph))
            {
                return true;
            }
        }

        return false;
    }
}
