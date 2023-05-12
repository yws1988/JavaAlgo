package geometry.helpers;

import datastructures.geometry.Point;

import java.util.Arrays;

public class PolygonHelper
{
    /// <summary>
    /// We can compute area of a polygon using Shoelace formula.
    /// Vertex axis x values, axis y values
    /// </summary>
    public static double getPolygonArea(double[] xs, double[] ys, int n)
    {

        // Initialze area
        double area = 0.0;

        // Calculate value of shoelace formula
        int j = n-1;

        for (int i = 0; i < n; i++)
        {
            area += (xs[j] + xs[i]) * (ys[j] - ys[i]);

            // j is previous vertex to i
            j = i;
        }

        // Return absolute value
        return Math.abs(area/2);
    }

    public static boolean isPointInsidePolygon(Point[] vetexes, Point p)
    {
        double maxX = Arrays.stream(vetexes).map(v->v.x).max(Double::compare).get()+1;
        int n = vetexes.length;
        for (int i = 0; i < n; i++)
        {
            int next = i != n - 1 ? i + 1 : 0;
            if (LinePointHelper.isPointInLine(vetexes[i], vetexes[next], p))
            {
                return true;
            }
        }

        double y = p.y;
        Point newP = new Point(maxX, y);

        for (int i = 0; i <= n; i++)
        {
            y += i;
            final double yc = y;
            newP = new Point(maxX, y);
            if (Arrays.stream(vetexes).anyMatch(v -> LinePointHelper.isPointInLine(p, new Point(maxX, yc), v))){
                continue;
            }
            break;
        }

        int count = 0;

        for (int i = 0; i < n; i++)
        {
            int next = i != n - 1 ? i + 1 : 0;
            if (LineHelper.isTwoLineIntersect(vetexes[i], vetexes[next], p, newP))
            {
                count++;
            }
        }

        return count % 2 == 1 ? true : false;
    }
}
