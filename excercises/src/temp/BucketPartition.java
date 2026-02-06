package temp;

/*
Given an array of latencies, numBuckets, buketWidth
We want to know the number of latencies belongs to each bucket. Create numBuckets number of bucket of length bucketWidth starting 0.
Any latencies higher than the last bucket goes into the last bucket.
Return an array ans of size numBuckets where ans[i] is the number of latencies in ith bucket.

Example:
Input:
latencies: [6, 7, 50, 100, 110]
numBuckets: 8
bucketWidth: 10
OUtput:
[2, 0, 0, 0, 0, 1, 0, 2]
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BucketPartition
{
    public static void solve(int[] latencies) throws IOException {
        var ans=new int[11];
        for(int latency : latencies){
            if(latency>=100){
                ans[10]++;
            }else{
                ans[latency/10]++;
            }
        }

        System.out.println(Arrays.toString(ans));
    }

    public static class FileSystemManagement {

        public static int preCal(Node root) {
            if (root.children == null && root.children.isEmpty()) {
                return root.total = root.size;
            }

            int total = 0;
            for (Node cnode : root.children) {
                total += preCal(cnode);
            }

            return root.total = total;
        }

        public static int getTotalFromPath(Node root, String[] paths, int n) {
            if(n==paths.length){
                return root.total;
            }

            if(root.children == null && root.children.isEmpty()){
                return 0;
            }

            for (Node child : root.children){
                if(child.name.equals(paths[n])){
                    return getTotalFromPath(child, paths, n+1);
                }
            }

            return 0;
        }

        public static void solve(Node root, String path) {
            preCal(root);
            path = "/hello/word";
            String[] paths = path.split("/");
            int total = getTotalFromPath(root, paths, 1);
            System.out.println(total);
        }

        public static void mainF(String[] argv){
            String[] paths = "/hello/word/".substring(1).split("/");
        }

        public class Node {
            public String name;
            public int size;
            public int total;
            public Node parent;
            public List<Node> children;

            public Node(String name, int size, int total, Node parent, List<Node> children) {
                this.name = name;
                this.size = size;
                this.total = total;
                this.parent = parent;
                this.children = children;
            }
        }
    }
}

