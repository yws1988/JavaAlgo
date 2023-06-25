package graph.path.shortestpath;

import java.util.List;
import java.util.Stack;

public class WarshallShortestPath {
    public static final int MaxValue = Integer.MAX_VALUE;
    public static int[][] getShortestCostsAndPath(int[][] graph, int[][] path)
    {
        int v = graph.length;
        path = new int[v][v];
        var costs = new int[v][v];

        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if (i != j)
                {
                    costs[i][j] = graph[i][j] == 0 ? MaxValue : graph[i][j];
                }

                if (costs[i][j] != MaxValue)
                {
                    path[i][j] = i;
                }
                else
                {
                    path[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < v; k++)
        {
            for (int i = 0; i < v; i++)
            {
                for (int j = 0; j < v; j++)
                {
                    if (costs[i][k] != MaxValue && costs[k][j] != MaxValue)
                    {
                        if (costs[i][j] > costs[i][k] + costs[k][j])
                        {
                            costs[i][j] = costs[i][k] + costs[k][j];
                            path[i][j] = path[k][j];
                        }
                    }
                }

                if (costs[i][i] < 0)
                {
                    System.out.println("There is a negative cycle");
                    return null;
                }
            }
        }

        return costs;
    }

    public static List<Integer> getPath(int start, int end, int[][] path)
    {
        if (path[start][end] == -1)
        {
            System.out.println("No Path");
            return null;
        }

        var stack = new Stack<Integer>();
        stack.push(end);
        while (end != start)
        {
            stack.push(path[start][end]);
            end = path[start][end];
        }

        return stack.stream().toList();
    }
}
