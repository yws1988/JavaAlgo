package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BridgeTreeWithMinimumCostsSolution
{
    public static int nt,m,q;
    public static int[] ns;
    public static String s;
    public static int[] upVertex, downVertex;

    public static void solve() throws IOException {
        int[][] tmp = readIntMatrix(m);
        List<EdgeWithWeight>[] graph = buildListArrayWithWeight(nt, tmp, false);
        HashSet<Integer>[] bridgeTree = buildBridgeTree(graph);

        buildLcaDpLevel(bridgeTree, root);
        upVertex = new int[componentId];
        downVertex = new int[componentId];

        q = readInt();

        for (int i = 0; i < q; i++) {
            var points = readIntArray();
            int a = points[0]-1;
            int b = points[1]-1;
            int bridgeComponentA = bridgeComponents[a];
            int bridgeComponentB = bridgeComponents[b];

            if(bridgeComponentA==bridgeComponentB){
                continue;
            }

            int ancestorAB = Lca(bridgeComponentA, bridgeComponentB);

            upVertex[bridgeComponentA]++;
            downVertex[bridgeComponentB]++;
            upVertex[ancestorAB]--;
            downVertex[ancestorAB]--;
        }

        boolean[] vs = new boolean[componentId];
        bridgeCosts = new Integer[componentId];

        ArrayList<Integer>[] listBridgeTree = new ArrayList[componentId];

        for (int i = 0; i < componentId; i++) {
            listBridgeTree[i] = new ArrayList(bridgeTree[i]);
        }

        dfsBridgeTreeWithStack(listBridgeTree, vs);
        System.out.println(costs);
    }

    public static Integer[] bridgeCosts;
    static Long costs=0L;

    static void dfs(HashSet<Integer>[] bridgeTree, int curr, boolean[] vs){
        vs[curr]=true;
        for (var child : bridgeTree[curr]) {
            if(!vs[child]){
                bridgeCosts[child] = componentTreeBridges.get(List.of(curr, child));
                dfs(bridgeTree, child, vs);
                upVertex[curr]+=upVertex[child];
                downVertex[curr]+=downVertex[child];
            }
        }

        if(curr!=root){
            costs+= Math.min(upVertex[curr], downVertex[curr])*bridgeCosts[curr];
        }
    }

    static void dfsBridgeTreeWithStack(ArrayList<Integer>[] bridgeTree, boolean[] vs){
        Stack<NodeInfo> nodes = new Stack<>();
        nodes.add(new NodeInfo(root, -1, 0));

        while(nodes.size()>0){
            NodeInfo nodeInfo = nodes.peek();
            int curr = nodeInfo.node;
            vs[curr]=true;

            boolean isPop = true;

            for (int i = 0; i < bridgeTree[curr].size(); i++) {
                int child = bridgeTree[curr].get(i);
                if(!vs[child]){
                    isPop=false;
                    nodes.add(new NodeInfo(child, curr, 0));
                }
            }

            if(isPop){
                nodeInfo = nodes.pop();
                curr = nodeInfo.node;
                int parent = nodeInfo.parent;

                if(curr!=root){
                    upVertex[parent]+=upVertex[curr];
                    downVertex[parent]+=downVertex[curr];

                    if(upVertex[curr]<0){
                        System.out.println(upVertex[curr]);
                    }

                    costs+= Math.min(upVertex[curr], downVertex[curr]) *  1L * componentTreeBridges.get(List.of(parent, curr));
                }
            }
        }
    }

    public static int step;
    public static int root=0;
    public static Stack<EdgeWithWeight> stackEdges = new Stack<>();
    public static Stack<Node> stackVertex = new Stack<>();
    public static HashSet<List<Integer>> bridges = new HashSet<>();
    public static HashMap<List<Integer>, Integer> componentTreeBridges = new HashMap<>();
    public static int[] bridgeComponents;

    public static HashSet<Integer>[] buildBridgeTree(List<EdgeWithWeight>[] graph)
    {
        step = 0;
        int v = graph.length;
        bridgeComponents = new int[v];
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];

        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
        }

        dfsWithStack(root, graph, visited, dist, low, parents);

        while (stackEdges.size()>0){
            var edge = stackEdges.pop();
            bridgeComponents[edge.des] = componentId;
            bridgeComponents[edge.src==-1 ? 0 : edge.src] = componentId;
        }
        componentId++;

        HashSet<Integer>[] brideTree=new HashSet[componentId];

        for (int i = 0; i < componentId; i++) {
            brideTree[i]=new HashSet<>();
        }

        bridgeCosts = new Integer[componentId];

        for (var edge : bridges) {
            Integer srcComponentId = bridgeComponents[edge.get(0)];
            Integer desComponentId = bridgeComponents[edge.get(1)];
            brideTree[srcComponentId].add(desComponentId);
            brideTree[desComponentId].add(srcComponentId);

            componentTreeBridges.put(List.of(srcComponentId, desComponentId), edge.get(2));
            componentTreeBridges.put(List.of(desComponentId, srcComponentId), edge.get(2));
        }

        return brideTree;
    }

    static void dfsWithStack(int root, List<EdgeWithWeight>[] graph, boolean[] visited, int[] dist, int[] low, int[] parents)
    {
        stackEdges.push(new EdgeWithWeight(-1, root, 0));
        stackVertex.push(new Node(root, 0));
        visited[root] = true;
        dist[root] = ++step;
        low[root] = step;

        while (stackVertex.size() > 0)
        {
            var currentNode = stackVertex.peek();
            var node = currentNode.node;
            var childIndex = currentNode.childIndex;

            boolean isPop = true;

            for (int i = childIndex; i < graph[node].size(); i++) {
                int childNode = graph[node].get(i).des;
                currentNode.setChildIndex(i+1);
                if(!visited[childNode]){
                    visited[childNode] = true;
                    dist[childNode] = ++step;
                    low[childNode] = step;

                    parents[childNode]=node;
                    stackVertex.push(new Node(childNode, 0));
                    stackEdges.push(graph[node].get(i));
                    low[node] = Math.min(dist[childNode], low[node]);
                    isPop = false;
                    break;
                }else if(parents[node]!=childNode){
                    low[node] = Math.min(dist[childNode], low[node]);
                }
            }

            if (isPop)
            {
                currentNode = stackVertex.pop();
                node = currentNode.node;

                if(node!=root){
                    int parentNode = parents[node];
                    low[parentNode]=Math.min(low[parentNode], low[node]);
                }

                if(node!=root && low[node]>dist[parents[node]]){
                    assignComponentId(parents[node], node);
                }
            }
        }
    }

    public static int componentId = 0;

    private static void assignComponentId(int src, int des){
        EdgeWithWeight edge = null;
        do{
            edge = stackEdges.pop();
            bridgeComponents[edge.des] = componentId;
            bridgeComponents[edge.src] = componentId;

            if(edge.src == src && edge.des == des){
                bridges.add(List.of(src, des, edge.weight));
                break;
            }
        }while(stackEdges.size()>0);

        componentId++;
        bridgeComponents[src] = componentId;
    }

    public static class EdgeWithWeight implements Comparable<EdgeWithWeight>{
        public int src;
        public int des;
        public int weight;

        public EdgeWithWeight(int src, int des, int weight) {
            this.src = src;
            this.des = des;
            this.weight = weight;
        }

        @Override
        public int compareTo(EdgeWithWeight o) {
            return this.weight-o.weight;
        }
    }

    public static class Node {
        public int node;
        public int childIndex;

        public Node(int x, int y) {
            this.node = x;
            this.childIndex = y;
        }

        public void setChildIndex(int childIndex) {
            this.childIndex = childIndex;
        }
    }


    static Collection<Integer>[] tree;
    static int n;
    static int level;
    static int[][] dp;
    static int[] depth;

    public static void buildLcaDpLevel(Collection<Integer>[] treeP, int root)
    {
        tree = treeP;
        n = tree.length;
        level = (int)Math.ceil(Math.log(n)/Math.log(2)) + 1;
        dp = new int[n][level];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < level; j++)
            {
                dp[i][j] = -1;
            }
        }

        boolean[] vs = new boolean[n];
        depth = new int[n];
//        dfs(root, -1, 0, vs);
        dfsDepthLevelWithStack(vs);
        calDp();
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

//    private static void dfs(int node, int parent, int level, boolean[] vs)
//    {
//        vs[node] = true;
//        dp[node][0] = parent;
//        depth[node] = level;
//
//        if(level==8000){
//            System.out.println("ddd");
//        }
//
//        for (var child : tree[node])
//        {
//            if (!vs[child])
//            {
//                dfs(child, node, level+1, vs);
//            }
//        }
//    }

    private static void dfsDepthLevelWithStack(boolean[] vs)
    {
        Stack<NodeInfo> nodeInfos = new Stack<>();

        nodeInfos.add(new NodeInfo(root, -1, 0));

        while(nodeInfos.size()>0){
            NodeInfo nodeInfo = nodeInfos.pop();
            int node = nodeInfo.node;
            int parent = nodeInfo.parent;
            vs[node] = true;
            dp[node][0] = parent;
            depth[node] = nodeInfo.level;

            for (var child : tree[node])
            {
                if (!vs[child])
                {
                    nodeInfos.add(new NodeInfo(child, node, nodeInfo.level+1));
                }
            }
        }
    }

    private static class NodeInfo{
        public int node;
        public int parent;
        public int level;

        public NodeInfo(int node, int parent, int level) {
            this.node = node;
            this.parent = parent;
            this.level = level;
        }
    }

    private static void calDp()
    {
        for (int j = 1; j < level; j++)
        {
            for (int i = 1; i < n; i++)
            {
                if (dp[i][j - 1] != -1)
                {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
        }
    }

    public static BufferedReader reader;

    public static void mainF( String[] argv ) throws Exception
    {
        if(true){
            FileReader file = new FileReader("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            reader = new BufferedReader(file);
        }else{
            InputStreamReader isr = new InputStreamReader(System.in);
            reader = new BufferedReader(isr);
        }

        ns = readIntArray();
        nt=ns[0];
        m=ns[1];

        solve();
        reader.close();
    }

    public static int readInt() throws IOException { return Integer.parseInt(reader.readLine()); }
    public static int[] readIntArray() throws IOException { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String[] readStringArray() throws IOException { return reader.readLine().split("[ \t]"); }
    public static int[][] readIntMatrix(int numberOfRows) throws IOException { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++){matrix[i] = readIntArray(); matrix[i][0]--;matrix[i][1]--;} return matrix; }


    public static List<EdgeWithWeight>[] buildListArrayWithWeight(int n, int[][] arr, boolean isDirected)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<EdgeWithWeight>();
        }

        HashMap<List<Integer>, Integer> map = new HashMap<>();

        for (var item : arr)
        {
            int src = item[0];
            int des = item[1];
            int weight = item[2];

            if(src>des){
                int tmp=des;
                des=src;
                src=tmp;
            }

            var key = List.of(src, des);

            if(map.containsKey(key)){
                weight = 0;
            }

            map.put(key, weight);
        }

        map.entrySet().forEach(entry -> {
            graph[entry.getKey().get(0)].add(new EdgeWithWeight(entry.getKey().get(0), entry.getKey().get(1), entry.getValue()));
            graph[entry.getKey().get(1)].add(new EdgeWithWeight(entry.getKey().get(1), entry.getKey().get(0), entry.getValue()));
        });

        return graph;
    }

    public static void printEdgeList(HashSet<Integer>[] biComponentGraph){
        HashSet<List> edges = new HashSet<>();
        List<Integer> edge;
        for (int i = 0; i < biComponentGraph.length; i++) {
            for (int j : biComponentGraph[i]) {
                if(i>j){
                    edge = List.of(j, i);
                }else{
                    edge = List.of(i, j);
                }

                if(!edges.contains(edge)){
                    edges.add(edge);
                    System.out.println(edge.get(0)+"-"+edge.get(1));
                }
            }
        }
    }
}

