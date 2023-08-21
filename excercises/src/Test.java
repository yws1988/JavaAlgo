import graph.connectivity.MaxCliqueOfRanges;

public class Test {
    /*
     1 1 0 1
    1 1 1 1
    0 1 1 0
    1 1 0 1
     */
    public static void mainF(String[] args){
        var graph = new int[][]{{1,1,0,1},{1,1,1,1},{0,1,1,0},{1,1,0,1}};

        System.out.println(MaxCliqueOfRanges.getMaxClique(graph));
    }
}
