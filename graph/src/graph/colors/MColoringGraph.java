package graph.colors;
/*
m Coloring Problem | Backtracking-5
Given an undirected graph and a number m, determine if the graph can be colored with at most m colors such that no two adjacent vertices of the graph are colored with same color. Here coloring of a graph means assignment of colors to all vertices.

Input:
1) A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is adjacency matrix representation of the graph. A value graph[i][j] is 1 if there is a direct edge from i to j, otherwise graph[i][j] is 0.
2) An integer m which is maximum number of colors that can be used.

Output:
An array color[V] that should have numbers from 1 to m. color[i] should represent the color assigned to the ith vertex. The code should also return false if the graph cannot be colored with m colors.
 */

public class MColoringGraph {
    public static boolean assignColors(int[][] g, int colorNum, int[] colors)
    {
        int n = g.length;
        colors = new int[n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 1; j <= colorNum; j++)
            {
                colors[i] = j;
                if (isSafe(g, n, i, colors))
                {
                    break;
                }

                if (j == colorNum)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isSafe(int[][] g, int n, int v, int[] colors)
    {
        for (int i = 0; i < n; i++)
        {
            if (i == v) continue;
            if(g[v][i]==1 && colors[v] == colors[i])
            {
                return false;
            }
        }

        return true;
    }
}
