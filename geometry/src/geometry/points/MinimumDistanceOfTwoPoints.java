package geometry.points;
/// <summary>
/// Given an array of n points in the panel, and the problem is to find out the
// closest pair of points in the array with divde and conquer method.
/// </summary>

import datastructures.geometry.Point;
import math.utils.DoubleUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import geometry.helpers.PointHelper;

class MinimumDistanceOfTwoPoints
{
    public static double getMinimumDistanceOfTwoPoints(List<Point> ps)
    {
        var orderedPs = ps.stream().sorted(new PointComparator()).collect(Collectors.toList()); //nlogn
        return getClosestDistance(orderedPs);
    }

    static double getClosestDistance(List<Point> ps)
    {
        int n = ps.size();
        if (n > 3)
        {
            var psLeft = ps.subList(0, (n + 1) / 2);
            var psRight = ps.subList((n + 1) / 2+1, n+1);
            double minDistance =  Double.MAX_VALUE;
            final double mD = Math.min(getClosestDistance(psLeft), getClosestDistance(psRight));
            var mP = ps.get((n + 1) / 2);
            var strip = ps.stream().filter(p -> Math.abs(p.x - mP.x) < mD).collect(Collectors.toList()); //nlogn
            int nS = strip.size();

            for (int i = 0; i < nS; i++) //n
            {
                for (int j = i+1; j < nS && Math.abs(strip.get(i).y-strip.get(j).y)<mD; j++)
                {
                    minDistance = Math.min(mD, PointHelper.getTwoPointsDistance(strip.get(i), strip.get(j)));
                }
            }

            return minDistance;
        }
        else
        {
            return getMinPointsDistance(ps);
        }
    }

    static double getMinPointsDistance(List<Point> ps)
    {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < ps.size(); i++)
        {
            for (int j = i + 1; j < ps.size(); j++)
            {
                min=Math.min(min, PointHelper.getTwoPointsDistance(ps.get(i), ps.get(j)));
            }
        }

        return min;
    }

    public static class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return DoubleUtil.compare(o1.y,o2.y);
        }
    }
}
