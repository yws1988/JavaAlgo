package unionFind;

public class UnionFind {
    public static Subset[] createSubsets(int n)
    {
        var subsets = new Subset[n];
        for (int v = 0; v < n; v++)
        {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
            subsets[v].size = 1;
        }

        return subsets;
    }

    public static int find(Subset[] subsets, int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    public static void union(Subset[] subsets, int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (xroot != yroot)
        {
            if (subsets[xroot].rank < subsets[yroot].rank){
                subsets[xroot].parent = yroot;
                subsets[yroot].size+=subsets[xroot].size;
                subsets[xroot].size = 0;
            }
            else if (subsets[yroot].rank < subsets[xroot].rank){
                subsets[yroot].parent = xroot;
                subsets[xroot].size+=subsets[yroot].size;
                subsets[yroot].size = 0;
            }
            else
            {
                subsets[xroot].parent = yroot;
                subsets[yroot].rank++;
                subsets[yroot].size+=subsets[xroot].size;
                subsets[xroot].size = 0;
            }
        }
    }

    public static class Subset
    {
        public int parent;
        public int rank;
        public int size;
    }
}


