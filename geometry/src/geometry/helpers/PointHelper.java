package geometry.helpers;

import datastructures.geometry.Point;

public class PointHelper {
    public static int orientationOfThreePoints(Point p1, Point p2, Point p3)
    {
        double val = (p2.Y - p1.Y) * (p3.X - p2.X) - (p2.X - p1.X) * (p3.Y - p2.Y);

        if (val == 0) return 0;  // colinear

        // clock or counterclock wise
        return (val > 0) ? 1 : 2;
    }

    public static Point rotatePoint(Point pointToRotate, Point centerPoint, double angleInDegrees)
    {
        double angleInRadians = angleInDegrees * (Math.PI / 180);
        double cosTheta = Math.cos(angleInRadians);
        double sinTheta = Math.sin(angleInRadians);
        return new Point
                (
                        cosTheta * (pointToRotate.X - centerPoint.X) - sinTheta * (pointToRotate.Y - centerPoint.Y) + centerPoint.X,
                        sinTheta * (pointToRotate.X - centerPoint.X) + cosTheta * (pointToRotate.Y - centerPoint.Y) + centerPoint.Y
                );
    }

    public static double getTwoPointsDistance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow((p1.X - p2.X), 2) + Math.pow((p1.Y - p2.Y), 2));
    }
}
