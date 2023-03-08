package hackerearth.sorting;

import java.io.File;
        import java.io.FileNotFoundException;
        import java.util.*;
        import java.util.stream.Collectors;

public class SwapGetMaxValue
{
    public static int n;
    public static int[] ns;

    public static void solve()
    {
        n=readInt();
        ns=readIntArray();

        int leftMax = 0;
        int rightMin = Integer.MAX_VALUE;

        int leftMaxMin = Integer.MAX_VALUE;
        int rightMinMax = 0;

        long result=0;

        for (int i = 0; i < n; i++) {
            result+=Math.abs(i+1-ns[i]);

            leftMax = Math.max(i+1, ns[i]);
            leftMaxMin = Math.min(leftMaxMin, leftMax);

            rightMin = Math.min(i+1, ns[i]);
            rightMinMax = Math.max(rightMinMax, rightMin);
        }

        if(rightMinMax > leftMaxMin){
            result+=2*(rightMinMax-leftMaxMin);
        }

        System.out.println(result);
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


