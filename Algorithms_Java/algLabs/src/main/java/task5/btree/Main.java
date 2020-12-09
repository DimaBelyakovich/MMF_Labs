package task5.btree;

import java.util.Stack;

public class Main {

    public static void printPostorder(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode current = root;

        while (true) {

            if (current != null) {
                if (current.right != null)
                    s.push(current.right);
                s.push(current);
                current = current.left;
                continue;
            }

            if (s.isEmpty())
                return;
            current = s.pop();

            if (current.right != null && !s.isEmpty() && current.right == s.peek()) {
                s.pop();
                s.push(current);
                current = current.right;
            } else {
                System.out.print(current.data + " ");
                current = null;
            }

        }
    }

    public static TreeNode search(TreeNode root, int key){
        if (root == null || key == root.data)
            return root;
        if (key < root.data)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    public static void main(String[] args)
    {
        TreeNode root = TreeNode.createRandomIntegerTree(14);
        root.inOrderInteger(", ");

        TreeNode.drawTree (root);

        printPostorder(root);

        System.out.println();
        System.out.print(search(root, 1));
    }


}
