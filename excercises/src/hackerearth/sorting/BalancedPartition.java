package hackerearth.sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BalancedPartition
{
    public static class Point{
        public int x;
        public int y;
        public int h;
        public int value;
        public Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
            value=x-y;
        }
    }

    public static int t,n,l;
    public static int[] ns;

    public static void solve(){
        for (int i = 0; i < t; i++) {
            n = readInt();
            ArrayList<Point> points = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                ns=readIntArray();
                Point point = new Point(ns[0], ns[1], ns[2]);
                points.add(point);
            }

            points.sort((pointA, pointB)->{return (pointA.value-pointB.value);});

            int sum = points.stream().mapToInt(s -> s.h).sum();
            LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

            points.forEach(point -> {
                Integer value = Integer.valueOf(point.value);
                if(map.containsKey(value)){
                    map.put(value, map.get(value) + point.h);
                }else{
                    map.put(value, point.h);
                }
            });

            boolean possible=false;
            int leftSum = 0;
            int rightSum = sum-leftSum;

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int pointSum = entry.getValue();
                if(leftSum == rightSum || leftSum==(rightSum-pointSum)){
                    System.out.println("YES");
                    possible=true;
                    break;
                }

                leftSum+=pointSum;
                rightSum-=pointSum;
            }

            if(!possible){
                System.out.println("NO");
            }
        }
    }


    public static Scanner scanner;

    public static void mainF(String[] args) throws FileNotFoundException {
        if(false){
            File file = new File("files/input.txt");
            System.out.println(file.getAbsolutePath());
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        t = readInt();

        solve();
        scanner.close();
    }

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp; }
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }

}
