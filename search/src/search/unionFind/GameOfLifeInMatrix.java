package search.unionFind;

/*
This challenge is based on the game of life, the principles of which you will find here:
https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

In this variant, at each step:
- when a cell has a living neighbor above and a living neighbor on the left: it becomes alive;
- when a cell has no living neighbor, neither above nor to the left: it dies;
- in the rest of the cases, it retains its state.

You must indicate the survival time of the population from an initial configuration. That is to say the number of steps after which there is no longer a living cell.

For convenience, the initial configuration will be described by a series of rectangles of living cells.

Input Data:

Line 1: an integer N between 1 and 1000 representing the number of rectangles.
Lines 2 to N + 1: four integers x1 y1 x2 y2 each between 1 and 1,000,000 and separated by spaces.
All the points included in the rectangle bounded by x1, y1 (top-left corner) and x2, y2 (bottom-right corner) are living cells.

There will be no more than 1,000,000 living cells to start with.

Exit
An integer representing the survival time of the population. If the population survives indefinitely, return -1.

Example

Give the followings 3 rectangles:

3
5 1 5 1
2 2 4 2
2 3 2 4

has a lifespan of 6.
*/

import datastructures.geometry.Rectangle;

import java.util.List;

public class GameOfLifeInMatrix {
    public static int n;

    public static int getLifeSpan(List<Rectangle> rectangles)
    {
        var sets = UnionFind.createSubsets(n);
        var minXY = new int[n];
        var maxX = new int[n];
        var maxY = new int[n];

        for (int i = 0; i < n; i++)
        {
            var rectangleX = rectangles.get(i);
            minXY[i] = rectangleX.x1 + rectangleX.y1;
            maxX[i] = rectangleX.x2;
            maxY[i] = rectangleX.y2;

            for (int j = 0; j < i; j++)
            {
                var rectangleY = rectangles.get(j);

                if (!(rectangleX.x2 + 1 < rectangleY.x1 || rectangleX.y2 + 1 < rectangleY.y1 || rectangleX.x1 - 1 > rectangleY.x2 || rectangleX.y1 - 1 > rectangleY.y2) &&
                        !(rectangleX.x2 + 1 == rectangleY.x1 && rectangleX.y2 + 1 == rectangleY.y1) &&
                        !(rectangleY.x2 + 1 == rectangleX.x1 && rectangleY.y2 + 1 == rectangleX.y1))
                {
                    int ii = UnionFind.find(sets, i);
                    int jj = UnionFind.find(sets, j);
                    UnionFind.union(sets, ii, jj);

                    int k = UnionFind.find(sets, ii);

                    minXY[k] = Math.min(Math.min(minXY[k], minXY[ii]), minXY[jj]);
                    maxX[k] = Math.max(Math.max(maxX[k], maxX[ii]), maxX[jj]);
                    maxY[k] = Math.max(Math.max(maxY[k], maxY[ii]), maxY[jj]);
                }
            }
        }

        int lifes = 0;

        for (int i = 0; i < n; i++)
        {
            int parent = UnionFind.find(sets, i);

            lifes = Math.max(lifes, maxX[parent] + maxY[parent] - minXY[parent] + 1);
        }

        return lifes;
    }


}
