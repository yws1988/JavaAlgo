package geometry.lines;
/*
The maze is described by two polylines. Each of them has N + 1 points.
The points of the first polyline are: (x0, y0), (x2, y2), ... , (xN, yN).
The points of the second polyline are (x0, y0 + H), (x2, y2 + H), ... , (xN, yN + H).
The entrance to the maze is a segment (x0, y0) - (x0, y0 + H) and the exit is a segment (x0, y0) - (xN, yN + H).
During your running in the maze you can be in any point inside the maze.

The faster you go through the maze - the more chances to survive you have.
You know description of the the maze. Find the length of the shortest path you have to run.

Input
The first line contains one integer N.
The following N + 1 lines contains 2 space-separated integers each - description of the first polyline.
The last line contains one integer H.

Output
Ouput one real number with exactly 10 digits after dot - the shortest distance you have to cover.
 */

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShortestPathBetweenTwoParallelPolylines {
    public static int[][] points;
    public static int n;
    public static int h;


    public static double getShortestPath()
    {
        n = points.length;
        double[][] dp = new double[n][2];

        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                dp[i][j] = Double.MAX_VALUE;
            }
        }

        for (int i = 1; i < n; i++)
        {
            int x = points[i][0];
            int y = points[i][1];
            int dd = x - points[0][0];

            if (checkHorizontalLineLeftToCurrent(i, y))
            {
                dp[i][0] = Math.min(dp[i][0], dd);
            }

            if (checkHorizontalLineLeftToCurrent(i, y+h))
            {
                dp[i][1] = Math.min(dp[i][1], dd);
            }
        }


        for (int i = 0; i < n-1; i++)
        {
            for (int m = 0; m < 2; m++)
            {
                for (int j = i+1; j < n; j++)
                {
                    for (int k = 0; k < 2; k++)
                    {
                        int x = points[i][0];
                        int y = points[i][1] + (m == 0 ? 0 : h);
                        int xRight = points[j][0];
                        int yRight = points[j][1] + (k == 0 ? 0 : h);

                        if (isTwoLineIntersected(i+1, j-1, x, y, xRight, yRight))
                        {
                            dp[j][k] = Math.min(dp[j][k], dp[i][m]+dis(x,y, xRight, yRight));
                        }
                    }
                }
            }
        }

        double dis = Math.min(dp[n-1][0], dp[n-1][1]);

        for (int i = 0; i < n; i++)
        {
            int xn = points[n-1][0];
            int x = points[i][0];
            int y = points[i][1];
            int dd = xn - x;

            if (checkHorizontalLineRightToEnd(i, y))
            {
                dis = Math.min(dis, dp[i][0] + dd);
            }

            if (checkHorizontalLineRightToEnd(i, y+h))
            {
                dis = Math.min(dis, dp[i][1] + dd);
            }
        }

        BigDecimal bigDecimal = new BigDecimal(dis);
        bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static int orientation(int x1, int y1, int x2, int y2, int x, int y)
    {
        double val = (y2 - y1) * (x - x2) - (x2 - x1) * (y - y2);

        if (val == 0) return 0;  // colinear

        // clock or counterclock wise
        return (val > 0) ? 1 : -1;
    }

    //LinePointHelper

    static boolean isTwoLineIntersected(int l, int r, int x, int y, int xr, int yr)
    {
        for (int i = l; i <= r; i++)
        {
            int res = orientation(x, y, xr, yr, points[i][0], points[i][1]) * orientation(x, y, xr, yr, points[i][0], points[i][1] + h);
            if (res > 0) return false;
        }

        return true;
    }

    static boolean checkHorizontalLineRightToEnd(int l, int y)
    {
        for (int i = l; i < n; i++)
        {
            if (y>points[i][1]+h || y < points[i][1])
            {
                return false;
            }
        }
        return true;
    }

    static boolean checkHorizontalLineLeftToCurrent(int l, int y)
    {
        for (int i = 0; i <= l; i++)
        {
            if (y>points[i][1]+h || y < points[i][1])
            {
                return false;
            }
        }
        return true;
    }

    static double dis(int x, int y, int xr, int yr)
    {
        return Math.sqrt(Math.pow(x - xr, 2) + Math.pow(y - yr, 2));
    }
}
