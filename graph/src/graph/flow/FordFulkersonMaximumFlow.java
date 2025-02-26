package graph.flow;

/*
FordFulkersonMaximumFlowProblem

https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
*/


import java.util.LinkedList;


public class FordFulkersonMaximumFlow
{
    public static int getMaximumFlow(int[][] rGraph, int src, int des)
    {
        //rGraph is residual graph

        int v = rGraph.length;

        int maxFlow = 0;
        int[] parent = new int[v];
        while (isReachableByBFS(rGraph, src, des, parent))
        {
            int p = parent[des];
            int c = des;
            int minFlow = rGraph[p][c];
            while (p != src)
            {
                c = p;
                p = parent[c];
                minFlow = Math.min(minFlow, rGraph[p][c]);
            }

            for (c = des; c != src; c = parent[c]) {
                p = parent[c];
                rGraph[p][c] -= minFlow;
                rGraph[c][p] += minFlow;
            }

            maxFlow += minFlow;
        }

        return maxFlow;
    }

    static boolean isReachableByBFS(int[][] graph, int src, int des, int[] parent)
    {
        int v = graph.length;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(src);
        boolean[] visited = new boolean[v];
        visited[src] = true;

        while (queue.size() > 0)
        {
            int p = queue.remove();
            for (int i = 0; i < v; i++)
            {
                if (!visited[i] && graph[p][i] > 0)
                {
                    queue.add(i);
                    visited[i] = true;
                    parent[i] = p;
                    if (i == des)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

