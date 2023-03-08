package hackerearth.sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class NumberOfOvertakes
{
    public static int n, t;
    public static int[] ns, pos;
    public static long result=0;

    public static void solve()
    {
        int[] velocities = new int[n];

        for (int i = 0; i < n; i++) {
            velocities[pos[i]-1]=ns[i];
        }

        mergeSortWithNumOfOvertakes(velocities, 0, n-1);

        System.out.println(result);
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

    public static Scanner scanner;

    public static void mainF(String[] args) throws FileNotFoundException {

        if(false){
            File file = new File("files/input.txt");
            System.out.println(file.getAbsolutePath());
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }
        n=readInt();
        ns = readIntArray();
        pos = readIntArray();
        solve();
        scanner.close();
    }

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp; }
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }

    public static int max(int[] array){
        return Collections.max(Arrays.stream(array).boxed().collect(Collectors.toList()));
    }
}