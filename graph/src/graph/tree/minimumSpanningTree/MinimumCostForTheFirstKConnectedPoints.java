package graph.tree.minimumSpanningTree;
/*
 * Give a graph two dimension matrix, graph[u, v] represents the cost between vertex u and v.
 * Output the minimum cost of all the first k vertex in the graph that the k vertex are connected.
 * Minimum costs to connect the first k points in the graph
*/

import datastructures.tuple.Triple;
import graph.path.shortestpath.WarshallShortestPath;
import utils.graph.GraphLIstBuilder;

public class MinimumCostForTheFirstKConnectedPoints {
    public static int getMinimumCost(int[][] graph, int k)
    {
        int[][] path = new int[graph.length][];
        var costs = WarshallShortestPath.getShortestCostsAndPath(graph, path);

        var newGraph = GraphLIstBuilder.<Triple>buildListArray(k);

        for (int a = 0; a < k; a++)
        {
            for (int b = a + 1; b < k; b++)
            {
                newGraph[a].add(new Triple(a, b, costs[a][b]));
                newGraph[b].add(new Triple(b, a, costs[a][b]));
            }
        }

        return MinimumSpanningTree.getMinimumSpanningTree(newGraph);
    }
}
