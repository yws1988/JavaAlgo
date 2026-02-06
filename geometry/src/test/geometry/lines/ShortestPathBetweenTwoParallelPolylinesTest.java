package geometry.lines;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static geometry.lines.ShortestPathBetweenTwoParallelPolylines.*;

class ShortestPathBetweenTwoParallelPolylinesTest {

    @Test
    void getShortestPath() {
        points = new int[][]{
                {0, 0}, {1,1}, {4, -2}
        };

        h = 2;

        double result = ShortestPathBetweenTwoParallelPolylines.getShortestPath();

        Assertions.assertEquals(4.162, result);
    }
}