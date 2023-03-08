package hackerearth.sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class SelectSubset
{
    public static int n, t;
    public static int[] as, bs;
    public static String s;
    public static String[] ss;

    public static void solve()
    {
        for (int i = 0; i < t; i++) {
            n=readInt();
            as=readIntArray();
            bs=readIntArray();
            List<Node> nodes = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                nodes.add(new Node(as[j], bs[j]));
            }

            Collections.sort(nodes, (nodeA, nodeB)->{
                return nodeA.x- nodeB.x == 0 ? nodeA.y-nodeB.y : nodeA.x- nodeB.x;
            });

            int[] nums = nodes.stream().mapToInt(s -> s.y).toArray();

            System.out.println(getLongestLength(nums));
        }
    }

    public static class Node{
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
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
        t=readInt();

        solve();
        scanner.close();
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

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp; }
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }

    public static int max(int[] array){
        return Collections.max(Arrays.stream(array).boxed().collect(Collectors.toList()));
    }
}


