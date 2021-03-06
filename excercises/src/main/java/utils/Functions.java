package utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Functions
{
    public <T> T[] buildNumberArray(int size, T value) {
        ArrayList<T> arr = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            arr.set(i, value);
        }

        T[] a = (T[]) Array.newInstance(value.getClass(), size);
        return arr.toArray(a);
    }

    public static void printArrayMatrix(int[][] g, String sep)
    {
        int n = g.length;

        for (int i = 0; i < n; i++)
        {
            System.out.println(String.join(sep, Arrays.stream(g[i]).mapToObj(String::valueOf).toArray(String[]::new)));
        }
    }
}
