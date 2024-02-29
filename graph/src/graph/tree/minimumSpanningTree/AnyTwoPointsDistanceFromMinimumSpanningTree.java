package graph.tree.minimumSpanningTree;

import datastructures.tuple.Triple;

import java.util.*;
import java.util.stream.IntStream;

public class AnyTwoPointsDistanceFromMinimumSpanningTree
{
    public static Map<Key, Integer> getMinimumDistance(List<Triple>[] graph)
    {
        int n = graph.length;

        MinimumSpanningTree.getMinimumSpanningTree(graph);
        List<Triple> path = MinimumSpanningTree.path;

        List<Triple>[] newGraph = (List<Triple>[]) IntStream.range(0, n).mapToObj(idx -> new ArrayList<Triple>()).toArray();

        var distances = new HashMap<Key, Integer>();

        for (int k = 0; k < n; k++)
        {
            var vs = new boolean[n];
            bfs(k, distances, newGraph, vs);
        }

        return distances;
    }

    static void bfs(int root, Map<Key, Integer> dic, List<Triple>[] graph, boolean[] vs)
    {
        var queue = new LinkedList<Integer>();
        queue.add(root);
        dic.put(new Key(root, root), 0);

        while (queue.size() > 0)
        {
            var currentNode = queue.pop();
            vs[currentNode] = true;

            for (var pair : graph[currentNode])
            {
                if (!vs[pair.y])
                {
                    dic.put(new Key(root, pair.y), dic.get(new Key(root, pair.x)) + pair.z);
                    queue.add(pair.y);
                }
            }
        }
    }

    public static class Key{
        public int x;
        public int y;

        public Key(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
