package datastructures.tuple;

public class Triple implements Comparable<Triple>{
    public int x;
    public int y;
    public int z;

    public Triple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int compareTo(Triple o) {
        return this.z-o.z;
    }
}
