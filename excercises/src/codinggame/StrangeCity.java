package codinggame;

import utils.CollectionHelper;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class StrangeCity {
    public static int n, q, d, dis = 0;
    public static List<Integer>[] graph;
    public static long[] costs;

    public static int solve(int point, long x, long y) {

        var visited = new boolean[n];
        var queue = new PriorityQueue<Pair>(new PairComparator());

        List<Pair> pairs = new ArrayList<>();
        int indexOfPoint = 0;
        long costOfPoint = 0;
        var startPair = new Pair(d, 0);
        queue.add(startPair);

        while (queue.size() > 0)
        {
            var nodeParent = queue.poll();
            int s = nodeParent.index;
            if (visited[s]) continue;
            visited[s] = true;

            if(nodeParent.index == point){
                indexOfPoint = pairs.size();
                costOfPoint = nodeParent.cost;
            }
            pairs.add(nodeParent);

            for (int i = 0; i < graph[s].size(); i++)
            {
                int cNode = graph[s].get(i);
                if (!visited[cNode])
                {
                    queue.add(new Pair(cNode, nodeParent.cost + costs[cNode]));
                }
            }
        }

        var costOfAllPoints = pairs.stream().map(p -> p.cost).collect(Collectors.toList());

        int indexOfX = CollectionHelper.binarySearchLeftElement(costOfAllPoints, x);

        if(indexOfX == -1){
            return 0;
        }

        if(indexOfX>=indexOfPoint){
           return indexOfX+1;
        }

        if(costOfPoint>y){
            return 0;
        }

        return indexOfPoint+1;
    }

    public static class Pair
    {
        public long cost;

        public int index;

        public Pair(int index, long cost)
        {
            this.index = index;
            this.cost = cost;
        }
    }

    public static class PairComparator implements Comparator<Pair>{
        @Override
        public int compare(Pair o1, Pair o2) {
            return (int) Math.signum(o1.cost-o2.cost);
        }
    }

    public static Scanner scanner;

    public static void main(String[] argv) throws Exception {
        scanner = new Scanner(System.in);

        if(true){
            File file = new File("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        var ns = readIntArray();
        n = ns[0];
        q = ns[1];
        d = ns[2] - 1;
        costs = readLongArray();

        graph = buildListArray(n);
        int[] tt;
        for (int i = 0; i < n - 1; i++) {
            tt = readIntArray();
            graph[tt[0] - 1].add(tt[1] - 1);
            graph[tt[1] - 1].add(tt[0] - 1);
        }

        long last=0;

        for (int i = 0; i < q; i++) {
            long[] qs = readLongArray();
            int point = (int) qs[0];
            last = solve((int)((point ^ last)-1), qs[1] ^ last, qs[2] ^ last);

            System.out.println(last);
        }
        scanner.close();
    }

    //
    public static int readInt() {
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }

    public static int[] readIntArray() {
        return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray();
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static String[] readStringArray() {
        return scanner.nextLine().split("[ \t]");
    }

    public static String[] readLines(int quantity) {
        String[] lines = new String[quantity];
        for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim();
        return lines;
    }

    public static int[][] readIntMatrix(int numberOfRows) {
        int[][] matrix = new int[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray();
        return matrix;
    }

    public static long[] readLongArray() {
        return Arrays.stream(readStringArray()).mapToLong(Long::parseLong).toArray();
    }

    public static <T> List<T>[] buildListArray(int n) {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<T>();
        }

        return graph;
    }
}
