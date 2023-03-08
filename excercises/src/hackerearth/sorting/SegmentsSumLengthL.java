package hackerearth.sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SegmentsSumLengthL
{
    public static class Segment{
        public int x;
        public int y;
        public Segment(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int t,n,l;
    public static int[] ns;

    public static void solve(){
        for (int i = 0; i < t; i++) {
            ns = readIntArray();
            n=ns[0];
            l=ns[1];

            ArrayList<Segment> segments = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                ns=readIntArray();
                Segment seg = new Segment(ns[0], ns[1]);
                segments.add(seg);
            }

            segments.sort((segA, segB)->{return (segA.x-segB.x)==0 ? segA.y-segB.y : (segA.x-segB.x);});

            int len=segments.size();

            boolean find = false;
            for (int a = 0; a < len; a++) {
                int endmax = segments.get(a).x+l;
                int currentend=segments.get(a).y;

                if(currentend>endmax){
                    continue;
                }else if(currentend==endmax){
                    System.out.println("Yes");
                    find=true;
                    break;
                }

                for (int b = a+1; b < len; b++) {
                    Segment seg = segments.get(b);
                    if(seg.y<=endmax && currentend>=seg.x  && currentend<=seg.y){
                        currentend = Math.max(currentend, seg.y);
                    }
                }

                if(currentend==endmax){
                    System.out.println("Yes");
                    find=true;
                    break;
                }
            }

            if(!find){
                System.out.println("No");
            }
        }
    }


    public static Scanner scanner;

    public static void mainF(String[] args) throws FileNotFoundException {
        if(true){
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