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
        int child = 0;

        while (stack.size() > 0)
        {
            var node = stack.peek();

            if(!visited[node]){
                visited[node] = true;
                dist[node] = ++step;
                low[node] = step;
            }

            boolean isPop = true;
            for (var childNode : graph[node])
            {
                if(!visited[childNode]){
                    parents[childNode]=node;
                    stack.push(childNode);
                    isPop = false;
                    break;
                }
            }

            if (isPop)
            {
                node = stack.pop();
                for (var childNode : graph[node])
                {
                    if(childNode!=parents[node]){
                        low[node] = Math.min(dist[childNode], low[node]);
                    }
                }

                if(node!=root){
                    int parentNode = parents[node];
                    low[parentNode]=Math.min(low[parentNode], low[node]);
                }

                if(parents[node] == root){
                    child++;
                }else if(node!=root && low[node]>=dist[parents[node]]){
                    isArticulationPoints[parents[node]]=true;
                }
            }
        }

        if(child>1){
            isArticulationPoints[root]=true;
        }
    }

}

