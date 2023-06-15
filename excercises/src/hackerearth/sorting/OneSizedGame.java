package hackerearth.sorting;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class OneSizedGame
{
    public static int n,t;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve()
    {
        t=readInt();
        for (int i = 0; i < t; i++) {
            n=readInt();
            ns=readIntArray();
            var pq = new PriorityQueue<Pair>(new PairComparator());
            for (int j = 0; j < n; j++) {
                pq.add(new Pair(ns[j], j+1));
            }

            int sum=0;
            int removedElements=0;

            while(pq.size()!=1){
                var pair = pq.poll();
                pair.y-=removedElements;
                if(pair.x>=sum){
                    sum+= pair.y;
                    pq.add(pair);
                }else{
                    removedElements++;
                }
            }

            var pair = pq.poll();
            if(sum>pair.x){
                System.out.println("Kushagra");
            }else{
                System.out.println("Ladia");
            }
        }
    }

    public static Scanner scanner;

    public static void mainF(String[] argv) throws Exception
    {
        if(true){
            File file = new File("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        solve();
        scanner.close();
    }

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp;}
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

        public Pair clone(){
            return new Pair(x, y);
        }
    }

    public static class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x-o2.x == 0 ? o1.y - o2.y : o1.x-o2.x;
        }
    }
}

