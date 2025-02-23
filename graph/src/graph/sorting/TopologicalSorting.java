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

    public static int[] topologicalSort(List<Integer>[] graph) {
        int len = graph.length;
        int[] counter = new int[len];
        for (int i = 0; i < len; i++) {
            for (int child : graph[i]) counter[child]++;
        }

        int[] ret = new int[len];
        int q = 0;

        // sources
        for (int i = 0; i < len; i++) {
            if (counter[i] == 0) ret[q++] = i;
        }

        for (int p = 0; p < q; p++) {
            for (int child : graph[ret[p]]) {
                if (--counter[child] == 0) ret[q++] = child;
            }
        }
        // loop
        for (int i = 0; i < len; i++) {
            if (counter[i] > 0) return null;
        }
        return ret;
    }

}
