package graph.tree.binarytree;

public class LowestCommonAncestorInBinaryTree{
    public static Node root;
    public static int getLowestCommonAncestor(int val1, int val2)
    {
        Node result = search(root, val1, val2);
        if (result != null) return result.value;
        return -1;
    }

    static Node search(Node node, int val1, int val2)
    {
        if (node == null) return null;

        if(node.value == val1 || node.value == val2)
        {
            return node;
        }

        var searchLeft = search(node.left, val1, val2);
        var searchRight = search(node.right, val1, val2);

        if (searchLeft != null && searchRight != null) return node;

        return searchLeft != null ? searchLeft : searchRight;
    }


    public static class Node
    {
        public Node left, right;
        public int value;

        public Node(int v)
        {
            value = v;
        }
    }
}
