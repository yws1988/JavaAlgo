package branchAndBounds;
/*
 Given an array 10 integers separated by spaces called target array.
 Given N arrays with 10 integers in each array, we can mix any number (less than 50) of this N arrays
 Find the combination which of the arrays that is nearest to the target array.
 The difference of the two arrays is the sum of square of differences of each element in the array
 For the input:

  3 5 10 0 0 0 0 0 0 0
  3
  3 5 25 0 0 0 0 0 0 0
  1 4 12 0 0 0 0 0 0 0
  0 5 11 0 0 0 0 0 0 0

  A possible solution is:
  1 1 1 2
  The resulting blend will have the following characteristics:
  0.75 4.25 11.75 0 0 0 0 0 0 0

  The difference of the arrays is calculated as:
  (3 - 0.75)² + (5 - 4.25)² + (10 - 11.75)² = 8.6875
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class ArrayNearestToTargetArray {
    public static List<Integer> getCombinationOfNearestArray(double[][] arrays, double[] target){
        int n = arrays.length;
        var queue = new PriorityQueue<Node>(new NodeComparator());

        for (int i = 0; i < n; i++) {
            double res=diff(arrays[i], target);
            queue.add(new Node(res, List.of(i), arrays[i]));
        }

        List<Integer> result=new ArrayList<>();
        double min=Double.MAX_VALUE;
        while(queue.size()>0){
            var item = queue.poll();

            if(item.diff<min){
                min=item.diff;
                result=item.solutions;
            }

            if(item.solutions.size()>=50){
                break;
            }

            for (int i = 0; i < n; i++) {
                int len=item.solutions.size();
                double[] newValues = new double[10];
                for (int j = 0; j < 10; j++) {
                    newValues[j]=(item.values[j]*len+arrays[i][j])/(len+1);
                }

                var newSolution = new ArrayList<>(item.solutions);
                newSolution.add(i);
                queue.add(new Node(diff(newValues, target), newSolution, newValues));
            }
        }

        return result;
    }

    static double diff(double[] array, double[] target){
        return IntStream.range(0, target.length).mapToDouble(s-> Math.pow(array[s]-target[s], 2)).sum();
    }

    public static class Node {
        public double diff;
        public List<Integer> solutions;
        public double[] values;

        public Node(double diff, List<Integer> solutions, double[] values) {
            this.diff = diff;
            this.solutions = solutions;
            this.values=values;
        }

        public Node clone() {
            return new Node(diff, solutions, values);
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return (int) Math.signum(o1.diff-o2.diff);
        }
    }
}
