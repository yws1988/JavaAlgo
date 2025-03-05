package other;

public class SegmentNumbersAddition {
    public static void solve(int a, int b, int sum){
        int[][] mappings = {{0,8}, {1,7}, {5,6}, {6,8}, {8,9}, {5,9}, {3, 9}};

        int[][] graph = new int[10][10];

        for (int i = 0; i < mappings.length; i++) {
            graph[mappings[i][0]][mappings[i][1]] = 1;
            graph[mappings[i][1]][mappings[i][0]] = 1;
        }

        if(a+b==sum){
            System.out.println("Impossible");
        }else{
            for (int i = 0; i < 10; i++) {
                if(graph[a][i]==1 && i+b==sum){
                    System.out.println(i+" "+b+" "+sum);
                    return;
                }else if(graph[b][i]==1 && a+i==sum){
                    System.out.println(a+" "+i+" "+sum);
                    return;
                }else if(graph[sum][i]==1 && a+b==i){
                    System.out.println(a+" "+b+" "+i);
                    return;
                }
            }
        }

        System.out.println("Impossible");
    }
}
