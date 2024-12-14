package graph.brige;
/*
Given a directed connected graph with n vertices and m edges, each edge has a weight of cost w.
If the path go through u to v the cost is 0, but v to u the cost is w.
Before querying the cost of path from vertex u to v, we can modify the direction of any edges.
Calculate the minimum total of the costs for all the queries.
The first line contains two integers n, m — the number of vertices and the number of edges, respectively.
Next m lines contain three integers u, v, w
7 7
4 6 7
4 5 10
5 7 3
3 5 3
1 2 5
7 3 6
2 6 1
2
6 5
7 4

Next line contains a single integer q — the number of queries.
Next q lines contain two integers each, start and end vertice
 */

import java.io.*;
import java.util.*;

public class BridgeTreeWithMinimumCostsModifyingDirection {

    static int N, M;
    static ArrayList<Integer> adj[];
    static int u[], v[], w[];
    static boolean vis[];
    static int disc[], low[], componentId[], parent[];
    static int time, total;
    static int parentVertex[], parentFine[], depth[];
    static int P[][];
    static ArrayList<Integer> bridgeTree[];
    static int pos[], neg[];
    static long ans;

    public static BufferedReader reader;

    public static void mainF(String args[]) throws IOException {
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

    @SuppressWarnings("unchecked")
    public static void solve() throws IOException {
        if(true){
            FileReader file = new FileReader("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            reader = new BufferedReader(file);
        }else{
            InputStreamReader isr = new InputStreamReader(System.in);
            reader = new BufferedReader(isr);
        }

        var tmp = readIntArray();
        N = tmp[0];
        M = tmp[1];

        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<Integer>();

        u = new int[M + 1];
        v = new int[M + 1];
        w = new int[M + 1];

        for (int i = 1; i <= M; i++) {
            tmp=readIntArray();
            u[i] = tmp[0];
            v[i] = tmp[1];
            w[i] = tmp[2];
            adj[u[i]].add(i);
            adj[v[i]].add(i);
        }

        vis = new boolean[N + 1];
        disc = new int[N + 1];
        low = new int[N + 1];
        parent = new int[N + 1];
        time = 0;
        dfs(1);

        Arrays.fill(vis, false);
        total = 1;
        componentId = new int[N + 1];
        parentVertex = new int[N + 1];
        parentFine = new int[N + 1];
        depth = new int[N + 1];
        bridgeTree = new ArrayList[N + 1];
        bridgeTree[1] = new ArrayList<Integer>();
        buildBridgeTreeWithDfs(1, total);

        preprocessLcaDpLevel();

        int Q = readInt();

        pos = new int[total + 1];
        neg = new int[total + 1];
        ans = 0;

        while (Q-- > 0) {
            tmp = readIntArray();
            int a = tmp[0];
            int b = tmp[1];
            a = componentId[a];
            b = componentId[b];
            int lca = Lca(a, b);
            pos[a]++;
            pos[lca]--;
            neg[b]++;
            neg[lca]--;
        }

        dfsCalculateCosts(1);

        System.out.println(ans);

        reader.close();
    }

    static void dfsCalculateCosts(int curr) {
        for (int child : bridgeTree[curr]) {
            dfsCalculateCosts(child);
            pos[curr] += pos[child];
            neg[curr] += neg[child];
        }
        ans += Math.min(pos[curr], neg[curr]) * 1L * parentFine[curr];
    }

    static void dfs(int curr) {
        disc[curr] = low[curr] = time++;
        vis[curr] = true;
        for (int edge : adj[curr]) {
            int next = u[edge] + v[edge] - curr;
            if (vis[next]) {
                if (edge != parent[curr])
                    low[curr] = Math.min(low[curr], disc[next]);
            } else {
                parent[next] = edge;
                dfs(next);
                low[curr] = Math.min(low[next], low[curr]);
            }
        }
    }

    static void buildBridgeTreeWithDfs(int curr, int type) {
        componentId[curr] = type;
        vis[curr] = true;
        for (int edge : adj[curr]) {
            int next = u[edge] + v[edge] - curr;
            if (!vis[next]) {
                if (low[next] > disc[curr]) {
                    total++;
                    parentVertex[total] = type;
                    parentFine[total] = w[edge];
                    depth[total] = depth[type] + 1;
                    bridgeTree[type].add(total);
                    bridgeTree[total] = new ArrayList<Integer>();
                    buildBridgeTreeWithDfs(next, total);
                } else
                    buildBridgeTreeWithDfs(next, type);
            }
        }
    }


    static Collection<Integer>[] tree;
    static int level;
    static int[][] dp;

    public static void preprocessLcaDpLevel()
    {
        level = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
        dp = new int[N+1][level];

        for (int i = 1; i <= N; i++)
        {
            for (int j = 0; j < level; j++)
            {
                if(j==0){
                    dp[i][0] = parentVertex[i];
                }else{
                    dp[i][j] = -1;
                }
            }
        }

        for (int j = 1; j < level; j++)
        {
            for (int i = 2; i <= N; i++)
            {
                if (dp[i][j - 1] != -1)
                {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
        }
    }

    public static int Lca(int u, int v)
    {
        if (depth[u] > depth[v])
        {
            int temp = u;
            u = v;
            v = temp;
        }

        int h = depth[v] - depth[u];

        for (int i = 0; i < level; i++)
        {
            if(((h>>i) & 1) == 1)
            {
                v = dp[v][i];
            }
        }

        if (u == v) return u;

        for (int i = level-1; i >= 0; i--)
        {
            if(dp[u][i]!=dp[v][i])
            {
                u = dp[u][i];
                v = dp[v][i];
            }
        }

        return dp[u][0];
    }


    public static int readInt() throws IOException { return Integer.parseInt(reader.readLine()); }
    public static int[] readIntArray() throws IOException { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String[] readStringArray() throws IOException { return reader.readLine().split("[ \t]"); }
    public static int[][] readIntMatrix(int numberOfRows) throws IOException { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++){matrix[i] = readIntArray(); matrix[i][0]--;matrix[i][1]--;} return matrix; }
}