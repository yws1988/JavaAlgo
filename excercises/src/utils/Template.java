package utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Template
{
    public static int n;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve()
    {
    }

    public static Scanner scanner;

    public static void mainf( String[] argv ) throws Exception
    {
        if(true){
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
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }
}

