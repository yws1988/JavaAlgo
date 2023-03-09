package delete;

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
        ss = readLines(n);
        gg = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gg[i][j] = ss[i].charAt(j)=='#' ? 1:0;
            }
        }
        getConnectedGraphNum(gg);

        System.out.println(count);
    }

    public static int count=0;

    public static void getConnectedGraphNum(int[][] graph)
    {
        int v = graph.length;
        boolean[][] vs = new boolean[v][v];


        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if(graph[i][j]==1 && !vs[i][j])
                {
                    count++;
                    DFSUtil(graph, i, j, vs, v);
                }
            }
        }
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static void DFSUtil(int[][] graph, int i, int j, boolean[][] vs, int v)
    {
        vs[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int tI = i + dx[k];
            int tJ = j + dy[k];
            if(isSafe(tI, tJ, v) && !vs[tI][tJ] && graph[tI][tJ]==1)
            {
                DFSUtil(graph, tI, tJ, vs, v);
            }
        }
    }

    static boolean isSafe(int i, int j, int v)
    {
        return i >= 0 && i < v && j >= 0 && j < v;
    }

    public static Scanner scanner;

    public static void mainF( String[] argv ) throws Exception
    {
        if(true){
            File file = new File("C:\\Users\\shen\\Downloads\\input1.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        n = readInt();

//        ns = readIntArray();
//        s = readString();
//        ss = readStringArray();
//        ss = readLines(n);
//        gg = readIntMatrix(n);

        solve();
        scanner.close();
    }

    public static int readInt() { int ddd =scanner.nextInt();  scanner.nextLine(); return ddd;}
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

