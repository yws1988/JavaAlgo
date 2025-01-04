package unionFind;
/***
 * In an undirected graph with n vertex and m edges, the edges have two types, type1, tupe2,
 * The graph is balanced when all the vertex are connected by edges type1 are also
 * connected with the edges type2.
 * You will be given q instructions asking you to build either edge type1 and type2.
 * After each instruction, you must report whether the graph is balanced or not.
 * First line, n, m, number of vertex and number of edges
 * next m lines, each line with three numbers t, u, v, type of edges, vertex numbers
 * 5 8
 * 1 1 2
 * 1 2 3
 * 2 1 3
 * 2 1 2
 * 1 3 4
 * 2 2 5
 * 1 4 5
 * 2 1 4
 * Sample output:
 * NO
 * NO
 * NO
 * YES
 * NO
 * NO
 * NO
 * YES
 */

import search.unionFind.UnionFind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static search.unionFind.UnionFind.find;
import static search.unionFind.UnionFind.union;

public class VertexConnectedInBalancedGraph
{
    public static int n,m;
    public static int[] ns;

    public static void solve() throws FileNotFoundException {
        if(true){
            File file = new File("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        ns = readIntArray();
        n=ns[0];
        m=ns[1];

        var tmp = readIntMatrix(m);
        var subsets = UnionFind.createSubsets(n+1);

        var queue = new LinkedList<List>();
        for (int i = 0; i < m; i++) {
            int t = tmp[i][0];
            int u = tmp[i][1];
            int v = tmp[i][2];

            if(t==1 && subsets[u].parent!=subsets[v].parent){
                queue.add(List.of(u, v));
            }else{
                union(subsets, u, v);
                while(queue.size()>0){
                    List<Integer> pair = queue.peek();
                    if(find(subsets, pair.get(0)) == find(subsets, pair.get(1))){
                        queue.pop();
                    }else{
                        break;
                    }
                }
            }

            System.out.println(queue.size()>0 ? "NO":"YES");
        }

        scanner.close();
    }

    public static Scanner scanner;

    public static void mainF( String[] argv ) throws Exception
    {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    solve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }
}

