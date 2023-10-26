package graph.tree.binarytree;
/*
In a binary tree, find the lowest common ancestor for node u and v
 */

import java.util.List;

public class LowestCommonAncestorWithDpSolution
{
    static List<Integer>[] tree;
    static int n;
    static int level;
    static int[][] dp;
    static int[] depth;

    public static int getLowestCommonAncestor(int u, int v, List<Integer>[] treeP, int root)
    {
        tree = treeP;
        n = tree.length;
        level = (int)Math.ceil(Math.log(n)/Math.log(2)) + 1;
        dp = new int[n][level];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < level; j++)
            {
                dp[i][j] = -1;
            }
        }

        boolean[] vs = new boolean[n];
        depth = new int[n];
        dfs(root, -1, 0, vs);
        calDp();

        return Lca(u, v);
    }

    private static void dfs(int node, int parent, int level, boolean[] vs)
    {
        vs[node] = true;
        dp[node][0] = parent;
        depth[node] = level;

        for (var child : tree[node])
        {
            if (!vs[child])
            {
                dfs(child, node, level+1, vs);
            }
        }
    }

    private static void calDp()
    {
        for (int j = 1; j < level; j++)
        {
            for (int i = 1; i < n; i++)
            {
                if (dp[i][j - 1] != -1)
                {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
        }
    }

    private static int Lca(int u, int v)
    {
        if (depth[u] > depth[v])
        {
            int temp = u;
            u = v;
            v = temp;
        }

        int h = depth[v] - depth[u];

        for (int i = 0; i < level; i++)
        {
            if(((h>>i) & 1) == 1)
            {
                v = dp[v][i];
            }
        }

        if (u == v) return u;

        for (int i = level-1; i >= 0; i--)
        {
            if(dp[u][i]!=dp[v][i])
            {
                u = dp[u][i];
                v = dp[v][i];
            }
        }

        return dp[u][0];
    }
}
