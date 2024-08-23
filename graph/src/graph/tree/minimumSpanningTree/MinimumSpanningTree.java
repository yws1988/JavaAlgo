package graph.tree.minimumSpanningTree;

/***
 * Triple in the graph: x = src, y = des, z = weight
 */

import datastructures.tuple.Triple;
import datastructures.tuple.TripleDouble;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class MinimumSpanningTree {
    public static List<Triple> path = new ArrayList<>();

    public static int getMinimumSpanningTree(List<Triple>[] graph)
    {
        int v = graph.length;
        int minCost = 0;
        boolean[] visited = new boolean[v];

        var queue = new PriorityQueue<Triple>();
        for (var item : graph[0])
        {
            queue.add(item);
        }

        visited[0] = true;
        int visitedNumOfNode = 1;
        while (queue.size() > 0)
        {
            var p = queue.poll();

            if (!visited[p.y])
            {
                path.add(p);
                minCost += p.z;
                visited[p.y] = true;
                ++visitedNumOfNode;

                if (visitedNumOfNode == v)
                {
                    return minCost;
                }

                for (var item : graph[p.y])
                {
                    queue.add(item);
                }
            }
        }

        return minCost;
    }

    public static List<TripleDouble> pathD = new ArrayList<>();

    public static double getMinimumSpanningTreeWithDoubleWeight(List<TripleDouble>[] graph)
    {
        int v = graph.length;
        double minCost = 0;
        boolean[] visited = new boolean[v];

        var queue = new PriorityQueue<TripleDouble>();
        for (var item : graph[0])
        {
            queue.add(item);
        }

        visited[0] = true;
        int visitedNumOfNode = 1;
        while (queue.size() > 0)
        {
            var p = queue.poll();

            if (!visited[p.y])
            {
                pathD.add(p);
                minCost += p.z;
                visited[p.y] = true;
                ++visitedNumOfNode;

                if (visitedNumOfNode == v)
                {
                    return minCost;
                }

                for (var item : graph[p.y])
                {
                    queue.add(item);
                }
            }
        }

        return minCost;
    }

    static void initialGraphWithOneEdge(int n, int s, int d, int w)
    {
        var graph = (ArrayList<Triple>[])IntStream.range(0, n).mapToObj(i -> new ArrayList<Triple>()).toArray();

        graph[s].add(new Triple(s, d, w));
        graph[d].add(new Triple(d, s, w));
    }
}
