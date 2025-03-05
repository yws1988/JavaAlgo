package graph.path;
/*
    Given a 3×3 board with 8 tiles(every tile has one number from 1 to 8) and
    one empty space.The objective is to place the numbers on tiles to match
    final configuration using the empty space.We can slide four adjacent
    (left, right, above and below) tiles into the empty space.

    Matrix from:
    1 2 3
    5 6 0
    7 8 4

    To:
    1 2 3
    5 8 6
    0 7 4

    Output the movement:
    1 2 3
    5 6 0
    7 8 4

    1 2 3
    5 0 6
    7 8 4

    1 2 3
    5 8 6
    7 0 4

    1 2 3
    5 8 6
    0 7 4
 */

import utils.ArrayHelper;
import utils.graph.MatrixHelper;

import java.util.*;

public class EightPuzzles {
    static int[][] src = new int[3][3], des = new int[3][3];
    public static int x, y;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static List<String> getPath() {
        var queue = new LinkedList<Node>();

        var set = new HashSet<String>();
        var srcStr = MatrixHelper.getHashString(src);
        var desStr = MatrixHelper.getHashString(des);
        set.add(srcStr);

        queue.add(new Node(src, x, y, Arrays.asList(srcStr)));

        while (queue.size() > 0) {
            var p = queue.pop();

            for (int i = 0; i < 4; i++) {
                int tx = dx[i] + p.X;
                int ty = dy[i] + p.Y;

                if (isSafe(tx, ty)) {
                    var newMatrix = ArrayHelper.clone(p.Matrix);
                    var newList = List.copyOf(p.Path);
                    var tmp = newMatrix[tx][ty];
                    newMatrix[tx][ty] =0;
                    newMatrix[p.X][p.Y] =tmp;

                    var hashKey = MatrixHelper.getHashString(newMatrix);

                    newList.add(hashKey);
                    if (!set.contains(hashKey)) {
                        queue.add(new Node(newMatrix, tx, ty, newList));
                    }

                    if (desStr == hashKey) {
                        return newList;
                    }
                }
            }
        }

        return null;
    }

    static boolean isSafe(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    public static class Node {
        public int[][] Matrix;
        public int X;
        public int Y;
        public List<String> Path;

        public Node(int[][] matrix, int x, int y, List<String> path) {
            Matrix = matrix;
            X = x;
            Y = y;
            Path = path;
        }
    }
}
