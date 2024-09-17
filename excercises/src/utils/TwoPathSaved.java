package utils;

import datastructures.graph.EdgeWithWeight;
import utils.graph.GraphBuilder;

import java.io.File;
import java.util.*;

import static graph.connectivity.ConnectedGraphBuilder.transformConnectedGraph;

public class TwoPathSaved
{
    public static int n,m,q;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve()
    {
        int[][] tmp = readIntMatrix(m);
        List<EdgeWithWeight>[] graph = GraphBuilder.buildListArrayWithWeight(n, tmp, false);

        int[] components = new int[n];
        HashSet<Integer>[] componentGraph = transformConnectedGraph(graph, components);

        for (int i = 0; i < q; i++) {
            var points = readIntArray();
            int a = components[points[0]-1];
            int b = components[points[1]-1];
            int c = components[points[2]-1];
            int d = components[points[3]-1];

            if(c==d){
                System.out.println("No");
                continue;
            }

            if(a==b){
                System.out.println("Yes");
            }

            var queue = new LinkedList<List>();
            queue.add(List.of(a, 0));
            HashSet<List> set = new HashSet<>();
            boolean isFound = false;

            while (queue.size() > 0)
            {
                List<Integer> next = queue.poll();
                set.add(next);

                int node = next.get(0);
                if(node==b){
                    isFound = true;
                    break;
                }

                int doesPassCAndD = 0;

                if(node==c || node==d){
                    if(next.get(1)==1){
                        continue;
                    }else{
                        doesPassCAndD = 1;
                    }
                }

                for (int child : componentGraph[node])
                {
                    List<Integer> nextPair = List.of(child, doesPassCAndD);

                    if (!set.contains(nextPair))
                    {
                        queue.add(nextPair);
                    }
                }
            }

            System.out.println(isFound ? "Yes" : "No");
        }

    }

    public static Scanner scanner;

    public static void mainF( String[] argv ) throws Exception
    {
        if(true){
            File file = new File("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        ns = readIntArray();
        n=ns[0];
        m=ns[1];
        q=ns[2];

        solve();
        scanner.close();
    }

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp;}
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(s->Integer.parseInt(s)).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++){matrix[i] = readIntArray(); matrix[i][0]--;matrix[i][1]--;} return matrix; }

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
}

