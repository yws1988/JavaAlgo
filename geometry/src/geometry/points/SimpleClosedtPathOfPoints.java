/*
    Find Simple Closed Path for a given set of points. Given a set of points, connect the dots without crossing.

    Input: points[] = {(0, 3), (1, 1), (2, 2), (4, 4),
                       (0, 0), (1, 2), (3, 1}, {3, 3}};

    Output: Connecting points in following order would
            not cause any crossing
           {(0, 0), (3, 1), (1, 1), (2, 2), (3, 3),
            (4, 4), (1, 2), (0, 3)}
 */
package geometry.points;

import datastructures.geometry.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleClosedtPathOfPoints {
    public static Point[] getClosestPath(Point[] points)
    {
        var startPoint = Arrays.stream(points).sorted((p1, p2)->{
            return (int)(p1.y -p2.y == 0 ? p1.x -p2.x : p1.y -p2.y);
        }).findFirst().get();
        var zeroPoints = Arrays.stream(points).filter(p -> p.x == startPoint.x).sorted((p1, p2) -> {return (int)(p1.y - p2.y);}).skip(1).collect(Collectors.toList());
        var restPoints = Arrays.stream(points).filter(p -> p.x != startPoint.x).collect(Collectors.toList());

        for (var item : restPoints)
        {
            double height = item.y - startPoint.y;
            double distanceX = item.x - startPoint.x;
            item.Priority = height / distanceX;
        }

        var restPsPositif = restPoints.stream().filter(p->p.Priority>=0).sorted((p1, p2)->{return (int)(p1.Priority-p2.Priority == 0 ? p1.x -p2.x : p1.Priority-p2.Priority);}).collect(Collectors.toList());
        var restPsNegatif = restPoints.stream().filter(p -> p.Priority < 0).sorted((p1, p2)->{return (int)(p2.Priority-p1.Priority == 0 ? Math.abs(p1.x -startPoint.x)-Math.abs(p2.x -startPoint.x) : p2.Priority-p1.Priority);}).collect(Collectors.toList());
        List<Point> list = List.of(startPoint);
        list.addAll(restPsPositif);
        list.addAll(zeroPoints);
        list.addAll(restPsNegatif);

        return list.toArray(new Point[0]);
    }
}
