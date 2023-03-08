/*
    Give a char matrix,  in the char grid it contains the following characters:
    '.' represents a crossable cell
    '#' represents a wall cell, is not crossable
    '!' an door cell
    's' source crossable cell axis(0, 0)
    'd' destination crossable cell(n-1, n-1)

    once you've walked on such a door cell, it becomes impassable.
    Indicating the minimum number of door cells to cross in order to reach the destination and return back
    to source. If it is possible to reach destination but not come back then return -1.
    If it is not possible to reach the source from destination, return -2.

    Input char grid like this:
    .....
    ##!!#
    #!!!#
    #!!##
    .....

    Output -1

    Input char grid:
    ....
    #!!#
    #!!#
    ....

    return 4
 */

package graph.connectivity;

import java.time.temporal.ValueRange;
import java.util.*;

public class ConnectedAreasBidirectionalPathCostInGrid {
    static int n;
    static Map<Integer, List<Pair>> nodesByArea;
    static Map<Integer, Character> type = new HashMap<>();
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    final static int INF = Integer.MAX_VALUE;

    public static int GetCost(String[] grid) {
        n = grid.length;
        nodesByArea = new HashMap<>();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = -1;
            }
        }

        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1) {
                    if (grid[i].charAt(j) == '.') {
                        nodesByArea.put(area, dfs(i, j, area, grid));
                        type.put(area, '.');
                        area++;
                    } else if (grid[i].charAt(j) == '!') {
                        var list = new ArrayList<Pair>();
                        for (int k = 0; k < 4; k++) {
                            int tx = i + dx[k];
                            int ty = j + dy[k];
                            if (tx >= 0 && tx < n && ty >= 0 && ty < n && grid[tx].charAt(ty) != '#') {
                                list.add(new Pair(tx, ty));
                            }
                        }

                        map[i][j] = area;
                        nodesByArea.put(area, list);
                        type.put(area, '!');
                        area++;
                    }
                }
            }
        }

        int[] twin = new int[area];
        int t = area;
        for (int i = 0; i < area; i++) {
            if (type.get(i) == '!') {
                twin[i] = t;
                t++;
            } else {
                twin[i] = -1;
            }
        }
        List<Node>[] ng = new ArrayList[t+1];

        for (int i = 0; i <= t; i++) {
            ng[i]=new ArrayList<>();
        }

        for (int i = 0; i < area; i++) {
            if (type.get(i) == '!') {
                for(var item : nodesByArea.get(i))
                {
                    ng[twin[i]].add(new Node(map[item.x][item.y],0, INF));
                }

                ng[i].add(new Node(twin[i], 1, 1));
            } else {
                for(var item : nodesByArea.get(i))
                {
                    ng[i].add(new Node(map[item.x][item.y], 0, INF));
                }
            }
        }

        var routeFromSourceToDestination1 = bellmanford(ng, 0, map[n - 1][n - 1]);

        if (grid[0].charAt(0) == '#' || routeFromSourceToDestination1.dis == INF) {
            return -2;
        }

        var path = routeFromSourceToDestination1.path.toArray();

        int lp = path.length;
        for (int i = 0; i < lp - 1; i++) {
            var bn = (int)path[i];
            var fn = (int)path[i + 1];

            for (int j = 0; j < ng[bn].size(); j++) {
                if (ng[bn].get(j).destination == fn) {
                    ng[bn].get(j).times -= 1;
                    ng[fn].add(new Node(bn, -ng[bn].get(j).cost, 1));
                    break;
                }
            }
        }

        var routeFromSourceToDestination2 = bellmanford(ng, 0, map[n - 1][n - 1]);

        if (routeFromSourceToDestination2.dis == INF) {
            return -1;
        }

        return routeFromSourceToDestination2.dis + routeFromSourceToDestination2.dis;
    }

    static BellmanfordResult bellmanford(List<Node>[] ng, int s, int d) {
        int len = ng.length;
        int[] dis = new int[len];
        int[] ps = new int[len];

        for (int i = 0; i < len; i++) {
            dis[i] = INF;
            ps[i] = -1;
        }

        dis[s] = 0;

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len; j++) {
                for(var e : ng[j])
                {
                    if (e.times > 0 && dis[j] != INF && dis[j] + e.cost < dis[e.destination]) {
                        dis[e.destination] = dis[j] + e.cost;
                        ps[e.destination] = j;
                    }
                }
            }
        }

        Stack<Integer> path = new Stack<>();
        path.push(d);
        int t = ps[d];
        while (t != -1) {
            path.push(t);
            t = ps[t];
        }

        return new BellmanfordResult(path,dis[d]);
    }

    public static class BellmanfordResult {
        public Stack<Integer> path;
        public int dis;

        public BellmanfordResult(Stack<Integer> path, int dis) {
            this.path = path;
            this.dis = dis;
        }
    }

    static List<Pair> dfs(int x, int y, int a, String[] grid) {
        List<Pair> list = new ArrayList<>();
        Stack<Pair> stack = new Stack<Pair>();
        map[x][y] = a;
        stack.push(new Pair(x, y));

        while (stack.size() > 0) {
            var next = stack.pop();
            for (int i = 0; i < 4; i++) {
                int tx = next.x + dx[i];
                int ty = next.y + dy[i];
                if (tx >= 0 && tx < n && ty >= 0 && ty < n) {
                    if (map[tx][ty] == -1 && grid[tx].charAt(ty) == '.') {
                        map[tx][ty] = a;
                        stack.push(new Pair(tx, ty));
                    } else if (grid[tx].charAt(ty) == '!') {
                        list.add(new Pair(tx, ty));
                    }
                }
            }
        }

        return list;
    }

    public static class Node {
        public int destination;
        public int cost;
        public int times;

        public Node(int d, int w, int t) {
            this.destination = d;
            this.cost = w;
            this.times = t;
        }
    }

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}



