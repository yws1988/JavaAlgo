package graph.matching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximumCommonNeighboursAssignmentTest {

    @Test
    void printMaxCommonNeighboursMatching() {
        int[] rows = {1, 2, 3};
        int[] columns = {4, 5};

        int[][] relations = {{1, 3}, {4, 5}, {1, 5}, {1, 2}};

        String assignments = MaximumCommonNeighboursAssignment.printMaxCommonNeighboursMatching(rows, columns, relations);

        assertEquals("1 4,2 5", assignments);
    }
}