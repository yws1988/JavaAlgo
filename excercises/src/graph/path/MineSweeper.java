package graph.path;

public class MineSweeper
{
    static String[][] strs;
    static int H, L;

    public static int calculate(int startX, int startY)
    {
        strs = new String[H][L];
        int result = 0;
        for (int i = 0; i < H; i++)
        {
            for (int j = 0; j < L; j++)
            {
                if (strs[i][j] == "x")
                {
                    startX = i;
                    startY = j;
                }

                convertArray(i, j);
            }
        }

        int total = getNum(startX, startY);

        return result;
    }

    // d represents number cell, v represents visited cell, . represents a empty cell
    static void convertArray(int x, int y)
    {
        for (int i = x-1; i <= x + 1; i++)
        {
            for (int j = y-1; j <= y + 1; j++)
            {
                if (x < 0 || y < 0 || x >= H || y >= L) continue;
                if (strs[i][j] != "*" && strs[i][j]!="x" && strs[i][j]!=".")
                {
                    strs[i][j] = "d";
                }
            }
        }
    }


    static int getNum(int x, int y)
    {
        if (strs[x][y] == "v") return 0;
        if (strs[x][y] == "*" || strs[x][y] == "d")
        {
            strs[x][y] = "v";
            return 1;
        }
        int total = 1;
        strs[x][y] = "v";

        for (int i = x - 1; i <= x + 1; i++)
        {
            for (int j = y - 1; j <= y + 1; j++)
            {
                if (x < 0 || y < 0 || x >= H || y >= L) continue;
                if (strs[i][j] != "v")
                {
                    total += getNum(i, j);
                }
            }
        }
        return total;
    }
}
