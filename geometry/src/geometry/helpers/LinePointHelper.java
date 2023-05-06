package geometry.helpers;

import datastructures.geometry.Line;
import datastructures.geometry.Point;

public class LinePointHelper
{
    public static boolean isPointInLine(Point l1, Point l2, Point p)
    {
        if (PointHelper.orientationOfThreePoints(l1, l2, p) == 0 && p.x >= Math.min(l1.x, l2.x) && p.x <= Math.max(l1.x, l2.x) && p.y>= Math.min(l1.y, l2.y) && p.y<=Math.max(l1.y, l2.y))
        {
            return true;
        }

        return false;
    }

    public static Point getIntersectPoint(Line abLine, Line cdLine)
    {
        // Line AB represented as a1x + b1y = c1
        double a1 = abLine.A;
        double b1 = abLine.B;
        double c1 = -abLine.C;

        // Line CD represented as a2x + b2y = c2
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

    public static double getPointToLineDistance(Line line, Point p)
    {
        // line ax+by+c=0
        double slop = 0;
        double a, b, c;
        if (line.A == 0)
        {
            a = 1;
            b = 0;
            c = p.x;
        }
        else
        {
            slop = line.B / line.A;
            a = slop;
            b = -1;
            c = -(a * p.x + b * p.y);
        }

        var verticalLine = new Line(a, b, c);
        var intersectPoint = getIntersectPoint(line, verticalLine);
        var result = Math.pow(intersectPoint.y - p.y, 2) + Math.pow(intersectPoint.x - p.x, 2);
        return Math.sqrt(result);
    }

    public static Line getBestApproximateLine(Point[] ps)
    {
        int n = ps.length;
        double m, c, sum_x = 0, sum_y = 0,
                sum_xy = 0, sum_x2 = 0;

        for (int i = 0; i < n; i++)
        {
            sum_x += ps[i].x;
            sum_y += ps[i].y;
            sum_xy += ps[i].x * ps[i].y;
            sum_x2 += Math.pow(ps[i].x, 2);
        }

        m = (n * sum_xy - sum_x * sum_y) / (n * sum_x2 - Math.pow(sum_x, 2));

        c = (sum_y - m * sum_x) / n;

        return new Line(m,-1,c);
    }
}
