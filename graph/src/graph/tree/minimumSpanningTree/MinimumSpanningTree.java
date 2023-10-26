package graph.tree.minimumSpanningTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class MinimumSpanningTree {
    public static List<Pair> path = new ArrayList<>();

    public static int getMinimumSpanningTree(List<Pair>[] graph)
    {
        int v = graph.length;
        int minCost = 0;
        boolean[] visited = new boolean[v];

        var queue = new PriorityQueue<Pair>();
        for (var item : graph[0])
        {
            queue.add(item);
        }

        visited[0] = true;
        int visitedNumOfNode = 1;
        while (queue.size() > 0)
        {
            var p = queue.poll();

            if (!visited[p.des])
            {
                path.add(p);
                minCost += p.weight;
                visited[p.des] = true;
                ++visitedNumOfNode;

                if (visitedNumOfNode == v)
                {
                    return minCost;
                }

                for (var item : graph[p.des])
                {
                    queue.add(item);
                }
            }
        }

        return minCost;
    }

    static void initialGraphWithOneEdge(int n, int s, int d, int w)
    {
        var graph = (ArrayList<Pair>[])IntStream.range(0, n).mapToObj(i -> new ArrayList<Pair>()).toArray();

        graph[s].add(new Pair(s, d, w));
        graph[d].add(new Pair(d, s, w));
    }


    public static class Pair{
        public int src;
        public int des;
        public int weight;

        public Pair(int src, int des, int weight) {
            this.src = src;
            this.des = des;
            this.weight = weight;
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.weight-o2.weight;
        }
    }
}
