package graph.flow;
/*
A Bipartite Graph is a graph whose vertices can be divided into two independent sets,
U and V such that every edge (u, v) either connects a vertex from U to V or a vertex from V to U. In other words,
for every edge (u, v), either u belongs to U and v to V, or u belongs to V and v to U.
We can also say that there is no edge that connects vertices of same set.

https://www.geeksforgeeks.org/maximum-bipartite-matching/
 */

// Java program to find out whether a given graph is Bipartite or not
import java.util.*;
import java.lang.*;
import java.io.*;

public class BiPartiteGraph
{
    public static boolean isGraphBipartite(List<Integer>[] graph)
    {
        int n = graph.length;
        int[] color = new int[n];

        var queue = new LinkedList<Integer>();
        queue.add(0);
        color[0] = 1;

        while (queue.size() > 0)
        {
            int p=queue.poll();
            for (var c : graph[p])
            {
                if(color[c] == 0)
                {
                    queue.add(c);
                    color[c] = color[p] == 1 ? 2 : 1;
                }else if(color[c] == color[p])
                {
                    return false;
                }
            }
        }

        return true;
    }
}


