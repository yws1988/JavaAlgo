package sorting.mergesort;

public class MergeSort {
    public static int[] mergeSort(int[] array, int start, int end){
        if(end>start){
            int mid = (start+end)/2;
            int len = end-start+1;
            int[] leftArray = mergeSort(array, start, mid);
            int[] rightArray = mergeSort(array, mid+1, end);

            int[] sortedArray = new int[len];
            int i=0, j=0, h=0;

            while(i<leftArray.length && j<rightArray.length){
                if(leftArray[i]<=rightArray[j]){
                    sortedArray[h++]=leftArray[i++];
                }else{
                    sortedArray[h++]=rightArray[j++];
                }
            }

            while(i<leftArray.length){
                sortedArray[h++]=leftArray[i++];
            }

            while(j<rightArray.length){
                sortedArray[h++]=rightArray[j++];
            }

            return sortedArray;
        }

        return new int[]{array[start]};
    }
}
