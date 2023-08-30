package graph.connectivity;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class KCoreGraphUndirectedGraphDFSSolutionTest {

    @Test
    void getKCoreVertex() {
        var adjacencyList =(ArrayList<Integer>[])IntStream.range(0, 9).mapToObj(s -> new ArrayList<Integer>()).toArray();

        adjacencyList[0].add(1);
        adjacencyList[0].add(2);
        adjacencyList[1].add(2);
        adjacencyList[1].add(5);
        adjacencyList[2].add(3);
        adjacencyList[2].add(4);
        adjacencyList[2].add(5);
        adjacencyList[2].add(6);
        adjacencyList[3].add(4);
        adjacencyList[3].add(6);
        adjacencyList[3].add(7);
        adjacencyList[4].add(6);
        adjacencyList[4].add(7);
        adjacencyList[5].add(6);
        adjacencyList[5].add(8);
        adjacencyList[6].add(7);
        adjacencyList[6].add(8);


        var vertexes3 = KCoreGraphUndirectedGraphPriorityQueueSolution.getKCoreVertex(adjacencyList, 3);
        assertEquals(Arrays.asList( 2, 4, 6, 7), vertexes3);

//        var vertexes4 = KCoreGraphUndirectedGraphPriorityQueueSolution.getKCoreVertex(adjacencyList, 4);
//        var vertexes6 = KCoreGraphUndirectedGraphPriorityQueueSolution.getKCoreVertex(adjacencyList, 6);
//        var vertexes7 = KCoreGraphUndirectedGraphPriorityQueueSolution.getKCoreVertex(adjacencyList, 7);


//        assertEquals(Arrays.asList( 2, 3, 6, 7), vertexes4);
//        assertEquals(Arrays.asList( 2, 3, 4, 7), vertexes6);
//        assertEquals(Arrays.asList( 3, 4, 6), vertexes7);
    }
}