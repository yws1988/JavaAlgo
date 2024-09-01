package graph.connectivity;

import java.util.ArrayList;
import java.util.List;

public class Briges
{
    public static int step;

    public static List<Edge> getAllBriges(List<Integer>[] graph)
    {
        var briges = new ArrayList<Edge>();
        step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];

        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
        }

        DFS(0, graph, visited, dist, low, parents, briges);

        return briges;
    }

    public static void DFS(int s, List<Integer>[] graph, boolean[] vs, int[] dist, int[] low, int[] parents, List<Edge> briges)
    {
        vs[s] = true;
        dist[s] = ++step;
        low[s] = step;

        int child = 0;
        for (var c : graph[s])
        {
            if (!vs[c])
            {
                child++;
                parents[c] = s;
                DFS(c, graph, vs, dist, low, parents, briges);
                low[s] = Math.min(low[c], low[s]);

                if (low[c] > dist[s])
                {
                    briges.add(new Edge(s, c));
                }
            }
            else if (parents[s] != c)
            {
                low[s] = Math.min(dist[c], low[s]);
            }
        }
    }

    public static class Edge
    {
        public int Src;
        public int Des;

        public Edge(int s, int d)
        {
            Src = s;
            Des = d;
        }

    }
}
