package other;

import datastructures.tuple.Pair;

import java.util.ArrayList;

public class TowerDefenseInChessGame {
    private static int n = 8, roiRow, roiColumn;
    public static String[] board;

    public static void solve()
    {
        var tourPositions = new ArrayList<Pair>();

        for (var i = 0; i < n; i++)
            for (var j = 0; j < n; j++)
                if (board[i].charAt(j) == 'T')
                {
                    tourPositions.add(new Pair(i, j));
                }
                else if (board[i].charAt(j) == 'R')
                {
                    roiRow = i;
                    roiColumn = j;
                }

        boolean isEchec = tourPositions.stream().anyMatch(s -> s.x == roiRow || s.y == roiColumn);

        for (var i = -1; i < 2; i++)
            for (var j = -1; j < 2; j++)
            {
                if (i == 0 && j == 0) continue;
                var tr = roiRow + i;
                var tc = roiColumn + j;
                if (isSafe(tr, tc))
                {
                    var isStepOk = true;
                    for (var item : tourPositions)
                    if (!(item.x == tr && item.y == tc))
                        if (item.x == tr || item.y == tc)
                            isStepOk = false;

                    if (isStepOk)
                    {
                        System.out.println("still-in-game");
                        return;
                    }
                }
            }

        System.out.println(isEchec ? "check-mat" : "pat");
    }

    private static boolean isSafe(int row, int column)
    {
        if (row >= 0 && row < 8 && column >= 0 && column < 8) return true;

        return false;
    }

}
