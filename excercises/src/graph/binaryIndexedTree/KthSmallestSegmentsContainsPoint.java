package graph.binaryIndexedTree;

/*
You are given N segments [L, R]. Now, you need to answer some queries based on these segments.
Overall, you need to answer Q queries. In each query you shall be given 2 integers K and X.
You need to find the size of the Kth smallest segment that contains point X.
If no K segments contain point X, returns -1 instead as the answer to that query.
A segment [L, R] is said to contain a point X, if  L<=X<=R.
When we speak about the  smallest segment, we refer to the one having the smallest size.
*/

import datastructures.trees.BinaryIndexedTree;

import java.util.*;

public class KthSmallestSegmentsContainsPoint
{
    /// <summary>
    /// get all the result into an array
    /// </summary>
    /// <param name="intervals">All the segments [L, R]</param>
    /// <param name="queries">All the queries (K, X)</param>
    /// <returns></returns>
    public static int[] getResult(List<Interval> intervals, List<Query> queries)
    {
        int n = intervals.size();
        var size = new int[n];
        var sortedSize = new int[n];

        for (int i = 0; i < n; i++)
        {
            size[i] = intervals.get(i).right - intervals.get(i).left + 1;
            sortedSize[i] = size[i];
        }

        var dataList = new ArrayList<Integer>();
        int query_size = queries.size();

        for (int i = 0; i < query_size; i++)
        {
            dataList.add(queries.get(i).point);
        }

        for (int i = 0; i < n; i++)
        {
            dataList.add(intervals.get(i).left);
            dataList.add(intervals.get(i).right);
        }

        Collections.sort(dataList);
        Arrays.sort(sortedSize);

        var maxn = dataList.size() + 2;
        var cnt = new int[maxn];

        for (int i = 0; i < n; i++)
        {
            intervals.get(i).left = Collections.binarySearch(dataList, intervals.get(i).left);
            intervals.get(i).right = Collections.binarySearch(dataList, intervals.get(i).right);
            size[i] = Arrays.binarySearch(sortedSize, size[i]);
            cnt[intervals.get(i).left]++;
            cnt[intervals.get(i).right + 1]++;
        }

        var listOfSizePairs = new PairSize[maxn][];

        for (int i = 0; i < maxn; i++)
        {
            listOfSizePairs[i] = new PairSize[cnt[i]];
            cnt[i] = 0;
        }

        for (int i = 0; i < n; i++)
        {
            int curr1 = intervals.get(i).left, curr2 = intervals.get(i).right + 1;

            listOfSizePairs[curr1][cnt[curr1]++] = new PairSize(size[i], 1);

            listOfSizePairs[curr2][cnt[curr2]++] = new PairSize(size[i], -1);
        }

        Arrays.fill(cnt, 0);

        for (int i = 0; i < query_size; i++)
        {
            queries.get(i).point = Collections.binarySearch(dataList, queries.get(i).point);

            cnt[queries.get(i).point]++;
        }

        var listOfQueryPair = new PairQuery[maxn][];
        for (int i = 0; i < maxn; i++)
        {
            listOfQueryPair[i] = new PairQuery[cnt[i]];
            cnt[i] = 0;
        }

        for (int i = 0; i < query_size; i++)
        {
            int curr = queries.get(i).point;

            listOfQueryPair[curr][cnt[curr]++] = new PairQuery(i, queries.get(i).k);
        }

        var binaryIndexedTree = new BinaryIndexedTree(n);

        var res = new int[query_size];

        for (int i = 0; i < maxn; i++)
        {
            for (PairSize pair : listOfSizePairs[i])
            {
                binaryIndexedTree.update(pair.indexOfSize, pair.value);
            }

            for (PairQuery query : listOfQueryPair[i])
            {
                int k = query.k, low = 0, high = n-1;

                while (low < high)
                {
                    int mid = (low + high) >> 1;

                    if (binaryIndexedTree.query(mid) >= k)
                    {
                        high = mid;
                    }
                    else
                    {
                        low = mid + 1;
                    }
                }

                res[query.index] = binaryIndexedTree.query(low) >= k ? low : -1;
            }
        }

        for (int i = 0; i < query_size; i++)
        {
            res[i] = res[i] == -1 ? -1 : sortedSize[res[i]-1];
        }

        return res;
    }


    public static class Interval implements Comparator<Interval>
    {
        public int left;
        public int right;

        public Interval(int left, int right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compare(Interval o1, Interval o2) {
            return (o1.right - o1.left) - (o2.right - o2.left);
        }
    }

    public static class Query
    {
        public int k;
        public int point;

        public Query(int k, int point)
        {
            this.k = k;
            this.point = point;
        }
    }

    public static class PairSize
    {
        public int indexOfSize;
        public int value;

        public PairSize(int idx, int val)
        {
            indexOfSize = idx;
            value = val;
        }
    }

    public static class PairQuery
    {
        public int index;
        public int k;

        public PairQuery(int idx, int val)
        {
            index = idx;
            k = val;
        }
    }


}
