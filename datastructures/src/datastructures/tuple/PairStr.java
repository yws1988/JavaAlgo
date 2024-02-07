package datastructures.tuple;

import java.util.Comparator;

public class PairStr {
    public String x;
    public String y;

    public PairStr(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public PairStr clone(){
        return new PairStr(x, y);
    }
}


