package datastructures.geometry;

import java.util.Comparator;

public class Range implements Comparator<Range> {
    public double Min;
    public double Max;

    public Range(double min, double max) {
        Min = min;
        Max = max;
    }

    @Override
    public int compare(Range o1, Range o2) {
        int sign = (int) Math.signum(o1.Min - o2.Min);
        return sign ==  0 ? (int) Math.signum(o1.Max - o2.Max) : sign;
    }
}
