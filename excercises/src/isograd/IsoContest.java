package isograd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class IsoContest
{
    public static int n, m;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve() throws IOException {
        if(true){
            FileReader file = new FileReader("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            bufferedReader = new BufferedReader(file);
        }else{
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }

        n = readInt();
        ns = readIntArray();
        n=ns[0];
        m=ns[1];
        s = readString();
        ss = readStringArray();
        ss = readLines(n);

        bufferedReader.close();
    }

    public static BufferedReader bufferedReader;

    public static void main( String[] argv ) throws Exception
    {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    solve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public static int readInt() throws IOException { int tmp = Integer.parseInt(bufferedReader.readLine()); return tmp;}
    public static int[] readIntArray() throws IOException { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static int[][] readIntMatrix(int numberOfRows) throws IOException { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }
    public static String readString() throws IOException { return bufferedReader.readLine(); }
    public static String[] readStringArray() throws IOException { return bufferedReader.readLine().split("[ \t]"); }
    public static String[] readLines(int quantity) throws IOException { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = bufferedReader.readLine().trim(); return lines; }
    public static String[][] readStringMatrix(int numberOfRows) throws IOException { String[][] matrix = new String[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readStringArray(); return matrix; }
    public static char[] readChars() throws IOException { return bufferedReader.readLine().toCharArray(); }
    public static char[][] readCharsMatrix(int numberOfRows) throws IOException { char[][] matrix = new char[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readChars(); return matrix; }

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Pair other = (Pair) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override

        public Pair clone() {
            return new Pair(x, y);
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x-o2.x;
        }
    }
}

