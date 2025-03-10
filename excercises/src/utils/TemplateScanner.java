package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TemplateScanner
{
    public static int n, m;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve() throws FileNotFoundException {
        if(true){
            File file = new File("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        n = readInt();
        ns = readIntArray();
        n=ns[0];
        m=ns[1];
        s = readString();
        ss = readStringArray();
        ss = readLines(n);

        scanner.close();
    }



    public static Scanner scanner;

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

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp;}
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Template.Pair other = (Template.Pair) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public Template.Pair clone() {
            return new Template.Pair(x, y);
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

