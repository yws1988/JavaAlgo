package graph.connectivity;


import org.junit.jupiter.api.Test;
import utils.graph.GraphBuilder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KCoreGraphUndirectedGraphDFSSolutionTest {

    @Test
    void getKCoreVertex(){
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {1, 5}, {2, 3},
                        {2, 4}, {2, 5}, {2, 6},{3, 4},{3, 6},{3, 7},
                        {4, 6},{4, 7},{5, 6},{5, 8},{6, 7},{6, 8}};
        var adjacencyList = GraphBuilder.<Integer>buildListArray(9, edges, false, 0);

        var vertexes3 = KCoreGraphUndirectedGraphPriorityQueueSolution.getKCoreVertex(adjacencyList, 3);

        assertEquals(Arrays.asList( 2, 3, 4, 6, 7), vertexes3);
    }
}