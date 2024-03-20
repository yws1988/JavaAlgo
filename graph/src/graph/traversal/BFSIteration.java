package graph.traversal;

import datastructures.tuple.Pair;

import java.util.*;

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

    /***
     * Get the shortest distance from vertex src to vertex des
     * @param graph
     * @param src
     * @param des
     * @param vs
     * @return -1 if is not reachable
     */
    public static int bfs(List<Integer>[] graph, int src, int des, Boolean[] vs)
    {
        var queue = new LinkedList<Pair>();
        queue.add(new Pair(src, 1));

        while (queue.size() > 0)
        {
            var p = queue.pop();
            for (var c : graph[p.x])
            {
                if (!vs[c])
                {
                    if (c == des) return p.y + 1;

                    vs[c] = true;
                    queue.add(new Pair(c, p.y + 1));
                }
            }
        }

        return -1;
    }

    /***
     * Get the shortest distance from vertex src to vertex des
     * @param graph
     * @param src
     * @param des
     * @param vs
     * @return -1 if is not reachable
     */
    public static int bfs(HashSet<Integer>[] graph, int src, int des, Boolean[] vs)
    {
        var queue = new LinkedList<Pair>();
        queue.add(new Pair(src, 1));

        while (queue.size() > 0)
        {
            var p = queue.pop();
            for (var c : graph[p.x])
            {
                if (!vs[c])
                {
                    if (c == des) return p.y + 1;

                    vs[c] = true;
                    queue.add(new Pair(c, p.y + 1));
                }
            }
        }

        return -1;
    }
}
