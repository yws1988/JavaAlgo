package search.collections.array;
/*
Given an array with n elements, returns the number of elements which is greater than or equal to min and
lower than or equal to max in the given sub array from index left to right.

For example for array
12 7 5 20 4 6 2 3 78

x=4 y=10 index left=1 right=3

Returns 2
 */

import datastructures.trees.SegmentTreeList;
import utils.CollectionHelper;
import utils.graph.GraphBuilder;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumOfElementsGreaterThanXInSubArray {
    public static int[] array;
    private static List<Integer>[] tree;


    public static int getNumOfElements(int min, int max, int left, int right){
        int n = array.length;

        SegmentTreeList segmentTreeList = new SegmentTreeList(array, CollectionHelper::mergeTwoOrderedLists);
        segmentTreeList.value(left, right, )
    }

    static int searchFunction(List<Integer> list, long min, long max)
    {
        int tdy = tree[idx].BinarySearch(max);
        if (tdy < 0)
        {
            tdy = ~tdy - 1;
        }
        else
        {
            tdy--;
        }

        if (tdy < 0) return 0;

        int tdx = tree[idx].BinarySearch(min);
        if (tdx < 0)
        {
            tdx = ~tdx;
        }

        return tdy - tdx + 1;
    }
}
