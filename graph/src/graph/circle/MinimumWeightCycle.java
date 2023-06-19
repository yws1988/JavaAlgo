package graph.circle;
/*
Given positive weighted undirected graph, find minimum weight cycle in it.

https://www.geeksforgeeks.org/find-minimum-weight-cycle-undirected-graph/
 */

import graph.path.shortestpath.DijsktraShortestPath;

public class MinimumWeightCycle {
    public static int getMinimumWeightCycle(int[][] graph)
    {
        int v = graph.length;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < v; i++)
        {
            for (int j = i+1; j < v; j++)
            {
                if (graph[i][j] > 0)
                {
                    int temp = graph[i][j];
                    graph[i][j] = 0;
                    result=Math.min(result, DijsktraShortestPath.getShortestPath(graph, i, j)+temp);
                    graph[i][j] = temp;
                }
            }
        }

        return result;
    }
}
