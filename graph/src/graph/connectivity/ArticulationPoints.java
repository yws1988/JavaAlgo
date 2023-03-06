package graph.connectivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArticulationPoints
{
    public static int step;

    public static boolean[] getAllArticulationPoints(ArrayList<Integer>[] graph)
    {
        step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        boolean[] isArticulationPoints = new boolean[v];
        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
        }

        DFS(0, graph, visited, dist, low, parents, isArticulationPoints);

        return isArticulationPoints;
    }

    public static void DFS(int s, ArrayList<Integer>[] graph, boolean[] vs, int[] dist, int[] low, int[] parents, boolean[] isArticulationPoints)
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
                DFS(c, graph, vs, dist, low, parents, isArticulationPoints);
                low[s] = Math.min(low[c], low[s]);
                if (parents[s] == -1 && child > 1)
                {
                    isArticulationPoints[s] = true;
                }

                if (parents[s] != -1 && low[c] >= dist[s])
                {
                    isArticulationPoints[s] = true;
                }
            }
            else if (parents[s] != c)
            {
                low[s] = Math.min(dist[c], low[s]);
            }
        }
    }

    public static boolean[] getAllArticulationPointsWithStack(List<Integer>[] graph)
    {
        step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        boolean[] isArticulationPoints = new boolean[v];
        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
        }

        DfsWithStack(0, graph, visited, dist, low, parents, isArticulationPoints);

        return isArticulationPoints;
    }

    public static void DfsWithStack(int root, List<Integer>[] graph, boolean[] visited, int[] dist, int[] low, int[] parents, boolean[] isArticulationPoints)
    {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(root);

        while (stack.size() > 0)
        {
            var p = stack.peek();

            if (!visited[p])
            {
                visited[p] = true;
                dist[p] = ++step;
                low[p] = step;
            }

            boolean isPop = true;

            for (var c : graph[p])
            {
                if (!visited[c])
                {
                    isPop = false;
                    stack.push(c);
                    parents[c] = p;
                }
            }

            if (isPop)
            {
                p = stack.pop();
                int child = 0;
                for (var c : graph[p])
                {
                    if (c!=parents[c])
                    {
                        child++;
                        low[p] = Math.min(dist[c], low[p]);
                    }
                }

                if(p == root && child > 1)
                {
                    isArticulationPoints[p] = true;
                }

                if(p != root && child > 0 && low[p]>=dist[p])
                {
                    isArticulationPoints[p] = true;
                }
            }
        }
    }

}

