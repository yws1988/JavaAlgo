package graph.path.shortestpath;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class DijsktraShortestPath
{
    public static int getShortestPath(int[][] graph, int src, int des)
    {
        int v = graph.length;
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

        return calculate(graph, src, des, v, weights, parents);
    }

    static int calculate(int[][] graph, int src, int des, int v, int[] weights, int[] parents)
    {
        var visited = new boolean[v];
        var queue = new PriorityQueue<Node>(new NodeComparator());

        queue.add(new Node(src, 0));
        parents[src] = src;

        while (queue.size() > 0)
        {
            var node = queue.poll();
            int s = node.index;

            if (s == des)
            {
                return weights[des];
            }

            if (visited[s]) continue;
            visited[s] = true;

            for (int i = 0; i < v; i++)
            {
                if (!visited[i] && graph[s][i] > 0)
                {
                    if (weights[i] > weights[s] + graph[s][i])
                    {
                        weights[i] = weights[s] + graph[s][i];
                        parents[i] = s;
                    }

                    queue.add(new Node(i, weights[i]));
                }
            }
        }

        return Integer.MAX_VALUE;
    }


    public static int[] getShortestPath(int[][] graph, int src)
    {
        int v = graph.length;
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

        calculate(graph, src, v, weights, parents);

        //var path = GetShortestPathForEveryNode(src, des, parents);
        return weights;
    }

    static void calculate(int[][] graph, int src, int v, int[] weights, int[] parents)
    {
        var visited = new boolean[v];
        var queue = new PriorityQueue<Node>(new NodeComparator());

        queue.add(new Node(src, 0));
        parents[src] = src;

        while (queue.size() > 0)
        {
            var node = queue.poll();
            int s = node.index;

            if (visited[s]) continue;
            visited[s] = true;

            for (int i = 0; i < v; i++)
            {
                if (!visited[i] && graph[s][i] > 0)
                {
                    if (weights[i] > weights[s] + graph[s][i])
                    {
                        weights[i] = weights[s] + graph[s][i];
                        parents[i] = s;
                    }

                    queue.add(new Node(i, weights[i]));
                }
            }
        }
    }

    static void calculate(List<Node>[] graph, int src, int v, int[] weights, int[] parents)
    {
        var visited = new boolean[v];
        var queue = new PriorityQueue<Node>(new NodeComparator());

        queue.add(new Node(src, 0));
        parents[src] = src;

        while (queue.size() > 0)
        {
            var nodeInfo = queue.poll();
            int s = nodeInfo.index;
            if (visited[s]) continue;
            visited[s] = true;

            for (int i = 0; i < graph[s].size(); i++)
            {
                var e = graph[s].get(i);
                if (!visited[e.index])
                {
                    if (weights[e.index] > weights[s] + e.weight)
                    {
                        weights[e.index] = weights[s] + e.weight;
                        parents[e.index] = s;
                    }

                    queue.add(new Node(e.index, weights[s]));
                }
            }
        }
    }

    public List<Integer> getShortestPathForEveryNode(int src, int des, int[] parents)
    {
        Stack<Integer> path = new Stack<Integer>();

        int p = des;
        path.push(p);
        do
        {
            path.push(parents[p]);
            p = parents[p];
        } while (p != src);

        return path.stream().toList();
    }

    public static class Node
    {
        public int weight;

        public int index;

        public Node(int index, int weight)
        {
            this.index = index;
            this.weight = weight;
        }
    }

    public static class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.weight-o2.weight;
        }
    }
}
