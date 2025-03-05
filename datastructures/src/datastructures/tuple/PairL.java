package datastructures.tuple;

import java.util.Comparator;

public class PairL {
    public long x;
    public long y;

    public PairL(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public PairL clone(){
        return new PairL(x, y);
    }

    public static class PairComparator implements Comparator<PairL> {

        @Override
        public int compare(PairL o1, PairL o2) {
            return (int) Math.signum(o1.x-o2.x);
        }
    }
}


