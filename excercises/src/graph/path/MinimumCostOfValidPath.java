package graph.path;

import datastructures.graph.EdgeWithWeight;
import datastructures.tuple.Triple;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

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

public class MinimumCostOfValidPath {
    public static int getMinimumCost(int src, int des, List<EdgeWithWeight>[] graph, boolean[] specialVertice)
    {
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        HashSet<Triple> set = new HashSet<Triple>();
        set.add(new Triple(1, 2, 3));

        queue.add(new Node(src, 0, 0, specialVertice[src]));

        while (queue.size() > 0)
        {
            var node = queue.poll();
            int s = node.src;

            if (s == des && node.isSpecialVertice)
            {
                return node.toWeight;
            }

            int fromWeight = node.fromWeight;
            set.add(new Triple(s, fromWeight, csp));

            for (int i = 0; i < es[s].Count; i++)
            {
                var des = es[s][i].d;
                var nw = es[s][i].w;

                if (fromWeight > 0 && (fromWeight * 0.5 > nw || fromWeight * 2 < nw)) continue;
                if (csp == 1 && sp[des] == 1) continue;

                if (csp == 1 || sp[des] == 1)
                {
                    if (!set.Contains(new ValueTuple<int,int,int>(des, nw, 1)))
                    {
                        queue.Enqueue(new Node(des, nw, node.toWeight + nw, 1));
                    }
                }
                else
                {
                    if (!set.Contains(new ValueTuple<int, int, int>(des, nw, 0)))
                    {
                        queue.Enqueue(new Node(des, nw, node.toWeight + nw, 0));
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
        public int toWeight;
        public boolean isSpecialVertice;

        public Node(int src, int cw, int tw, boolean isSpecialVertice) {
            this.src = src;
            this.fromWeight = cw;
            this.toWeight = tw;
            this.isSpecialVertice = isSpecialVertice;
        }

        @Override
        public int compareTo(Node o) {
            return this.toWeight -o.toWeight;
        }
    }
}
