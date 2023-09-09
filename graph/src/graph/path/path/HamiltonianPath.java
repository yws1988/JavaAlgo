package graph.path.path;

/*
A Hamiltonian path is defined as the path in a directed or undirected graph which visits
each and every vertex of the graph exactly once.
 */

import java.util.ArrayList;
import java.util.List;

public class HamiltonianPath
{
    public static List<Integer> getHamiltonianPath(List<Integer>[] graph)
    {
        int v = graph.length;

        boolean found = false;
        var childs = new int[v];
        int src = -1;

        for (int i = 0; i < v; i++)
        {
            var vs = new boolean[v];

            for (int h = 0; h < v; h++)
            {
                childs[h] = -1;
            }

            if (DfsPathUntil(graph, v, vs, i, 1, childs))
            {
                found = true;
                src = i;
                break;
            }
        }

        if (found)
        {
            var res = new ArrayList<Integer>();
            res.add(src);
            while (childs[src] != -1)
            {
                src = childs[src];
                res.add(src);
            }

            return res;
        }
        else
        {
            return null;
        }
    }

    public static List<Integer> getHamiltonianCircle(List<Integer>[] graph)
    {
        int v = graph.length;
        var childs = new int[v];
        var vs = new boolean[v];
        int src = 0;

        for (int h = 0; h < v; h++)
        {
            childs[h] = -1;
        }

        if (DfsCircleUntil(graph, v, vs, src, 1, childs))
        {
            var res = new ArrayList<Integer>();
            res.add(src);
            while (childs[src] != -1)
            {
                src = childs[src];
                res.add(src);
            }

            return res;
        }
        else
        {
            return null;
        }
    }

    static boolean DfsPathUntil(List<Integer>[] graph, int v, boolean[] vs, int src, int level, int[] childs)
    {
        if (level == v) return true;

        vs[src] = true;

        for (var c : graph[src])
        {
            if (!vs[c])
            {
                childs[src] = c;
                if (DfsPathUntil(graph, v, vs, c, level + 1, childs))
                {
                    return true;
                }

                childs[src] = -1;
            }
        }

        return vs[src] = false;
    }

    static boolean DfsCircleUntil(List<Integer>[] graph, int v, boolean[] vs, int src, int level, int[] childs)
    {
        if (level == v && graph[src].contains(0)) return true;

        vs[src] = true;

        for (var c : graph[src])
        {
            if (!vs[c])
            {
                childs[src] = c;
                if (DfsPathUntil(graph, v, vs, c, level + 1, childs))
                {
                    return true;
                }

                childs[src] = -1;
            }
        }

        return vs[src] = false;
    }
}
