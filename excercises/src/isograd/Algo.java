package isograd;

import java.util.*;

import static java.lang.Math.round;

public class Algo {
    Deque<Integer> queue=new ArrayDeque<Integer>();
    void hit(int time){
        queue.add(time);
    }
    int getHits(int time){
        while((time-queue.getFirst())>100){
            queue.pollFirst();
        }

        return queue.size();
    }
    public static void solve(){

    }

    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
        Collections.sort(intervals, (a, b)->{
            int c = a.get(0).compareTo(b.get(0));
            return c!=0 ? c:a.get(1).compareTo(b.get(1));
        });
        return null;
    }

    public static String maxWindowAvg(List<Point> points, int k){
        int max=Integer.MIN_VALUE;
        int sum=0;
        List<Integer> sums=new ArrayList<>();
        for (int i = 0; i < k; i++) {
            sum+=points.get(i).b;
        }
        sums.add(sum);
        max=Math.max(sum, max);
        for (int i = k; i < points.size(); i++) {
            sum-=points.get(i-k).b;
            sum+=points.get(i).b;
            sums.add(sum);
            max=Math.max(sum, max);
        }
        int[] too={5, 6};
        return Arrays.toString(too);
//        return Double.toString(Math.round((double)max/k*100)/100.0);
    }

    record Point(Integer a, Integer b){}

    public static void main(String[] args){
        List<Point> list = Arrays.asList(
                new Point(10, 20),
                new Point(5, 30),
                new Point(5, 10),
                new Point(1, 100),
                new Point(50, 2000)
        );
        list.stream().sorted((p1, p2) ->
                p1.a.compareTo(p2.a)!=0 ? p1.a.compareTo(p2.a) : p1.b.compareTo(p2.b))
            .forEach(System.out::println);
    }
}
