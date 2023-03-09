package delete;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class IsoContest1
{
    public static int n;
    public static int[] ns;
    public static String s;
    public static String[] ss;
    public static int[][] gg;

    public static void solve()
    {

    }

    public static Scanner scanner;

    public static void mainf( String[] argv ) throws Exception
    {
        if(true){
            File file = new File("C:\\Users\\shen\\Downloads\\input1.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        n = readInt();
        ns = readIntArray();
        s = readString();
        ss = readStringArray();
        ss = readLines(n);
        gg = readIntMatrix(n);

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

