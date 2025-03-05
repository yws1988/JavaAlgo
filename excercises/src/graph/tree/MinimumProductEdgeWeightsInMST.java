package graph.tree;
/*
Given a connected graph, with N vertices and M edges, each edge has a weight,
Output the minimum product value of all the edges which make the graph still be connected.
Example input N=3, M=3
3 3
0 1 12
1 2 15
2 0 18

Output: 180
 */

import datastructures.tuple.Triple;
import datastructures.tuple.TripleDouble;
import graph.tree.minimumSpanningTree.MinimumSpanningTree;
import utils.graph.GraphLIstBuilder;

import java.util.List;

public class MinimumProductEdgeWeightsInMST {
    public static long getMinimumProductOfEdgeWeights(List<Triple>[] graph){
        int n = graph.length;

        var graphWithDoubleWeight = GraphLIstBuilder.<TripleDouble>buildListArray(n);

        for (int i = 0; i < n; i++) {
            for (Triple triple : graph[i]){
                graphWithDoubleWeight[i].add(new TripleDouble(triple.x, triple.y, triple.z, Math.log10(triple.z)));
            }
        }

        MinimumSpanningTree.getMinimumSpanningTreeWithDoubleWeight(graphWithDoubleWeight);
        List<TripleDouble> pathD = MinimumSpanningTree.pathD;

        long result = 1;
        for (var item : pathD)
        {
            result = (result * item.z) % 1000000007;
        }

        return result;
    }
}
