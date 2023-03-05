package helpers;

import geometry.Range;

import java.util.ArrayList;
import java.util.Collection;
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
        Collections.sort(mergeIntervals, (range1, range2)->{return (range1.Min-range2.Min);});
        var newIntervals = intervals.(i => i.Min).ToList();

        var current = newIntervals[0];

        for (int i = 1; i < newIntervals.Count(); i++)
        {
            var next = newIntervals[i];
            if (current.Max >= next.Min)
            {
                current = new Range(current.Min, Math.Max(current.Max, next.Max));
            }
            else
            {
                mergeIntervals.Add(current);
                current = next;
            }
        }

        mergeIntervals.Add(current);

        return mergeIntervals;
    }
}
