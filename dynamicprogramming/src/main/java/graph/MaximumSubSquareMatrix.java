package graph;

public class MaximumSubSquareMatrix {
    public static int getMaximumSquareMatrix(int[][] matrix)
    {
        int lengthRow = matrix.length;
        int lengthColumn = matrix[0].length;

        int[][] dp = new int[lengthRow][lengthColumn];

        int maxSize = 0;
        int maxX = 0;
        int maxY = 0;

        for (int i = 0; i < lengthRow; i++)
        {
            for (int j = 0; j < lengthColumn; j++)
            {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                }
                else
                {
                    if (matrix[i][j] == 1)
                    {
                        dp[i][j] = Math.min(dp[i - 1][j-1], Math.min(dp[i - 1][j], dp[i][j - 1]))+1;
                    }else
                    {
                        dp[i][j] = 0;
                    }
                }

                if(dp[i][j] > maxSize)
                {
                    maxSize = dp[i][j];
                    maxX = i;
                    maxY = j;
                }
            }
        }

        //Console.WriteLine($"The maximun size of the square matrix is : {maxSize}");
        //Console.WriteLine($"The the right most index is : {maxX},{maxY}");

        return maxSize;
    }
}
