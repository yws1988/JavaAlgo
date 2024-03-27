package graph.sorting;

/*
  Give n lines represent n collections id from 0 to n-1, each line contains a few numbers, the first number
  represents the number of the elements in this collection. The other numbers reprensents the id of
  the subcollections, there is no cycle dependency in the collection relations. Ouput the maxinum number of
  elements contained in one collection.
  For example:
    10 1 2
    10
    10 3
    2 1

    Output 32
 */


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class NumOfElementsInTopologicalOrderedCollections {
    public static int getMaximumNumOfElements(List<Integer>[] subCollections, int[] numOfElements, int n)
    {
        var orderedCollectionIdsList = TopologicalSorting.getSortingOrder(subCollections);
        var orderedCollectionIds = orderedCollectionIdsList.toArray(Integer[]::new);

        var setOfCollections = (HashSet<Integer>[])IntStream.range(0, n).mapToObj(s -> new HashSet<Integer>()).toArray(HashSet[]::new);

        for (int i = 0; i < n; i++)
        {
            int id = orderedCollectionIds[i];
            setOfCollections[id].add(id);

            for (var subId : subCollections[id])
            {
                setOfCollections[id].addAll(setOfCollections[subId]);
            }
        }

        var result = new int[n];
        for (int i = 0; i < n; i++)
        {
            result[i] = setOfCollections[i].stream().mapToInt(d -> numOfElements[d]).sum();
        }

        return Arrays.stream(result).max().getAsInt();
    }
}
