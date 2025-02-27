package graph.matching;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class MaximumGroupMatching
{
    public static int n, p, d, m;
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

        ns = readIntArray();
        n=ns[0];
        p=ns[1];
        d=ns[2];
        m=ns[3];

        int[][] graph = readIntMatrix(n);



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

