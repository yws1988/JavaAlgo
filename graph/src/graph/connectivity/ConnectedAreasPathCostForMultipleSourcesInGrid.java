package graph.connectivity;

import graph.flow.FordFulkersonMaximumFlow;

import java.util.HashSet;

public class ConnectedAreasPathCostForMultipleSourcesInGrid
{
    static int n;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static int getMaxFlow(String[] graph)
    {
        n = graph.length;
        var costs = buildConnectedAreasCostMatrix(graph, 'p', 'c', '?', '#');
        if (costs == null)
        {
            return -1;
        }

        int numOfVetexes = costs.length;

        return FordFulkersonMaximumFlow.getMaximunFlow(costs, 0, numOfVetexes - 1);
    }

    public static int[][] buildConnectedAreasCostMatrix(String[] graph, char symbolSource, char symbolDestination, char symbolCost, char symbolWall)
    {
        var vs = new boolean[n][n];

        int counter = 0;
        int[][] areas = new int[n][n];
        int nTwinNodes = 0;
        int[][] twins = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (graph[i].charAt(j) == symbolCost)
                {
                    twins[i][j] = ++nTwinNodes;
                    areas[i][j] = ++counter;
                    vs[i][j] = true;
                }
                else if (graph[i].charAt(j) == symbolWall)
                {
                    vs[i][j] = true;
                }
                else
                {
                    if (!vs[i][j])
                    {
                        ++counter;
                        buildAreasWithDfs(graph, vs, areas, counter, i, j);
                    }
                }
            }
        }

        int numOfVetexes = counter + nTwinNodes + 2;
        var costs = new int[numOfVetexes][numOfVetexes];

        var sources = new HashSet<Integer>();
        var destinations = new HashSet<Integer>();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (graph[i].charAt(j) == 'p')
                {
                    sources.add(areas[i][j]);
                }
                else if (graph[i].charAt(j) == 'c')
                {
                    destinations.add(areas[i][j]);
                }
                else if (graph[i].charAt(j) == '?')
                {
                    int sNode = areas[i][j];
                    int dNode = twins[i][j] == 0 ? sNode : twins[i][j] + counter;
                    costs[sNode][dNode] = 1;
                    for (int h = 0; h < 4; h++)
                    {
                        int tx = i + dx[h];
                        int ty = j + dy[h];

                        if (isSafe(tx, ty) && areas[tx][ty] != 0)
                        {
                            int tsNode = areas[tx][ty];
                            int tdNode = twins[tx][ty] == 0 ? tsNode : twins[tx][ty] + counter;
                            costs[tdNode][sNode] = 1;
                            costs[dNode][tsNode] = 1;
                        }
                    }
                }
            }
        }

        // Add the super source and destination
        for (var patient : sources)
        {
            costs[0][patient] = Integer.MAX_VALUE;
        }

        for (var canard : destinations)
        {
            costs[canard][numOfVetexes - 1] = Integer.MAX_VALUE;
        }

        sources.retainAll(destinations);

        if (sources.size()>0)
        {
            System.out.println("-1");
            return null;
        }

        return costs;
    }



    static void buildAreasWithDfs(String[] graph, boolean[][] vs, int[][] area, int counter, int x, int y)
    {
        vs[x][y] = true;
        area[x][y] = counter;

        for (int i = 0; i < 4; i++)
        {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (isSafe(tx, ty) && !vs[tx][ty] && graph[tx].charAt(ty) != '?' && graph[tx].charAt(ty) != '#')
            {
                buildAreasWithDfs(graph, vs, area, counter, tx, ty);
            }
        }
    }

    static boolean isSafe(int tx, int ty)
    {
        return tx >= 0 && tx < n && ty >= 0 && ty < n;
    }
}

