package graph.path;

import datastructures.graph.EdgeWithWeight;

/*
You are given a weighted directed graph some of whose vertices are marked as special vertices.
A valid path in it is a path which satisfies the following conditions:

1. For any two adjacent edges x and y on the path, 0.5*weight(x) <= weight(y) <= 2*weight(x)
2. The path contains exactly one special vertex.

Given two vertices src and des, your task is to calculate the minimum cost of a valid path between them.

For example, a graph 4 vertices and 4 edges in the graph.
With the following edges with weights:
1 2 1
2 3 1
3 4 1
1 3 1

the number of special vertices 4.
the source 1 and destination 4.

The lowest cost is 2.
 */

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumCostOfValidPath {
    public static int getMinimumCost(int src, int des, List<EdgeWithWeight>[] graph, boolean[] specialVertice)
    {
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        HashSet<Step> set = new HashSet<>();

        queue.add(new Node(src, 0, 0, specialVertice[src]));

        while (queue.size() > 0)
        {
            var node = queue.poll();
            int s = node.src;

            if (s == des && node.containsSpecialVertice)
            {
                return node.totalWeight;
            }

            set.add(new Step(s, node.totalWeight, node.containsSpecialVertice));
            int fromWeight = node.fromWeight;

            for (int i = 0; i < graph[s].size(); i++)
            {
                int desVertice = graph[s].get(i).des;
                var currentWeight = graph[s].get(i).weight;

                if (fromWeight > 0 && (fromWeight * 0.5 > currentWeight || fromWeight * 2 < currentWeight)) continue;
                if (specialVertice[s] && specialVertice[desVertice]) continue;

                if (specialVertice[s] || specialVertice[desVertice])
                {
                    if (!set.contains(new Step(desVertice, node.totalWeight + currentWeight, true)))
                    {
                        queue.add(new Node(desVertice, currentWeight, node.totalWeight + currentWeight, true));
                    }
                }
                else
                {
                    if (!set.contains(new Step(desVertice, node.totalWeight + currentWeight, false)))
                    {
                        queue.add(new Node(desVertice, currentWeight, node.totalWeight + currentWeight, false));
                    }
                }
            }
        }

        return -1;
    }

    public static class Node implements Comparable<Node>
    {
        public int src;
        public int fromWeight;
        public int totalWeight;
        public boolean containsSpecialVertice;

        public Node(int src, int fromWeight, int totalWeight, boolean containsSpecialVertice) {
            this.src = src;
            this.fromWeight = fromWeight;
            this.totalWeight = totalWeight;
            this.containsSpecialVertice = containsSpecialVertice;
        }

        @Override
        public int compareTo(Node o) {
            return this.totalWeight -o.totalWeight;
        }
    }

    public static class Step {
        public int src;
        public int totalWeight;
        public boolean containsSpecialVertice;

        public Step(int src, int totalWeight, boolean containsSpecialVertice) {
            this.src = src;
            this.totalWeight = totalWeight;
            this.containsSpecialVertice = containsSpecialVertice;
        }
    }
}
