package graph.connectivity;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BiConnected
{
    public static int Step;

    public static boolean isGraphBiConnected(List<Integer>[] graph)
    {
        Step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
        }

        return DFS(0, graph, visited, dist, low, parents);
    }

    public static boolean DFS(int s, List<Integer>[] graph, boolean[] vs, int[] dist, int[] low, int[] parents)
    {
        vs[s] = true;
        dist[s] = ++Step;
        low[s] = Step;

        int child = 0;
        for (var c : graph[s])
        {
            if (!vs[c])
            {
                child++;
                parents[c] = s;
                if (!DFS(c, graph, vs, dist, low, parents)) return false;
                low[s] = Math.min(low[c], low[s]);
                if (parents[s] == -1 && child > 1)
                {
                    return false;
                }

                if (parents[s] != -1 && low[c] >= dist[s])
                {
                    return false;
                }
            }
            else if (parents[s] != c)
            {
                low[s] = Math.min(dist[c], low[s]);
            }
        }

        return true;
    }

    static ArrayList<ArrayList<Edge>> BiConnectedCompenents = new ArrayList<ArrayList<Edge>>();
    public static void getBiconnectedComponents(List<Integer>[] graph)
    {
        Step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
        }
        var edges = new Stack<Edge>();

        DFSComponents(edges, 0, graph, visited, dist, low, parents);
    }

    public static void pushComponent(int s, int d, Stack<Edge> edges)
    {
        ArrayList<Edge> biComponents = new ArrayList<>();

        Edge edge;
        do
        {
            edge= edges.pop();
            biComponents.add(edge);
        } while (s != edge.Src || d != edge.Des);

        BiConnectedCompenents.add(biComponents);
    }

    public static void DFSComponents(Stack<Edge> edges, int s, List<Integer>[] graph, boolean[] visited, int[] dist, int[] low, int[] parents)
    {
        visited[s] = true;
        dist[s] = ++Step;
        low[s] = Step;

        int child = 0;
        for (var c : graph[s])
        {
            if (!visited[c])
            {
                child++;
                parents[c] = s;
                edges.push(new Edge(s, c));
                DFSComponents(edges, c, graph, visited, dist, low, parents);
                low[s] = Math.min(low[c], low[s]);
                if (parents[s] == -1 && child > 1)
                {
                    pushComponent(s, c, edges);
                }

                if (parents[s] != -1 && low[c] >= dist[s])
                {
                    pushComponent(s, c, edges);
                }
            }
            else if (parents[s] != c)
            {
                if (dist[s] > dist[c])
                {
                    edges.push(new Edge(s, c));
                }

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


