package isograd;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class IsoContest
{
    public static int m,n;
    public static int[] ns;
    public static String s;
    public static String[] ss;
    public static int[][] gg;

    public static void solve()
    {
        for (int i = 0; i < n; i++) {
            ss = readStringArray();
            // gg = new int[n][n];
        }


        System.out.println();
    }

    public static Scanner scanner;

    public static void main( String[] argv ) throws Exception
    {
        if(true){
            File file = new File("C:\\Users\\shen\\Downloads\\input1.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        n = readInt();
        ns = readIntArray();
        n=ns[0];
        m=ns[1];

//        ns = readIntArray();
//        s = readString();
//        ss = readStringArray();
//        ss = readLines(n);
//        gg = readIntMatrix(n);

        solve();
        scanner.close();
    }

//
    public static int readInt() { int ddd =scanner.nextInt(); scanner.nextLine();  return ddd;}
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

