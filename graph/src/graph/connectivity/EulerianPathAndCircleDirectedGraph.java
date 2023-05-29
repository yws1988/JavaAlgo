package graph.connectivity;
/*
   A directed graph has an eulerian cycle if following conditions are true

    All vertices with nonzero degree belong to a single strongly connected component.
    In degree is equal to the out degree for every vertex.
 */

import java.util.*;
import java.util.LinkedList;

// This class represents a directed graph using adjacency list
class EulerianPathAndCircleDirectedGraph
{
    private int V; // No. of vertices
    private LinkedList<Integer> adj[];//Adjacency List
    private int in[];		 //maintaining in degree


    /* This function returns true if the directed graph has a eulerian
    cycle, otherwise returns false */
    Boolean isEulerianCycle(List<Integer>[] graph)
    {
        V= graph.length;
        in = new int[V];
        for (int i = 0; i < V; i++) {
            for (var item : graph[i]){
                in[item]++;
            }
        }
        // Check if all non-zero degree vertices are connected
        if (!StronglyConnectedGraph.isStronglyConnected(graph))
            return false;

        // Check if in degree and out degree of every vertex is same
        for (int i = 0; i < V; i++)
            if (adj[i].size() != in[i])
                return false;

        return true;
    }
}

