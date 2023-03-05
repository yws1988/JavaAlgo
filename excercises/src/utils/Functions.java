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

    static int getMinValue(int n1, int n2, int n3)
    {
        return n1 > n2 ? (n2 > n3 ? n3 : n2) : (n1 > n3 ? n3 : n1);
    }
}
