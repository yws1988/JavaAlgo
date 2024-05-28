package graph.counting;
/*
Given an undirected graph matrix with n vertex.
Matrix[i][j] is 1 if vertex i connects to vertex j, if i==j, the value will be 0.
Count the number of circles with length four.
 */
public class CountingCyclesWithLengthFour {
    public static long numOfCycleWithLengthFour(short[][] graph, int n){
        long num=0;

        for (int i = 0; i < n - 1; ++i)
        {
            for (int j = i + 1; j < n; ++j)
            {
                int numOfCommonNeighbours = 0;
                for (int k = 0; k < n; k++)
                {
                    numOfCommonNeighbours += graph[i][k] & graph[j][k];
                }
                num += numOfCommonNeighbours * (numOfCommonNeighbours - 1) / 2;
            }
        }

        return num/2;
    }
}
