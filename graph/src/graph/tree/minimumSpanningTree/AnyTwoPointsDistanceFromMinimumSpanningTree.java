package graph.tree.minimumSpanningTree;

import java.util.*;
import java.util.stream.IntStream;

public class AnyTwoPointsDistanceFromMinimumSpanningTree
{
    public static Map<Key, Integer> getMinimumDistance(List<MinimumSpanningTree.Pair>[] graph)
    {
        int n = graph.length;

        MinimumSpanningTree.getMinimumSpanningTree(graph);
        List<MinimumSpanningTree.Pair> path = MinimumSpanningTree.path;

        List<MinimumSpanningTree.Pair>[] newGraph = (List<MinimumSpanningTree.Pair>[]) IntStream.range(0, n).mapToObj(idx -> new ArrayList<MinimumSpanningTree.Pair>()).toArray();

        var distances = new HashMap<Key, Integer>();

        for (int k = 0; k < n; k++)
        {
            var vs = new boolean[n];
            bfs(k, distances, newGraph, vs);
        }

        return distances;
    }

    static void bfs(int root, Map<Key, Integer> dic, List<MinimumSpanningTree.Pair>[] graph, boolean[] vs)
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
                if (!vs[pair.des])
                {
                    dic.put(new Key(root, pair.des), dic.get(new Key(root, pair.src)) + pair.weight);
                    queue.add(pair.des);
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