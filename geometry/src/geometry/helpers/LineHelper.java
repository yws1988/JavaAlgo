package geometry.helpers;

import datastructures.geometry.Line;
import datastructures.geometry.Point;
import datastructures.geometry.Range;

public class LineHelper {
    public static Line getLineFromTwoPoint(Point p1, Point p2)
    {
        double a = p2.y - p1.y;
        double b = p1.x - p2.x;
        double c = -a * (p1.x) - b * (p1.y);
        return new Line(a, b, c);
    }

    public static boolean isTwoLineIntersect(Point p1, Point p2, Point q1, Point q2)
    {
        if ((PointHelper.orientationOfThreePoints(p1, p2, q1) != PointHelper.orientationOfThreePoints(p1, p2, q2)) && (PointHelper.orientationOfThreePoints(q1, q2, p1) != PointHelper.orientationOfThreePoints(q1, q2, p2)))
            return true;

        var rangeX1 = new Range(p1.x, p2.x);
        var rangeX2 = new Range(q1.x, q2.x);
        var rangeY1 = new Range(p1.y, p2.y);
        var rangeY2 = new Range(q1.y, q2.y);

        if (PointHelper.orientationOfThreePoints(p1, p2, q1) == 0 && PointHelper.orientationOfThreePoints(p1, p2, q2) == 0 && RangeHelper.isIntersect(rangeX1, rangeX2) && RangeHelper.isIntersect(rangeY1,rangeY2))
        {
            return true;
        }

        return false;
    }

    public static Point getIntersectPoint(Point A, Point B, Point C, Point D)
    {
        // Line AB represented as a1x + b1y = c1
        Line abLine = getLineFromTwoPoint(A, B);
        double a1 = abLine.A;
        double b1 = abLine.B;
        double c1 = -abLine.C;

        // Line CD represented as a2x + b2y = c2
        Line cdLine = getLineFromTwoPoint(C, D);
        double a2 = cdLine.A;
        double b2 = cdLine.B;
        double c2 = -cdLine.C;

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0)
        {
            return null;
        }
        else
        {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            return new Point(x, y);
        }
    }
}
