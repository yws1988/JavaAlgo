package graph.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSIteration {
    public static List<Integer> dfs(List<Integer>[] graph, int src)
    {
        var result = new ArrayList<Integer>();
        int v = graph.length;
        var visited = new boolean[v];

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(src);

        while (stack.size() > 0)
        {
            int next = stack.pop();
            visited[next] = true;
            result.add(next);

            for (int c : graph[next])
            {
                if (!visited[c])
                {
                    stack.push(c);
                }
            }
        }

        return result;
    }
}
