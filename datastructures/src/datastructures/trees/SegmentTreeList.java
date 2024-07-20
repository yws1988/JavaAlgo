package datastructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

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
//            return searchFunction(tree[idx], min, max);
        }

        if (qe < s || qs > e) {
            return null;
        }

        int mid = mid(s, e);

        return getValueUtil(qs, qe, s, mid, idx * 2 + 1, min, max) + getValueUtil(qs, qe, mid + 1, e, idx * 2 + 2, min, max);
    }


}
