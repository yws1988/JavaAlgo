package graph.sorting;

import java.util.List;
import java.util.Stack;

public class TopologicalSorting {
    public static Stack<Integer> getSortingOrder(List<Integer>[] graph)
    {
        var stack = new Stack<Integer>();
        int n = graph.length;
        var vs = new boolean[n];
        for (int i = 0; i < n; i++)
        {
            if (!vs[i])
            {
                topologicalSortingUtil(graph, i, vs, stack);
            }
        }

        return stack;
    }

    static void topologicalSortingUtil(List<Integer>[] graph, int i, boolean[] vs, Stack<Integer> stack)
    {
        vs[i] = true;

        for (int c : graph[i])
        {
            if (!vs[c])
            {
                topologicalSortingUtil(graph, c, vs, stack);
            }
        }

        stack.push(i);
    }
}
