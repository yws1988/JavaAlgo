package graph.connectivity;
/*
    Eulerian Cycle: An undirected graph has Eulerian cycle if following two conditions are true.
    All vertices with non-zero degree are connected.
    All vertices have even degree.
    Eulerian Path: An undirected graph has Eulerian Path if following two conditions are true.
    All vertices with non-zero degree are connected.
    If zero or two vertices have odd degree and all other vertices have even degree.
 */

import java.util.Arrays;
import java.util.List;

public class EulerianPathAndCircleUndirectedGraph {
    public static boolean isEulerianPathAndCircle(List<Integer>[] graph)
    {
        int v = graph.length;
        var visited = new Boolean[v];
        int[] degrees = Arrays.stream(graph).mapToInt(s -> s.size()).toArray();

        int src = -1;
        for (int i = 0; i < v; i++)
        {
            if (degrees[i] == 0)
            {
                visited[i] = true;
            }
            else
            {
                src = i;
            }
        }

        //Eulerian Path no edges
        if (src == -1)
        {
            return true;
        }

        dfs(src, graph, visited);

        if(Arrays.stream(visited).anyMatch(s -> !s))
        {
            return false;
        }

        int evenNum = (int)Arrays.stream(degrees).filter(s -> s % 2==0).count();

        //Eulerian Circle
        if (evenNum == v)
        {
            return true;
        }

        // Eulerian Path
        if (evenNum == v-2)
        {
            return true;
        }

        return false;
    }

    static void dfs(int s, List<Integer>[] graph, Boolean[] vs)
    {
        vs[s] = true;
        for (var c : graph[s])
        {
            if (!vs[c])
            {
                dfs(c, graph, vs);
            }
        }
    }
}
