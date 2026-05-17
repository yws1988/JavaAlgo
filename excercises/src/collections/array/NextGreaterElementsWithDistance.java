package collections.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/***
 * Given an integer array readings, return an array result where result[i] = [value, distance],
 * with value being the next greater element to the right of readings[i] and distance being the index difference.
 * If no greater element exists, return [-1, -1].
 * Input
 * readings = [2, 1, 2, 4, 3]
 * Output
 * [[4, 3], [2, 1], [4, 1], [-1, -1], [-1, -1]]
 */
public class NextGreaterElementsWithDistance {
    public static List<List<Integer>> findNextGreaterElementsWithDistance(List<Integer> readings) {
        // Write your code here
        var result = new ArrayList<List<Integer>>();
        for (int i = 0; i < readings.size(); i++) {
            result.add(List.of(-1, -1));
        }

        Deque<Integer> stack=new ArrayDeque<Integer>();
        stack.push(0);

        for (int i = 1; i < readings.size(); i++) {
            while(stack.size()>0){
                int p = stack.peek();
                if(readings.get(p)<readings.get(i)){
                    result.set(p, List.of(readings.get(i), i-p));
                    stack.pop();
                }else{
                    stack.push(i);
                    break;
                }
            }

            if(stack.size()==0){
                stack.push(i);
            }
        }

        return result;
    }
}
