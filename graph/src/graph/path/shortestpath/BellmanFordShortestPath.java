package graph.path.shortestpath;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BellmanFordShortestPath {

    public int[] getShortestPath(int[][] graph, int src)
    {
        int v = graph.length;
        var edges = new ArrayList<Edge>();
        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if (graph[i][j] != 0)
                {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }

        var weights = new int[v];
        var parents = new int[v];

        for (int i = 0; i < v; i++)
        {
            if (i == src)
            {
                weights[i] = 0;
            }
            else
            {
                weights[i] = Integer.MAX_VALUE;
            }
        }

        calculate(v, weights, parents, edges);

        if (containsNegativeCycle(weights, edges))
        {
            System.out.println("Graph contains negative cycle");
        }

        return weights;
    }

    static void calculate(int v, int[] weights, int[] parents, List<Edge> edges)
    {
        for (int i = 1; i < v; i++)
        {
            for (var e : edges)
            {
                if (weights[e.src] != Integer.MAX_VALUE && weights[e.des] > weights[e.src] + e.weight)
                {
                    weights[e.des] = weights[e.src] + e.weight;
                    parents[e.des] = e.src;
                }
            }
        }
    }

    public static boolean containsNegativeCycle(int[] weights, List<Edge> edges)
    {
        for (var edge : edges)
        {
            if (weights[edge.des] > weights[edge.src] + edge.weight)
            {
                return true;
            }
        }

        return false;
    }

    public List<Integer> getShortestPathForEveryNode(int src, int des, int[] parents)
    {
        var path = new Stack<Integer>();

        int p = des;
        path.push(p);
        do
        {
            path.push(parents[p]);
            p = parents[p];
        } while (p != src);

        return path.stream().toList();
    }

    public class Edge
    {
        public int src;
        public int des;
        public int weight;

        public Edge(int s, int d, int w)
        {
            src = s;
            des = d;
            weight = w;
        }
    }
}
