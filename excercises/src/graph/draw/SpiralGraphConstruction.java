package graph.draw;

public class SpiralGraphConstruction {
    public static void draw(int n)
    {
        char[][] chars = new char[n][n];
        int cX = n / 2;
        int cY = n / 2;
        chars[cX][cY] = '#';
        int[][] ds = {{ 1,0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }};
        int dir = 3;
        cX += ds[dir][0];
        cY += ds[dir][1];

        int len = 2;
        while (true)
        {
            if (cX < 0 || cX >= n || cY < 0 || cY >= n) break;
            dir++;
            if (dir >= 4) dir %= 4;
            for (int h = 1; h <= len; h++)
            {
                chars[cX][cY] = '#';
                cX += ds[dir][0];
                cY += ds[dir][1];
            }
            len++;
        }

        for(int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.println(chars[i][j] != '#' ? "=" : "#");
            }

            System.out.println();
        }
    }
}
