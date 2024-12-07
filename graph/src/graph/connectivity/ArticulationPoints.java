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
        boolean[] areArticulationPoints = new boolean[v];
        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
        }

        DfsWithStack(0, graph, visited, dist, low, parents, areArticulationPoints);

        return areArticulationPoints;
    }

    static void DfsWithStack(int root, List<Integer>[] graph, boolean[] visited, int[] dist, int[] low, int[] parents, boolean[] areArticulationPoints)
    {
        Stack<Node> stackVertex = new Stack<>();
        stackVertex.push(new Node(root, 0));
        visited[root] = true;
        dist[root] = ++step;
        low[root] = step;
        int child = 0;

        while (stackVertex.size() > 0)
        {
            var currentNode = stackVertex.peek();
            var node = currentNode.node;
            var childIndex = currentNode.childIndex;

            boolean isPop = true;

            for (int i = childIndex; i < graph[node].size(); i++) {
                int childNode = graph[node].get(i);
                currentNode.setChildIndex(i+1);
                if(!visited[childNode]){
                    visited[childNode] = true;
                    dist[childNode] = ++step;
                    low[childNode] = step;

                    parents[childNode]=node;
                    stackVertex.push(new Node(childNode, 0));
                    low[node] = Math.min(dist[childNode], low[node]);
                    isPop = false;
                    break;
                }else if(parents[node]!=childNode){
                    low[node] = Math.min(dist[childNode], low[node]);
                }
            }

            if (isPop)
            {
                currentNode = stackVertex.pop();
                node = currentNode.node;

                if(node!=root){
                    int parentNode = parents[node];
                    low[parentNode]=Math.min(low[parentNode], low[node]);
                }

                if(parents[node] == root){
                    child++;
                    if(child>1){
                        areArticulationPoints[root]=true;
                    }
                }else if(node!=root && low[node]>=dist[parents[node]]){
                    areArticulationPoints[parents[node]]=true;
                }
            }
        }
    }

    public static class Node {
        public int node;
        public int childIndex;

        public Node(int x, int y) {
            this.node = x;
            this.childIndex = y;
        }

        public void setChildIndex(int childIndex) {
            this.childIndex = childIndex;
        }
    }

}

