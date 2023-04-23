package geometry.ranges;

// Get the Maximum Number Of Intersections For multiple intervals

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaximumNumOfIntersectionsForRanges {
    public static int getMaxNumOfIntersections(List<Pair> intervals)
    {
        var sortedPairs = intervals.stream().flatMap(interval ->
                        Stream.of(new Pair(interval.x, 1), new Pair(interval.y, -1)))
                .sorted(new PairComparator())
                .collect(Collectors.toList());

        int max = 0;
        int res = 0;

        for (var pair : sortedPairs) {
            res += pair.y;
            max = Math.max(max, res);
        }

        return max;
    }

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair clone(){
            return new Pair(x, y);
        }
    }

    public static class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x-o2.x;
        }
    }
}
