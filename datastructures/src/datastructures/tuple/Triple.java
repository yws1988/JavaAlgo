package datastructures.tuple;

import java.util.Comparator;

public class Triple{
    public int x;
    public int y;
    public int z;

    public Triple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static class TripleComparator implements Comparator<Triple> {
        @Override
        public int compare(Triple o1, Triple o2) {
            return o1.z-o2.z;
        }
    }
}
