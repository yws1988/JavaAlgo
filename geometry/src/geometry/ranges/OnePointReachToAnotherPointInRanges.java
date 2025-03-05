package geometry.ranges;

/*
  There are n points from number 1 to N, and there are m ranges,each range represents as (L, R)
  1=<L<R<=n. Given a point A, judge if A can reach to N with the merge of ranges
 */

import datastructures.tuple.Pair;

import java.util.Collections;
import java.util.List;

public class OnePointReachToAnotherPointInRanges {
    public static boolean isReachable(List<Pair> pairs, int a, int n){
        Collections.sort(pairs, new Pair.PairComparator());

        for (Pair pair : pairs) {
            if(a >=pair.x && a <=pair.y){
                a = pair.y;
            }
        }

        return a==n;
    }
}
