package graph.connectivity;

public class TransitiveClosureGraph
{
    // Warshall algorithme
    public static boolean[][] getByWarshallMethod(int[][] graph)
    {
        int v = graph.length;
        var TransitiveGraph = new boolean[v][v];
        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if(graph[i][j] == 1)
                {
                    TransitiveGraph[i][j] = true;
                }
            }
    }

    for (int i = 0; i < v; i++)
    {
        for (int j = 0; j < v; j++)
        {
            for (int k = 0; k < v; k++)
            {
                TransitiveGraph[j][k] = TransitiveGraph[j][k] || (TransitiveGraph[j][i] && TransitiveGraph[i][k]);
            }
        }
    }

    return TransitiveGraph;
}

    // DFS method to get transitive closure graph
    public static boolean[][] getTransitiveClosureGraphByDFS(int[][] graph)
    {
        int v = graph.length;
        var transitiveGraph = new boolean[v][v];
        for (int i = 0; i < v; i++)
        {
            var vs = new boolean[v];
            DFS(i, graph, transitiveGraph, v, vs, i);
        }

        return transitiveGraph;
    }

    static void DFS(int i, int[][] graph, boolean[][] transiveGraph, int v, boolean[] vs, int src)
    {
        vs[i] = true;
        transiveGraph[src][i] = true;
        for (int j = 0; j < v; j++)
        {
            if (graph[i][j]!=0 && !vs[j])
            {
                DFS(j, graph, transiveGraph, v, vs, src);
            }
        }
    }
}
