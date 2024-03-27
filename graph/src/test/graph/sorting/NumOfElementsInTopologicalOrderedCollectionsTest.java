package graph.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumOfElementsInTopologicalOrderedCollectionsTest {

    @Test
    void getMaximumNumOfElements() {

        List<Integer>[] subCollections = new List[]{List.of(1, 2), List.of(), List.of(3), List.of(1)};
        int[] numOfElements = {10, 10, 10, 2};

        int result = NumOfElementsInTopologicalOrderedCollections.getMaximumNumOfElements(subCollections, numOfElements, 4);

        Assertions.assertEquals(32, result);
    }
}