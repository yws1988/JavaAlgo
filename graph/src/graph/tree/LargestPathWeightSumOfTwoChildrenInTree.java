package graph.tree;

/*
 * Given a directional tree, the tree node is numbered 0 to n, 0 is the root node of the tree,
 * each node of tree has a weight and each node just has one parent.
 * Get the largest sum of the path weights among at most two of the children.
 * (Similar questions: Maximum Path sum in a N-ary Tree. Find maximum path sum between any two nodes)
 */

import java.util.Arrays;
import java.util.List;

public class LargestPathWeightSumOfTwoChildrenInTree {

    public static int getLargestSum(List<Pair>[] graph)
    {
        int n = graph.length;
        var dp = new int[n];
        calculateMaxWeight(graph, 0, dp);

        var dpmax = new int[n];
        calculateTwoLargestChildrenSum(graph, 0, dpmax, dp);

        return Arrays.stream(dpmax).max().getAsInt();
    }
    static int calculateMaxWeight(List<Pair>[] graph, int root, int[] dp)
    {
        if (graph[root].size() == 0)
        {
            return dp[root] = 0;
        }

        for (var child : graph[root])
        {
            dp[root] = Math.max(dp[root], calculateMaxWeight(graph, child.index, dp) + child.weight);
        }

        return dp[root];
    }

    static void calculateTwoLargestChildrenSum(List<Pair>[] graph, int root, int[] dpmax, int[] dp)
    {
        int max = 0;
        int secondMax = 0;

        for (var child : graph[root])
        {
            calculateTwoLargestChildrenSum(graph, child.index, dpmax, dp);

            int childSum = dp[child.index] + child.weight;
            if (max <= childSum)
            {
                secondMax = max;
                max = childSum;
            }
        }

        dpmax[root] = max + secondMax;
    }

    public static class Pair{
        public int index;
        public int weight;

        public Pair(int x, int y) {
            this.index = x;
            this.weight = y;
        }
    }
}
