package datastructures.trees;

public class SegmentTreeSum
{
    int[] _arr;
    int n;
    public long[] Sum;
    public int N;

    public SegmentTreeSum(int[] arr)
    {
        _arr = arr;
        n = arr.length;
        this.construct();
    }

    void construct()
    {
        N = (int)Math.pow(2, ((int)Math.ceil(Math.log(_arr.length)/Math.log(2)))) * 2 - 1;
        Sum = new long[N];
        constructUtil(0, n-1, 0);
    }

    long constructUtil(int s, int e, int idx)
    {
        if (s == e) return Sum[idx]=_arr[s];

        int mid = mid(s, e);
        return Sum[idx] = constructUtil(s, mid, idx * 2 + 1) + constructUtil(mid + 1, e, idx * 2 + 2);
    }

    int mid(int s, int e)
    {
        return (s + e) / 2;
    }

    // qt:query start. qe:query end
    public long sum(int qs, int qe)
    {
        return sumUtil(qs, qe, 0, n-1, 0);
    }

    long sumUtil(int qs, int qe, int s, int e, int idx)
    {
        if(qs<=s && qe>=e)
        {
            return Sum[idx];
        }

        if(qe<s || qs > e)
        {
            return 0;
        }

        int mid = mid(s, e);

        return sumUtil(qs, qe, s, mid, idx * 2 + 1) + sumUtil(qs, qe, mid+1, e, idx * 2 + 2);
    }

    public void update(int key, int value)
    {
        int diff = value - _arr[key];
        _arr[key] = value;

        if (diff != 0)
        {
            updateUtil(key, diff, 0, n - 1, 0);
        }
    }

    void updateUtil(int key, int diff, int s, int e, int idx)
    {
        if(key<s || key > e)
        {
            return;
        }

        Sum[idx] += diff;

        if (s != e)
        {
            int mid = mid(s, e);
            updateUtil(key, diff, s, mid, idx * 2 + 1);
            updateUtil(key, diff, mid + 1, e, idx * 2 + 2);
        }

    }
}
