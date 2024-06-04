package graph.tree.lowestCommonAncestor;
/*
Given a tree with N nodes and N-1 edges, each edge has a weight.
Calculate the maximum weight of the edge in the path between node from u to v.
 */

import datastructures.tuple.Pair;

import java.util.List;
import java.util.Stack;

public class MaximumWeightInAnyPathOfTwoNodes{
    public static int n;
    static int level;
    static int[][] parentNode;
    static int[][] edgeWeight;
    static int[] depths;

    public static int getMaximumWeightsBetween(List<Pair>[] tree, int u, int v)
    {
        n = tree.length;
        level = (int)Math.ceil(Math.log(n)/Math.log(2)) + 1;
        parentNode = new int[n][level];
        edgeWeight = new int[n][level];
        depths = new int[n];
        Dfs(tree);
        calDp();

        return Lca(u, v);
    }

    private static void Dfs(List<Pair>[] tree)
    {
        int node, parent, depth, weight;
        boolean[] vs = new boolean[n];
        Stack<Node> stack = new Stack<Node>();
        stack.add(new Node(1, 0, 0, 0));
        while (stack.size() > 0)
        {
            var cNode = stack.pop();
            node = cNode.node;
            parent = cNode.parent;
            depth = cNode.depth;
            weight = cNode.weight;
            vs[node] = true;
            parentNode[node][0] = parent;
            edgeWeight[node][0] = weight;
            depths[node] = depth;

            for (var child : tree[node])
            {
                if (!vs[child.x])
                {
                    stack.add(new Node(child.x, node, depth + 1, child.y));
                }
            }
        }
    }

    private static void calDp()
    {
        for (int j = 1; j < level; j++)
        {
            for (int i = 1; i < n; i++)
            {
                parentNode[i][j] = parentNode[parentNode[i][j - 1]][j - 1];
                edgeWeight[i][j] = Math.max(edgeWeight[i][j - 1], edgeWeight[parentNode[i][j - 1]][j - 1]);
            }
        }
    }

    private static int Lca(int u, int v)
    {
        int maxE = 0;
        if (depths[u] > depths[v])
        {
            int temp = u;
            u = v;
            v = temp;
        }

        int h = depths[v] - depths[u];

        for (int i = 0; i < level; i++)
        {
            if (((h >> i) & 1) == 1)
            {
                maxE = Math.max(maxE, edgeWeight[v][i]);
                v = parentNode[v][i];
            }
        }

        if (u == v) return maxE;

        for (int i = level - 1; i >= 0; i--)
        {
            if (parentNode[u][i] != parentNode[v][i])
            {
                maxE = Math.max(maxE, Math.max(edgeWeight[u][i], edgeWeight[v][i]));
                u = parentNode[u][i];
                v = parentNode[v][i];
            }
        }

        return Math.max(maxE, Math.max(edgeWeight[u][0], edgeWeight[v][0]));
    }

    public static class Node {
        public int node;
        public int parent;
        public int depth;
        public int weight;

        public Node(int node, int parent, int depth, int weight) {
            this.node = node;
            this.parent = parent;
            this.depth = depth;
            this.weight = weight;
        }
    }
}
