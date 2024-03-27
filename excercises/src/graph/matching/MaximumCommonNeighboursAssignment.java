package graph.matching;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class MaximumCommonNeighboursAssignment {

    public static String printMaxCommonNeighboursMatching(int[] rows, int[] columns, int[][] relations)
    {
        int n = rows.length + columns.length;
        HashSet<Integer>[] friends = IntStream.rangeClosed(0, n).mapToObj(s->new HashSet<Integer>()).toArray(HashSet[]::new);

        for (var item : relations)
        {
            friends[item[0]].add(item[1]);
            friends[item[1]].add(item[0]);
        }

        int h = rows.length;
        int w = columns.length;


        var friendsMatrix = new int[h][w];

        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                HashSet<Integer> columnFriends = friends[columns[j]];
                int common = (int) friends[rows[i]].stream().filter(s -> columnFriends.contains(s)).count();
                friendsMatrix[i][j] = common;
            }
        }

        int maxFriends = HungarianMatching.getMaxCostWithDifferentDimensions(friendsMatrix, h, w);

        List<String> res = new ArrayList<>();

        for (int i = 0; i < h; i++)
        {
            int j = HungarianMatching.xy[i];

            if (j < w)
            {
                int a = rows[i];
                int b = columns[j];

                res.add((a) + " " + (b));
            }
        }

        return String.join(",", res);
    }
}
