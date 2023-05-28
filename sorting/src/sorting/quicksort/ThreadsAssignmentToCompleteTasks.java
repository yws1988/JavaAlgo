package sorting.quicksort;

/*
 There are m tasks, each task takes time interval [a, b], a computer processor can have n
 threads number from 1 to n, each thread can only process one task in the same time.
 Give all the time intervals for m tasks. Output the thread assignments for each task in order
 the computer can finish all the tasks.
    For example 6 threads with 7 following tasks:
    1 3
    1 4
    1 5
    1 6
    1 7
    2 9
    3 11

    Return
    1 2 3 4 5 6 1
    Assign your 6 threads to the first 6 queries. For the 7th query starting
    at time 3, you can use cable 1 which was assigned to a request ending at the time
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ThreadsAssignmentToCompleteTasks
{
    public static int[] getThreadAssignments(List<Task> tasks, int numOfThreads)
    {
        Collections.sort(tasks, new TaskComparator());
        int numOfTasks = tasks.size();

        var currentEndTimesOfThreads = new int[numOfThreads];
        var assignments = new int[numOfTasks];

        for (var task : tasks)
        {
            boolean hasAvailableThread = false;

            for (int k = 0; k < numOfThreads; k++)
            {
                if (currentEndTimesOfThreads[k] <= task.left)
                {
                    hasAvailableThread = true;
                    currentEndTimesOfThreads[k] = task.right;
                    assignments[task.index] = k + 1;

                    break;
                }
            }

            if (!hasAvailableThread)
            {
                return null;
            }
        }

        return assignments;
    }

    public static class Task{
        public int left;
        public int right;
        public int index;

        public Task(int left, int right, int index) {
            this.left = left;
            this.right = right;
            this.index = index;
        }
    }

    public static class TaskComparator implements Comparator<Task>{
        @Override
        public int compare(Task o1, Task o2) {
            return o1.left - o2.left;
        }
    }
}
