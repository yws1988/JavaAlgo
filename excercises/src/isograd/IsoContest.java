package isograd;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class IsoContest
{
    public static int m,n,t;
    public static int[] ns;
    public static String s;
    public static String[] ss;
    public static int[][] gg;

    public static void solve()
    {
        gg=new int[n][n];
        var tric = readIntArray();
        var listt=new ArrayList<Integer>();
        for (int i = 0; i < tric.length; i++) {
            listt.add(tric[i]);
        }
//        for (int j = 0; j < t; j++) {
//            var tmp = readIntArray();
//            for
//            tric.add(new Pair(tmp[0]-1, tmp[1]-1));
//        }

        for (int j = 0; j < t; j++) {
            var tmp = readIntArray();
            gg[tmp[0]-1][tmp[1]-1]=1;
            gg[tmp[1]-1][tmp[0]-1]=1;
        }
        
        
        var queue= new LinkedList<Integer>();
    
        queue.addAll(listt);
        queue.add(0);
        var vs = new boolean[n];

        var res = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            while(queue.size()>0){
                var ff = queue.getFirst();
                if(ff==0){
//                    res.add(DFSUtil(graph));
                }else{

                }
            }
        }

        var list = new ArrayList<Integer>();
        System.out.println("");
    }

    static void DFSUtil(int[][] graph, int i, boolean[][] vs, int v)
    {

        for (int k = 0; k < n; k++) {
            if(!vs[i][k] && graph[i][k]==1)
            {
                vs[i][k] = true;
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

//        n = readInt();
        ns = readIntArray();
        n=ns[0];
        m=ns[1];
        t=ns[2];

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

