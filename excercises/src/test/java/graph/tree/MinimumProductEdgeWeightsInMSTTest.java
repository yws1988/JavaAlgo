package graph.tree;

import datastructures.tuple.Triple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class MinimumProductEdgeWeightsInMSTTest {

    @Test
    void getMinimumProductOfEdgeWeights() {
        List<Triple>[] graph = new List[3];
        graph[0] = Arrays.asList(new Triple(0, 1, 12), new Triple(0, 2, 18));
        graph[1] = Arrays.asList(new Triple(1, 0, 12), new Triple(1, 2, 15));
        graph[2] = Arrays.asList(new Triple(2, 1, 15), new Triple(2, 0, 18));

        long result = MinimumProductEdgeWeightsInMST.getMinimumProductOfEdgeWeights(graph);

        Assertions.assertEquals(180, result);
    }
}