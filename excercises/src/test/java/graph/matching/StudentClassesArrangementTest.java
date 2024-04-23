package graph.matching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentClassesArrangementTest {

    @Test
    void getArrangements() {
        int n = 4;

        int[][] studentSlots = {{205,300}, {210,370}, {290,380}, {120,305}};

        int[] result = StudentClassesArrangement.getArrangements(n, studentSlots);

        assertEquals(new int[]{1, 2, 1, 1}, result);
    }
}