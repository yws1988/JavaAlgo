package graph.path.longestpath;

//Given a Weighted Directed Acyclic Graph(DAG) and a source vertex s in it,
//find the longest distances from s to all other vertices in the given graph.

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class LongestPathInDirectedAcyclicGraph
{
    /// <summary>
    /// Get all the longest path from source s to all the other paths
    /// </summary>
    /// <param name="graph"></param>
    /// <param name="s">Source vertex</param>
    /// <returns></returns>
    public static int[] getLongestPath(List<EdgeNode>[] graph, int s, int[] parents)
    {
        var stack = new Stack<Integer>();
        int n = graph.length;
        topologicalSorting(graph, stack);
        int[] weights = new int[n];
        Arrays.setAll(weights, i -> Integer.MIN_VALUE);
        weights[s] = 0;
        parents = new int[n];
        Arrays.setAll(parents, i -> -1);

        do
        {
            int p = stack.pop();
            if (weights[p] != Integer.MIN_VALUE)
            {
                for (EdgeNode edgeNode : graph[p])
                {
                    if (weights[p] + edgeNode.weight > weights[edgeNode.des])
                    {
                        weights[edgeNode.des] = weights[p] + edgeNode.weight;
                        parents[edgeNode.des] = p;
                    }
                }
            }
        } while (stack.size() > 0);

        return weights;
    }

    static void topologicalSorting(List<EdgeNode>[] graph, Stack<Integer> stack)
    {
        int n = graph.length;
        var vs = new boolean[n];
        for (int i = 0; i < n; i++)
        {
            if (!vs[i])
            {
                topologicalSortingUtil(graph, i, vs, stack);
            }
        }
    }

    static void topologicalSortingUtil(List<EdgeNode>[] graph, int i, boolean[] vs, Stack<Integer> stack)
    {
        vs[i] = true;

        for (EdgeNode edgeNode : graph[i])
        {
            if (!vs[edgeNode.des])
            {
                topologicalSortingUtil(graph, edgeNode.des, vs, stack);
            }
        }

        stack.push(i);
    }

    public static class EdgeNode
    {
        public int des;
        public int weight;

        public EdgeNode(int d, int w)
        {
            des = d;
            weight = w;
        }
    }
}
