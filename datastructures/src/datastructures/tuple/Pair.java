package datastructures.tuple;

import java.util.Comparator;
public class Pair{
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair clone(){
        return new Pair(x, y);
    }

    public static class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x-o2.x;
        }
    }
}


