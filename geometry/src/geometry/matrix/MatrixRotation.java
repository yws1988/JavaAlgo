package geometry.matrix;

/*
 Give a matrix rotate 90 or 180 degree the matrix to get another matrix

 For example matrix:
 1 2 3
 4 5 6
 7 8 9

 After rotation 90 degree clockwise:
 7 4 1
 8 5 2
 9 6 3

 After rotation 180 degree clockwise:
 9 8 7
 6 5 4
 3 2 1

 After rotation 90 degree counterclockwisse
 3 6 9
 2 5 8
 1 4 7
 */

public class MatrixRotation{
    public static int[][] rotateRight(int[][] matrix)
    {
        int n = matrix.length;
        var newMatrix = new int[n][n];
        int maxIndex = n - 1;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                newMatrix[i][ j] = matrix[maxIndex - j][ i];
            }
        }

        return newMatrix;
    }

    public static int[][] rotateLeft(int[][] matrix)
    {
        int n = matrix.length;
        var newMatrix = new int[n][n];
        int maxIndex = n - 1;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                newMatrix[i][ j] = matrix[j][ maxIndex - i];
            }
        }

        return newMatrix;
    }

    public static int[][] rotateRight180Degree(int[][] matrix)
    {
        int n = matrix.length;
        var newMatrix = new int[n][ n];
        int maxIndex = n - 1;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                newMatrix[i][ j] = matrix[maxIndex - i][ maxIndex - j];
            }
        }

        return newMatrix;
    }
}
