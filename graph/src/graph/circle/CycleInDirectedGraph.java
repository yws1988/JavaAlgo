package graph.circle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CycleInDirectedGraph {
    public static boolean doesGraphContainsCycle(List<Integer>[] graph)
    {
        int v = graph.length;
        var vs = new boolean[v];
        var recVsStack = new boolean[v];

        for (int i = 0; i < v; i++)
        {
            if (!vs[i] && DFSUtil(i, vs, recVsStack, graph))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean DFSUtil(int i, boolean[] vs, boolean[] recVsStack, List<Integer>[] graph)
    {
        vs[i] = true;
        recVsStack[i] = true;
        for (int child : graph[i])
        {
            if (!vs[child] && DFSUtil(child, vs, recVsStack, graph))
            {
                return true;
            }

            if (recVsStack[child])
            {
                return true;
            }
        }
        recVsStack[i] = false;
        return false;
    }

    public static boolean doesGraphContainsCycleWithDegrees(List<Integer>[] graph)
    {
        int v = graph.length;
        int[] indegrees = new int[v];

        for (int i = 0; i < v; i++) {
            for (int c : graph[i]) {
                indegrees[c]++;
            }
        }

        Deque<Integer> deque=new ArrayDeque<>();

        for (int i = 0; i < v; i++) {
            if(indegrees[i]==0)
                deque.add(i);
        }

        int processedNode = 0;
        while(!deque.isEmpty()){
            int node = deque.pollFirst();
            for (int childNode : graph[node]){
                if(--indegrees[childNode]==0){
                    deque.push(childNode);
                }
            }
        }

        return processedNode==v ? false : true;
    }
}
