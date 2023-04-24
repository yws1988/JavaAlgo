package geometry.points;
/*
Find Simple Closed Path for a given set of points. Given a set of points, connect the dots without crossing.

Input: points[] = {(0, 3), (1, 1), (2, 2), (4, 4),
                   (0, 0), (1, 2), (3, 1}, {3, 3}};

Output: Connecting points in following order would
        not cause any crossing
       {(0, 0), (3, 1), (1, 1), (2, 2), (3, 3),
        (4, 4), (1, 2), (0, 3)}
 */

import math.utils.DoubleUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TheClosestPathOfPoints {
    public static Point[] GetClosestPath(Point[] points)
    {
         var startPoint = Arrays.stream(points).sorted(new PointComparator()).findFirst().get();
         var zeroPoints = Arrays.stream(points).filter(p -> p.x == startPoint.x).sorted(new PointComparator()).skip(1).collect(Collectors.toList());
         var restPoints = Arrays.stream(points).filter(p -> p.x != startPoint.x).collect(Collectors.toList());

        for (var item : restPoints)
        {
            double height = item.y - startPoint.y;
            double distanceX = item.x - startPoint.x;
            item.priority = height / distanceX;
        }

        var restPsPositif = restPoints.stream().filter(p->p.priority >=0).sorted(new PointPriorityComparator("asc", startPoint.x)).collect(Collectors.toList());
        var restPsNegatif = restPoints.stream().filter(p->p.priority < 0).sorted(new PointPriorityComparator("desc", startPoint.x)).collect(Collectors.toList());

        var list = List.of(startPoint);
        list.addAll(restPsPositif);
        list.addAll(zeroPoints);
        list.addAll(restPsNegatif);
        return list.toArray(new Point[0]);
    }


    public static class Point {
        public double x;
        public double y;
        public double priority;

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

    public static class PointPriorityComparator implements Comparator<Point>{
        public String order = "asc";
        public double startX;

        public PointPriorityComparator(String order, double startX) {
            this.order = order;
            this.startX = startX;
        }

        @Override
        public int compare(Point o1, Point o2) {
            if(order == "asc"){
                int y = DoubleUtil.compare(o1.priority,o2.priority);
                return y == 0 ? DoubleUtil.compare(o1.x, o2.x) : y;
            }else{
                int y = DoubleUtil.compare(o2.priority,o1.priority);
                return y == 0 ? DoubleUtil.compare(Math.abs(o1.x - startX), Math.abs(o1.x - startX)) : y;
            }
        }
    }
}
