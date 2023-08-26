package graph.traversal;
/*
 * Given two dimension matrix [n, n], Iteration all the elements, the direction of
 * the iteration can just go up, right, bottom, left of the adjencent elements, output
 * the iteration path.
 * Go Up(output ^)
 * Go Bottom(output v)
 * Go Right(output >)
 * Go Left(output <)
 */
public class PrintTransversalPathInMatrix {
    public static String fromTopToBottom(int[][] matrix)
    {
        StringBuilder sb = new StringBuilder();

        int n = matrix.length;
        int startRow = 0, col = 0, direction = 1;

        while (startRow < n)
        {
            if (col + direction >= 0 && col + direction < n)
            {
                sb.append(direction == 1 ? ">" : "<");
                col += direction;
            }
            else
            {
                if (startRow < n - 1)
                {
                    sb.append("v");
                }

                direction = -direction;
                startRow++;
            }
        }

        return sb.toString();
    }

    public static String fromBottomToTop(int[][] matrix, int direction)
    {
        StringBuilder sb = new StringBuilder();

        int n = matrix.length;
        int startRow = n - 1;
        int col = direction == 1 ? 0 : n-1;
        while (startRow >= 0)
        {
            if (col + direction >= 0 && col + direction < n)
            {
                sb.append(direction == 1 ? ">" : "<");
                col += direction;
            }
            else
            {
                if (startRow > 0)
                {
                    sb.append("^");
                }

                direction = -direction;
                startRow--;
            }
        }

        return sb.toString();
    }
}
