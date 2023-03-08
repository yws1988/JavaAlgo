/*
    Give a char matrix n row*m column,  in the char grid it contains the following characters:
    '.' represents a crossable cell
    '#' represents a wall cell, is not crossable
    's' source crossable cell
    'd' destination crossable cell
    How many wall at least to break in order to connect source and destination?

    Input char grid like this:
    ##########
    s...#....#
    #####..###
    #......#.#
    ##########
    #..#..#..#
    #..#..####
    #..#.....#
    ########d#

   so the expected answer is 2.
 */

package graph.connectivity;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ConnectedAreasPathCostForSingleSourceInGrid
{
    static int n, m;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static int getShortestPath(String[] graph, int src, int des)
    {
        n = graph.length;
        m = graph[0].length();

        int v = n * m;
        var weights = new int[v];
        var parents = new int[v];

        for (int i = 0; i < v; i++)
        {
            if (i == src)
            {
                weights[i] = 0;
            }
            else
            {
                weights[i] = Integer.MAX_VALUE;
            }
        }

        return calculate(graph, src, des, v, weights, parents);
    }

    static int calculate(String[] graph, int src, int des, int v, int[] weights, int[] parents)
    {
        var visited = new boolean[v];
        var queue = new PriorityQueue<Node>((node1, node2)->{return node1.weight-node2.weight;});

        queue.add(new Node(src, 0));
        parents[src] = src;

        while (queue.size() > 0)
        {
            var node = queue.remove();
            int s = node.index;

            if (s == des)
            {
                return weights[des];
            }

            if (visited[s]) continue;
            visited[s] = true;

            int x = s / m;
            int y = s % m;

            for (int i = 0; i < 4; i++)
            {
                int tx = x + dx[i];
                int ty = y + dy[i];
                int d = tx * m + ty;

                if (isSafe(tx, ty) && !visited[d])
                {
                    int newWeight = weights[s];

                    if (graph[tx].charAt(ty) == '#')
                    {
                        newWeight += 1;
                    }

                    if (weights[d] > newWeight)
                    {
                        weights[d] = newWeight;
                        parents[d] = s;
                    }

                    queue.add(new Node(d, weights[d]));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static boolean isSafe(int tx, int ty)
    {
        return tx >= 0 && tx < n && ty >= 0 && ty < m;
    }

    public static class Node
    {
        public int weight;

        public int index;

        public Node(int index, int weight)
        {
            index = index;
            weight = weight;
        }
    }
}

