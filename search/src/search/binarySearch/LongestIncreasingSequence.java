package search.binarySearch;

public class LongestIncreasingSequence {
    public static int getLongestLength(int[] nums)
    {
        int length = nums.length;
        int[] tails = new int[length];
        int tailLength = 1;
        tails[0]=nums[0];

        for (int i = 1; i < length; i++) {
            if(nums[i]<tails[0]){
                tails[0]=nums[i];
            }else if(nums[i]>tails[tailLength-1]){
                tails[tailLength++]=nums[i];
            }else{
                tails[binarySearchIndex(tails, nums[i],0, tailLength-1)]=nums[i];
            }
        }

        return tailLength;
    }

    public static int binarySearchIndex(int[] tails, int value, int start, int end){
        while(end>start){
            int half = (end+start)/2;

            if(value>tails[half]){
                start=half+1;
            }else{
                end=half;
            }
        }

        return end;
    }
}
