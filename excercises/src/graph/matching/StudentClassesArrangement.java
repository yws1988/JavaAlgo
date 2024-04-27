package graph.matching;

import graph.sorting.TopologicalSorting;

import java.util.*;
import java.util.stream.IntStream;

import static graph.connectivity.StronglyConnectedComponentList.getSCC;

public class StudentClassesArrangement {

    /***
     * @param n
     * @param studentSlots slot info of the students[i]
     * @return get the arrangements for n student
     */
    public static int[] getArrangements(int n, int[][] studentSlots)
    {
        int[][] studentSlotInfos = new int[2*n][];

        for (int i = 0; i < n; i++)
        {
            studentSlotInfos[i * 2] = new int[] { studentSlots[i][0], i * 2 };
            studentSlotInfos[i * 2 + 1] = new int[] { studentSlots[i][1], i * 2 + 1 };
        }


        Arrays.sort(studentSlotInfos, Comparator.comparingInt(subarray -> subarray[0]));

        List<Integer>[] graph = new ArrayList[2*n];
        Arrays.setAll(graph, ArrayList<Integer>::new);

        int b = 0;
        for (int i = 1; i < 2 * n; ++i)
        {
            while (studentSlotInfos[b][0] + 60 < studentSlotInfos[i][0])
            {
                ++b;
            }

            for (int j = b; j < i; ++j)
            {
                graph[studentSlotInfos[j][1]].add(studentSlotInfos[i][1] % 2 == 1 ? studentSlotInfos[i][1] - 1 : studentSlotInfos[i][1] + 1);
                graph[studentSlotInfos[i][1]].add(studentSlotInfos[j][1] % 2 == 1 ? studentSlotInfos[j][1] - 1 : studentSlotInfos[j][1] + 1);
            }
        }

        int[] stronglyConnectedComponents = getSCC(graph, true);
        int len = graph.length;

        List<Integer>[] SccByComponentId = new ArrayList[len];
        Arrays.setAll(SccByComponentId, ArrayList<Integer>::new);

        for (int i = 0; i < n; ++i)
        {
            if (stronglyConnectedComponents[2 * i] == stronglyConnectedComponents[2 * i + 1])
            {
                System.out.println("KO");
                return null;
            }

            SccByComponentId[stronglyConnectedComponents[2 * i]].add(2 * i);
            SccByComponentId[stronglyConnectedComponents[2 * i + 1]].add(2 * i + 1);
        }

        List<Integer>[] graphForTopologicalSorting = new ArrayList[len];
        Arrays.setAll(graphForTopologicalSorting, ArrayList<Integer>::new);

        for (int i = 0; i < len; ++i)
        {
            graphForTopologicalSorting[i].add(stronglyConnectedComponents[i]);
            for (var connectedComponentElement : SccByComponentId[i]){
                for (var des : graph[connectedComponentElement]){
                    graphForTopologicalSorting[i].add(stronglyConnectedComponents[des]);
                }
            }
        }

        Stack<Integer> stack = TopologicalSorting.getSortingOrder(graphForTopologicalSorting);

        int[] cv = new int[n];

        // to verify if it needs to reverse
        for (int i : stack)
        {
            if (cv[i / 2] != 0) continue;

            cv[i / 2] = i % 2 == 0 ? 1 : 2;

            for (int m : SccByComponentId[i])
            {
                cv[m / 2] = m % 2 == 0 ? 1 : 2;
            }
        }

        return cv;
    }
}
