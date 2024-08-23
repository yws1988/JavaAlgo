package datastructures.tuple;

import java.util.Comparator;

public class TripleDouble implements Comparable<TripleDouble>{
    public int x;
    public int y;
    public int z;
    public double value;

    public TripleDouble(int x, int y, int z, double value) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.value = value;
    }

    @Override
    public int compareTo(TripleDouble o) {
        return (int) Math.signum(this.value-o.value);
    }
}
