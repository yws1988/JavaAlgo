package isograd;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static int computeLongestIncreasingSubsequenceLength(int n, List<Integer> quality) {
        // Write your code here
        int length = quality.size();
        if(length<=1) return length;

        int[] tails = new int[length];
        for (int i = 0; i < tails.length; i++) {
            tails[i]=Integer.MIN_VALUE;
        }
        int result = 0;
        for (int value : quality) {
            int left = 0, right=length-1;
            while(left<=right){
                int mid=(left+right)>>1;
                if(tails[mid]<value){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }

            if(left==length) return length;
            tails[left-1<0 ? 0 : left-1]=value;

            result=Math.max(result, left);
        }

        return result;
    }

    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
        Collections.sort(intervals, (a, b)->{
            int c = a.get(0).compareTo(b.get(0));
            return c!=0 ? c:a.get(1).compareTo(b.get(1));
        });
        return null;
    }

    record Point(Integer a, Integer b){}

    public static void main(String[] args){
        List<Integer> list = List.of(
                5,
                5,
                1,
                2,
                3,
                4,
                5
        );
        int result = computeLongestIncreasingSubsequenceLength(7, list);
        System.out.println(result);
    }
}
