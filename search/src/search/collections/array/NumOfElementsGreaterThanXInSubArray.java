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

import java.util.List;

public class NumOfElementsGreaterThanXInSubArray {
    public static int[] array;
    private static List<Integer>[] tree;


    public static int getNumOfElements(int min, int max, int left, int right){
        int n = array.length;

        SegmentTreeList segmentTreeList = new SegmentTreeList(array, CollectionHelper::mergeTwoOrderedLists);
        return segmentTreeList.value(left, right, min, max);
    }
}
