package other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagicFunctionTest {

    @Test
    void getTimesToReachPointHalfOfN() {
        MagicFunction.fs = new int[]{0,2,4,3,2,1};
        MagicFunction.n = 5;
        MagicFunction.k = 3;

        assertEquals(15, MagicFunction.getTimesToReachPointHalfOfN());
    }
}