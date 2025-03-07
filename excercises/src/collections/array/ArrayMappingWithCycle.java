package collections.array;
/*
 Given an integer array with n elements, the values for each element is between 0 to n-1,
 for element array[i]=j, means the value i is mapped to element j.
 The mapping will start from element 0, after m step of mappings, output the value.

 3 4
 2 3 1

 There are 3 elements, after 4 mappings, the value should be 2
 */

public class ArrayMappingWithCycle {
    public static int getValue(int step, int[] mappings){
        int n= mappings.length;

        int[] values = new int[n];
        boolean[] vs = new boolean[n];

        int i = 0;
        int counter=1;
        int[] valueStep=new int[n+1];
        int[] stepValue=new int[n+1];

        while(!vs[i]){
            vs[i]=true;
            valueStep[i]=counter;
            i=values[i];
            stepValue[counter]=i;
            counter++;
        }

        int cycle = counter-valueStep[i];

        if(step>valueStep[i]){
            step=valueStep[i]+(step-valueStep[i])%cycle;
        }

        return stepValue[step];
    }
}
