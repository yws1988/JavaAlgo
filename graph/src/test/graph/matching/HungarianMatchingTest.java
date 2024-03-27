package graph.matching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HungarianMatchingTest {

    @Test
    void getMinCost() {
        var costs = new int[][]{{108, 125, 150}, {150, 135, 175}, {122, 148, 250}};

        int minCost = HungarianMatching.getMinCost(costs, 3);

        // 122 + 135 + 150 = 407
        assertEquals(407, minCost);
        assertArrayEquals(new int[]{2, 1, 0}, HungarianMatching.xy);
    }

    @Test
    void getMaxCost() {
        var costs = new int[][]{{108, 125, 150}, {150, 135, 175}, {122, 148, 250}};

        int maxCost = HungarianMatching.getMaxCost(costs, 3);

        // 150 + 125 + 250 = 525
        assertEquals(525, maxCost);
        assertArrayEquals(new int[]{1, 0, 2}, HungarianMatching.xy);
    }
}