package graph.hamiltonianPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaximumLengthSimplePath
{
    public static int n, m, t;
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
        t = readInt();

        for (int i = 0; i < t; i++) {
            ns = readIntArray();
            n=ns[0];
            m=ns[1];

            var tmp = readIntMatrix(m);
            List<Integer>[] graph = buildListArray(n, tmp, false, -1);

            int minSumVertices = Integer.MAX_VALUE;
            int maxSumVertices = 0;

            for (int k  = 0; k < n; k++)
            {
                var vs = new boolean[n];
                sumOfVertices = 0;
                maxLevel=0;
                dfsPathUntil(graph, n, vs, k, 1, 0);
                minSumVertices = Math.min(sumOfVertices, minSumVertices);
                maxSumVertices = Math.max(sumOfVertices, maxSumVertices);
            }

            int gcd = greatestCommonDivisor(minSumVertices, maxSumVertices);
            System.out.println(maxSumVertices/gcd+" "+minSumVertices/gcd);

        }

        bufferedReader.close();
    }

    public static int greatestCommonDivisor(int m, int n)
    {
        while (n > 0)
        {
            int t = n;
            n = m % n;
            m = t;
        }

        return m;
    }


    static int maxLevel=0, sumOfVertices = 0;

    static void dfsPathUntil(List<Integer>[] graph, int n, boolean[] vs, int src, int level, int sum)
    {
        vs[src] = true;
        sum+=src+1;

        if(maxLevel <=level){
            maxLevel = level;
            sumOfVertices = Math.max(sum, sumOfVertices);
        }

        for (var c : graph[src])
        {
            if (!vs[c])
            {
                dfsPathUntil(graph, n, vs, c, level + 1, sum);
            }
        }

        vs[src] = false;
    }


    public static <T> List<T>[] buildListArray(int n, int[][] arr, boolean isDirected, int adjust)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<T>();
        }

        for (var item : arr)
        {
            int src = item[0]+adjust;
            int des = item[1]+adjust;
            graph[src].add(des);

            if (!isDirected)
            {
                graph[des].add(src);
            }
        }

        return graph;
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
    public static String readString() throws IOException { return bufferedReader.readLine(); }
    public static String[] readStringArray() throws IOException { return bufferedReader.readLine().split("[ \t]"); }
    public static String[] readLines(int quantity) throws IOException { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = bufferedReader.readLine().trim(); return lines; }
    public static int[][] readIntMatrix(int numberOfRows) throws IOException { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair clone(){
            return new Pair(x, y);
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x-o2.x;
        }
    }

    public static void printEdgeList(HashSet<Integer>[] biComponentGraph){
        HashSet<List> edges = new HashSet<>();
        List<Integer> edge;
        for (int i = 0; i < biComponentGraph.length; i++) {
            for (int j : biComponentGraph[i]) {
                if(i>j){
                    edge = List.of(j, i);
                }else{
                    edge = List.of(i, j);
                }

                if(!edges.contains(edge)){
                    edges.add(edge);
                    System.out.println(edge.get(0)+"-"+edge.get(1));
                }
            }
        }
    }
}

