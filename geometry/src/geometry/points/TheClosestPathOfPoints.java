package geometry.points;

import math.utils.DoubleUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TheClosestPathOfPoints {
    public static Point[] GetClosestPath(Point[] points)
    {
         var startPoint = Arrays.stream(points).sorted(new PointComparator()).findFirst().get();
         var zeroPoints = Arrays.stream(points).filter(p -> p.x == startPoint.x).sorted(new PointComparator()).skip(1).collect(Collectors.toList());
         var restPoints = Arrays.stream(points).filter(p -> p.x != startPoint.x).collect(Collectors.toList());

        for (var item : restPoints)
        {
            double height = item.y - startPoint.x;
            double distanceX = item.y - startPoint.x;
            item.Priority = height / distanceX;
        }

//        var restPsPositif = restPoints.Where(p=>p.Priority>=0).OrderBy(p=>p.Priority).ThenBy(p=> p.X);
//        var restPsNegatif = restPoints.Where(p => p.Priority < 0).OrderByDescending(p => p.Priority).ThenBy(p => Math.Abs(p.X - startPoint.X));

//        return new Point[] { startPoint }.Concat(restPsPositif).Concat(zeroPoints).Concat(restPsNegatif).ToArray();
    }


    public static class Point {
        public double x;
        public double y;
        public double Priority;

        public Point()
        {
        }

        public Point(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public static class PointComparator implements Comparator<Point>{
        @Override
        public int compare(Point o1, Point o2) {
            int y = DoubleUtil.compare(o1.y,o2.y);
            return y == 0 ? DoubleUtil.compare(o1.x, o2.x) : y;
        }
    }
}
