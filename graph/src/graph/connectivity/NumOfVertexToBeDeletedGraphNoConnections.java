package graph.connectivity;

/*
 * Give a graph, every time delete the vetex with maximum degrees,
 * how many vetexes should be deleted so that the graph has no edges.
 */

import datastructures.trees.SegmentTreeCompare;

import java.util.Arrays;
import java.util.HashSet;

public class NumOfVertexToBeDeletedGraphNoConnections {
    public static int getNumOfVetexToBeDeleted(HashSet<Integer>[] graph)
    {
        var arr = Arrays.stream(graph).mapToInt(s -> s.size()).toArray();

        var segmentTree = new SegmentTreeCompare(arr, (node1, node2) -> {
            if (node1 == null) return node2;
            if (node2 == null) return node1;
            return node1.compareTo(node2) > 0 ? node1 : node2;
        });

        int numOfVertex = 0;

        while (segmentTree.tree[0].value > 0)
        {
            numOfVertex++;
            var node = segmentTree.tree[0];

            int u = node.index;
            var neighbours = graph[u];

            for (int v : neighbours)
            {
                graph[v].remove(u);
                segmentTree.update(v, graph[v].size());
            }

            graph[u].clear();
            segmentTree.update(u, 0);
        }

        return numOfVertex;
    }
}
