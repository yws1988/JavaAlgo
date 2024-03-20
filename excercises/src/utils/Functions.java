package utils;

import java.util.Arrays;

public class Functions
{
    public static void printArrayMatrix(int[][] g, String sep)
    {
        int n = g.length;

        for (int i = 0; i < n; i++)
        {
            System.out.println(String.join(sep, Arrays.stream(g[i]).mapToObj(String::valueOf).toArray(String[]::new)));
        }
    }
}
