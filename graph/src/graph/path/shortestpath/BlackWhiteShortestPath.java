package graph.path.shortestpath;

/*
You are given a graph G consisting of N nodes and M edges. Each node of G is either colored in black or white. Also, each edge of G
has a particular weight. Find the least expensive path between node 1 and N, such that difference of the number
of black nodes and white nodes on the path is no more than 1.

It is guaranteed graph G does not consist of multiple edges and self loops.

Output a single integer denoting the length of the optimal path fulfilling the requirements. Print -1 if there is no such path.

Example for a graph 6 node and 6 edges, 1 is black, 0 is white:

6 6
1 2 1
2 3 1
1 4 2
4 3 2
3 5 2
5 6 3
1 1 1 0 0 0
 */

import datastructures.tuple.Pair;

import java.util.*;

public class BlackWhiteShortestPath {
    public static int getShortestPath(List<Node>[] graph, int[] color)
    {
        int n = graph.length;
        PriorityQueue<Node> nodes = new PriorityQueue<Node>(new NodeComparator());
        nodes.add(new Node(1, 0, color[0]));

        Set<Pair> vs = new HashSet<>();
        vs.add(new Pair(color[0], 1));

        while (nodes.size() > 0)
        {
            var node = nodes.poll();
            int index = node.index;
            int weight = node.weight;
            int difference = node.difference;

            vs.add(new Pair(difference, index));

            if (index == n && Math.abs(difference) <= 1)
            {
                return weight;
            }

            for (var childNode : graph[index])
            {
                int childNodeIndex = childNode.index;
                int childNodeWeight = childNode.weight;
                int cDifference = difference + color[childNodeIndex - 1];
                if (!vs.contains(new Pair(cDifference, childNodeIndex)))
                {
                    nodes.add(new Node(childNodeIndex, childNodeWeight+weight, cDifference));
                }
            }
        }

        return -1;
    }


    public static class Node
    {
        public int index;

        public int weight;

        public int difference;

        public Node(int index, int weight, int difference)
        {
            this.index = index;
            this.weight = weight;
            this.difference = difference;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.weight-o2.weight;
        }
    }
}
