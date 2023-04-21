package sorting.mergesort;

/*
 Given a array arr[] size is n, value of the array is the random of permutation from
 0 to n-1, calculate the overtakes if we sort the array with merge sort.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class NumberOfOvertakesToMergeSortArray
{
    public static int n, t;
    public static int[] ns, pos;
    public static long result=0;

    public static long numberOfOvertakes(int[] arr)
    {
        mergeSortWithNumOfOvertakes(arr, 0, n-1);
        return result;
    }

    public static int[] mergeSortWithNumOfOvertakes(int[] array, int start, int end){
        if(end>start){
            int mid = (start+end)/2;
            int len = end-start+1;
            int[] leftArray = mergeSortWithNumOfOvertakes(array, start, mid);
            int[] rightArray = mergeSortWithNumOfOvertakes(array, mid+1, end);

            int[] sortedArray = new int[len];
            int i=0, j=0, h=0, overtaken=0;

            while(i<leftArray.length && j<rightArray.length){
                if(leftArray[i]<=rightArray[j]){
                    sortedArray[h++]=leftArray[i++];
                    result+=j;
                }else{
                    sortedArray[h++]=rightArray[j++];
                }
            }

            while(i<leftArray.length){
                sortedArray[h++]=leftArray[i++];
                result+= j;
            }

            while(j<rightArray.length){
                sortedArray[h++]=rightArray[j++];
            }

            return sortedArray;
        }

        return new int[]{array[start]};
    }
}