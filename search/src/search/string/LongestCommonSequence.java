package search.string;

public class LongestCommonSequence
{
    public static String getLCS(String str1, String str2)
    {
        int xLength = str1.length() + 1;
        int yLength = str2.length() + 1;

        int[][] matrix = new int[yLength][xLength];
        for (int i = 1; i < yLength; i++)
        {
            for (int j = 1; j < xLength; j++)
            {
                if(str1.charAt(j-1) == str2.charAt(i - 1))
                {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                }
                else
                {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }

        // The length of the longest sequence is matrix[yLength-1, xLength-1]
        var sb = new StringBuilder();

        int m = yLength-1;
        int n = xLength-1;

        while (m > 0 && n > 0)
        {
            if (matrix[m][n] == 0) break;

            if (matrix[m][n] == matrix[m - 1][n])
            {
                m--;
            }
            else if (matrix[m][n] == matrix[m][n - 1])
            {
                n--;
            }
            else
            {
                sb.append(str1.charAt(n-1));
                m--;
                n--;
            }
        }

        return sb.toString();
    }
}
