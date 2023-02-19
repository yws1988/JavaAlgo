/*
Given an array of integers nums and an integer k,
return the total number of continuous subarrays whose sum equals to k.
For example array: 2 1 1 1 -2 1
if k = 3
The number of subarray is 3
*/

package collections.array;

import java.util.HashMap;

public class NumOfSubArraysEqualsToK {
    public static int getNumOfSubArrays(int[] array, int k)
    {
        int sum = 0;
        var dic = new HashMap<Integer, Integer>();
        dic.put(0, 1);
        int num = 0;

        for (int i = 0; i < array.length; i++)
        {
            sum += array[i];
            num += dic.getOrDefault(sum - k, 0);
            dic.put(sum, dic.getOrDefault(sum, 0)+1);
        }

        return num;
    }
}
