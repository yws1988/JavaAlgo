package geometry.points;

import datastructures.geometry.Point;

import java.util.*;
/*
    Given N point on a 2D plane as pair of (x, y) co-ordinates, we need to find maximum number of point
    which lie on the same line.

    Examples:

    Input : points[] = {-1, 1}, {0, 0}, {1, 1},
                        {2, 2}, {3, 3}, {3, 4}
    Output : 4
    Then maximum number of point which lie on same
    line are 4, those point are {0, 0}, {1, 1}, {2, 2},
    {3, 3}
 */
public class MaximumPointsInOneLine {
    public final double INF = Double.MAX_VALUE;

    public static int GetMax(Point[] ps)
    {
        var dicP = new HashMap<Double, Integer>();

        int num = ps.length;
        int maxPointsResult = 0;
        for (int i = 0; i < num; i++)
        {
            var verticalPoints = new HashSet<Integer>();
            for (int j = i+1; j < num; j++)
            {
                if(ps[j].x - ps[i].x == 0){
                    verticalPoints.add(i);
                    verticalPoints.add(j);
                }
                else{
                    double slop = (ps[j].y - ps[i].y) / (ps[j].x - ps[i].x);
                    if (dicP.containsKey(slop))
                    {
                        dicP.put(slop, dicP.get(slop)+1);
                    }
                    else
                    {
                        dicP.put(slop, 2);
                    }
                }
            }

            maxPointsResult = Math.max(verticalPoints.size(), dicP.values().stream().max(Comparator.naturalOrder()).get());

        }

        return maxPointsResult;
    }

    // use greatest common divisor for avoid double precision error
    public static int getMax(IntPoint[] points)
    {
        int n = points.length;
        var dic = new HashMap<IntPoint, Integer>();

        int max = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                int x = points[i].x - points[j].x;
                int y = points[i].y - points[j].y;

                if (x == 0)
                {
                    y = 1;
                }else if (y == 0)
                {
                    x = 1;
                }
                else
                {
                    int gcd = greatestCommonDivisor(Math.abs(x), Math.abs(y));
                    x /= gcd;
                    y /= gcd;
                    y = x * y < 0 ? -Math.abs(y) : Math.abs(y);
                    x = Math.abs(x);
                }

                var key = new IntPoint(x, y);
                if (dic.containsKey(key))
                {
                   dic.put(key, dic.get(key)+1);
                }
                else
                {
                    dic.put(key, 2);
                }

                max = Math.max(max, dic.get(key));
            }
            dic.clear();
        }

        return max;
    }

    public static int greatestCommonDivisor(int m, int n)
    {
        while (n > 0)
        {
            int t = n;
            n = m % n;
            m = t;
        }

        return m;
    }

    public static class IntPoint
    {
        public int x;
        public int y;

        public IntPoint(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}


