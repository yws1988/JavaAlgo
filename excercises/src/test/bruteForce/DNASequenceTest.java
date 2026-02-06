package bruteForce;

import bruteForce.DNASequence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DNASequenceTest {

    @Test
    public void solveSequence(){
        String[] strs = {"AT", "G", "CC", "TAG"};

        DNASequence.solveSequence(strs);
    }

}