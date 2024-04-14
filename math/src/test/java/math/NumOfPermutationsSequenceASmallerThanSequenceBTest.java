package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumOfPermutationsSequenceASmallerThanSequenceBTest {

    @Test
    void getNum() {
        int[] sequenceA = {1,2,2,4};
        int[] sequenceB = {2,4,2,1};

        long result = NumOfPermutationsSequenceASmallerThanSequenceB.getNum(sequenceA, sequenceB);

        assertEquals(8,  result);
        // 3 + 2 + 2 + 1
    }
}