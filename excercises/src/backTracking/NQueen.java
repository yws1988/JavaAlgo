package backTracking;

public class NQueen{
    public static boolean start(int[][] g)
    {
        int n = g.length;

        // if g[i,j]==1 put the queen position in this cell
        return findPath(g, 0, n);
    }

    static boolean findPath(int[][] g, int column, int n)
    {
        if(column==n)
        {
            return true;
        }

        for (int i = 0; i < n; i++)
        {
            if(isValid(g, i, column, n))
            {
                g[i][column] = 1;

                if(findPath(g, column + 1, n))
                {
                    return true;
                }

                g[i][column] = 0;
            }
        }

        return false;
    }

    static boolean isValid(int[][] g, int x, int y, int n)
    {
        for (int i = 0; i < y; i++)
        {
            if (g[x][i] == 1) return false;
        }

        for (int i = 1; i <= y; i++)
        {
            if (x-i>=0 && g[x-i][y-i] == 1) return false;

            if (x + i < n && g[x + i][y - i] == 1) return false;
        }

        return true;
    }
}
