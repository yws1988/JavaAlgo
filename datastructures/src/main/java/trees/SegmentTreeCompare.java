package trees;

import java.util.function.BiFunction;

public class SegmentTreeCompare {
    private int[] array;
    private int sizeOfArray;
    private Node[] tree;
    private int sizeOfTree;
    private BiFunction<Node, Node, Node> func;

    public SegmentTreeCompare(int[] array, BiFunction<Node, Node, Node> func) {
        this.array = array;
        sizeOfArray = array.length;
        this.func = func;
        this.build();
    }

    void build() {
        sizeOfTree = (int) Math.pow(2, ((int) Math.ceil(Math.log(sizeOfArray)/Math.log(2)))) * 2 - 1;
        tree = new Node[sizeOfTree];

        buildUntil(0, 0, sizeOfArray - 1);
    }

    Node buildUntil(int index, int s, int e) {
        if (s == e) return tree[index] = new Node(s, array[s]);

        int mid = mid(s, e);
        return tree[index] = this.func.apply(buildUntil(index * 2 + 1, s, mid), buildUntil(index * 2 + 2, mid + 1, e));
    }

    int mid(int s, int e) {
        return (s + e) / 2;
    }

    // qt:query start. qe:query end
    public Node value(int qs, int qe) {
        return getValueUtil(qs, qe, 0, sizeOfArray - 1, 0);
    }

    Node getValueUtil(int qs, int qe, int s, int e, int idx) {
        if (qs <= s && qe >= e) {
            return tree[idx];
        }

        if (qe < s || qs > e) {
            return null;
        }

        int mid = mid(s, e);

        return this.func.apply(getValueUtil(qs, qe, s, mid, idx * 2 + 1), getValueUtil(qs, qe, mid + 1, e, idx * 2 + 2));
    }

    public void update(int key, int value) {
        if (value != array[key]) {
            array[key] = value;

            updateUtil(key, value, 0, sizeOfArray - 1, 0);
        }

    }

    Node updateUtil(int key, int value, int s, int e, int idx) {
        if (key < s || key > e) {
            return tree[idx];
        }

        if (s == e) {
            tree[idx] = new Node(key, value);
            return tree[idx];
        }

        int mid = mid(s, e);

        tree[idx] = this.func.apply(updateUtil(key, value, s, mid, idx * 2 + 1), updateUtil(key, value, mid + 1, e, idx * 2 + 2));

        return tree[idx];

    }

    public static class Node implements Comparable<Node>{
        int index;
        int value;

        public Node(int index, int value)
        {
            index = index;
            value = value;
        }

        @Override
        public int compareTo(Node other) {
            int result = this.value - other.value;
            if (result == 0) {
                return other.index - this.index;
            }

            return result;
        }
    }
}
