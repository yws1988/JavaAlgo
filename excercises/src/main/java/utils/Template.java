package utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Template
{
    public static final boolean DEBUG = true;

    public static int n;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve()
    {
    }

    public static Scanner scanner;

    static void MainF(String[] args)
    {
        if(DEBUG){
            scanner = new Scanner("test\test.txt");
        }else{
            scanner = new Scanner(System.in);
        }

        n = readInt();
        ns = readIntArray();
        s = readString();
        ss = readStringArray();
        ss = readLines(n);

        solve();
        scanner.close();
    }

    public static int readInt() { return scanner.nextInt(); }
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }

}


/*
public class Function
{
    public <T> T[] buildNumberArray(int size, T value) {
        ArrayList<T> arr = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            arr.set(i, value);
        }

        T[] a = (T[])Array.newInstance(value.getClass(), size);
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
}*/

