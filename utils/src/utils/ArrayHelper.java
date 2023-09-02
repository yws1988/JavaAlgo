package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ArrayHelper{

    public static int[][] clone(int[][] array)
    {
        int m = array.length;

        var cArray = new int[m][];
        for (int i = 0; i < m; i++)
        {
            cArray[i] = Arrays.copyOf(array[i], array[i].length);
        }

        return cArray;
    }

    /// <summary>
    /// Inverse the position of the first k elements of the array
    /// </summary>
    /// <typeparam name="T"></typeparam>
    /// <param name="k"></param>
    /// <param name="array"></param>
    /// <returns></returns>
    public static int[] inverse(int[] array, int k)
    {
        var newArray = Arrays.copyOf(array, array.length);
        for (int i = 0, j = k; i <= k / 2; i++, j--)
        {
            int temp = newArray[i];
            newArray[i] = newArray[j];
            newArray[j] = temp;
        }

        return newArray;
    }

    public static List<Integer>[] buildListArray(int v){
        var graph = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        return graph;
    }

}
