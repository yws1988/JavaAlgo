package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

class ArrayHelperTest {

    @Test
    void generatePermutations() {
        var nums = new ArrayList<>(IntStream.rangeClosed(1, 4).boxed().toList());

        ArrayHelper.generatePermutations(4, nums);

        Assertions.assertEquals(24, ArrayHelper.allPermutations.size());
    }
}
