package datastructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class SegmentTreeList {
    public int[] array;
    public int sizeOfArray;
    public List<Integer>[] tree;
    public int sizeOfTree;
    public BiFunction<List<Integer>, List<Integer>, List<Integer>> func;

    public SegmentTreeList(int[] array, BiFunction<List<Integer>, List<Integer>, List<Integer>> func) {
        this.array = array;
        sizeOfArray = array.length;
        this.func = func;
        this.build();
    }

    void build() {
        sizeOfTree = (int) Math.pow(2, ((int) Math.ceil(Math.log(sizeOfArray)/Math.log(2)))) * 2 - 1;
        tree = new ArrayList[sizeOfTree];

        buildUntil(0, 0, sizeOfArray - 1);
    }

    List<Integer> buildUntil(int index, int s, int e) {
        if (s == e) return tree[index] = Arrays.asList(array[s]);

        int mid = mid(s, e);
        return tree[index] = this.func.apply(buildUntil(index * 2 + 1, s, mid), buildUntil(index * 2 + 2, mid + 1, e));
    }

    int mid(int s, int e) {
        return (s + e) / 2;
    }

    // qt:query start. qe:query end
    public Integer value(int qs, int qe, int min, int max) {
        return getValueUtil(qs, qe, 0, sizeOfArray - 1, 0, min, max);
    }

    Integer getValueUtil(int qs, int qe, int s, int e, int idx, int min, int max) {
        if (qs <= s && qe >= e) {
            return binarySearchNumElementsBetweenMinAndMax(tree[idx], min, max);
        }

        if (qe < s || qs > e) {
            return null;
        }

        int mid = mid(s, e);

        return getValueUtil(qs, qe, s, mid, idx * 2 + 1, min, max) + getValueUtil(qs, qe, mid + 1, e, idx * 2 + 2, min, max);
    }


    /***
     * Returns the num of elements whose value>=min and value<=max
     * @param sortedList
     * @param min
     * @param max
     * @return
     */

    public static int binarySearchNumElementsBetweenMinAndMax(List<Integer> sortedList, int min, int max)
    {
        int tdy = binarySearchLeftElement(sortedList, max+1);
        int tdx = binarySearchLeftElement(sortedList, min);

        return tdy - tdx;
    }

    /***
     * return the index of the first value which is greater or equal than specified key
     * @param list sorted array
     * @param greaterOrEqualValue the key
     * @return
     */
    public static int binarySearchLeftElement(List<Integer> list, Integer greaterOrEqualValue){
        int index = binarySearchLeftElement(list, greaterOrEqualValue, 0, list.size()-1);

        if(index<0){
            return -index-1;
        }

        while(index>=0){
            index = binarySearchLeftElement(list, greaterOrEqualValue, 0, index-1);
        }

        return -index-1;
    }

    public static <T> int binarySearchLeftElement(List<? extends Comparable<? super T>> list, T key, int low, int high) {

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = list.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }

        return -(low + 1);  // key not found
    }
}
