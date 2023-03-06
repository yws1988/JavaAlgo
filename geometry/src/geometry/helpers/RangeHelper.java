package geometry.helpers;

import datastructures.geometry.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RangeHelper {
    public static boolean isIntersect(Range one, Range two)
    {
        double min = one.Min > two.Min ? one.Min : two.Min;
        double max = one.Max < two.Max? one.Max : two.Max;
        if (min<=max)
        {
            return true;
        }

        return false;
    }

    public static Range intersect(Range one, Range two)
    {
        double min = one.Min > two.Min ? one.Min : two.Min;
        double max = one.Max < two.Max? one.Max : two.Max;
        if (min<=max)
        {
            return new Range(min, max);
        }

        return null;
    }

    public static List<Range> MergeRanges(List<Range> intervals)
    {
        List<Range> mergeIntervals = new ArrayList<>();
        Collections.sort(mergeIntervals, (range1, range2)->{return ((int)(range1.Min-range2.Min));});

        var current = mergeIntervals.get(0);

        for (int i = 1; i < mergeIntervals.size(); i++)
        {
            var next = mergeIntervals.get(i);
            if (current.Max >= next.Min)
            {
                current = new Range(current.Min, Math.max(current.Max, next.Max));
            }
            else
            {
                mergeIntervals.add(current);
                current = next;
            }
        }

        mergeIntervals.add(current);

        return mergeIntervals;
    }
}
