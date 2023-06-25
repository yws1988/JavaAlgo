package graph.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSIteration {
    public static List<Integer> bfs(List<Integer>[] graph, int src)
    {
        var result = new ArrayList<Integer>();
        int v = graph.length;
        var visited = new boolean[v];

        var queue = new LinkedList<Integer>();
        queue.add(src);

        while (queue.size() > 0)
        {
            int next = queue.poll();
            visited[next] = true;
            result.add(next);

            for (int c : graph[next])
            {
                if (!visited[c])
                {
                    queue.add(c);
                }
            }
        }

        return result;
    }
}
