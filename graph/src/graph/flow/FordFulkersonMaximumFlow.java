package graph.flow;

/*
FordFulkersonMaximumFlowProblem

https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
*/


import java.util.LinkedList;


public class FordFulkersonMaximumFlow
{
    public static int getMaximunFlow(int[][] rGraph, int s, int d)
    {
        //rGraph is residual graph

        int v = rGraph.length;

        int maxFlow = 0;
        int[] parent = new int[v];
        while (isReachableByBFS(rGraph, s, d, parent))
        {
            int p = parent[d];
            int c = d;
            int minFlow = rGraph[p][c];
            while (p != s)
            {
                c = p;
                p = parent[c];
                minFlow = Math.min(minFlow, rGraph[p][c]);
            }

            p = parent[d];
            c = d;
            do
            {
                rGraph[p][c] -= minFlow;
                rGraph[c][p] += minFlow;
                c = p;
                p = parent[c];
            } while (p != s);

            maxFlow += minFlow;
        }

        return maxFlow;
    }

    static boolean isReachableByBFS(int[][] graph, int s, int d, int[] parent)
    {
        int v = graph.length;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean[] visited = new boolean[v];
        visited[s] = true;

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
                    if (i == d)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

