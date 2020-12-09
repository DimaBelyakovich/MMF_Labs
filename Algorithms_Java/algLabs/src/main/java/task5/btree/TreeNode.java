package task5.btree;

import helpers.RandomGenerators;

import java.util.*;

public class TreeNode {

    public int data;

    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    int depth=0;
    int level=0;
    int drawPos=0;

    public static TreeNode createRandomIntegerTree (int numNodes)
    {

        TreeNode root = new TreeNode ();
        root.data = RandomGenerators.getRandomInt(0,120);

        int treeSize = countNodes(root);
        while (treeSize < numNodes)
        {
            int count = numNodes - treeSize;
            while (count-- > 0)
                root.insertInteger(RandomGenerators.getRandomInt(0,120));
            treeSize = countNodes (root);
        }
        return root;
    }

    void insertInteger(int data)
    {
        if (this.data == data)
            return;
        if (this.data < data)
        {
            if (this.right == null)
            {
                this.right = new TreeNode();
                this.right.data = data;
                this.right.parent = this;
            }
            else
            {
                this.right.insertInteger(data);
            }
        }
        else
        {
            if (this.left == null)
            {
                this.left = new TreeNode();
                this.left.data = data;
                this.left.parent = this;
            }
            else
            {
                this.left.insertInteger(data);
            }
        }
    }


    static void drawTree(TreeNode root)
    {

        System.out.println("\n\nLevel order traversal of tree:");
        int depth = depth(root);
        setLevels (root, 0);

        int depthChildCount[] = new int [depth+1];


        LinkedList<TreeNode> q = new  LinkedList<TreeNode> ();
        q.add(root.left);
        q.add(root.right);

        root.drawPos = (int)Math.pow(2, depth-1)*H_SPREAD;
        currDrawLevel = root.level;
        currSpaceCount = root.drawPos;
        System.out.print(getSpace(root.drawPos) + root.data);

        while (!q.isEmpty())
        {
            TreeNode ele = q.pollFirst();
            drawElement (ele, depthChildCount, depth, q);
            if (ele == null)
                continue;
            q.add(ele.left);
            q.add(ele.right);
        }
        System.out.println();
    }

    static int currDrawLevel  = -1;
    static int currSpaceCount = -1;
    static final int H_SPREAD = 3;

    static void drawElement(TreeNode ele, int depthChildCount[], int depth, LinkedList<TreeNode> q)
    {
        if (ele == null)
            return;

        if (ele.level != currDrawLevel)
        {
            currDrawLevel = ele.level;
            currSpaceCount = 0;
            System.out.println();
            for (int i=0; i<(depth-ele.level+1); i++)
            {
                int drawn = 0;
                if (ele.parent.left != null)
                {
                    drawn = ele.parent.drawPos - 2*i - 2;
                    System.out.print(getSpace(drawn) + "/");
                }
                if (ele.parent.right != null)
                {
                    int drawn2 = ele.parent.drawPos + 2*i + 2;
                    System.out.print(getSpace(drawn2 - drawn) + "\\");
                    drawn = drawn2;
                }

                TreeNode doneParent = ele.parent;
                for (TreeNode sibling: q)
                {
                    if (sibling == null)
                        continue;
                    if (sibling.parent == doneParent)
                        continue;
                    doneParent = sibling.parent;
                    if (sibling.parent.left != null)
                    {
                        int drawn2 = sibling.parent.drawPos - 2*i - 2;
                        System.out.print(getSpace(drawn2-drawn-1) + "/");
                        drawn = drawn2;
                    }

                    if (sibling.parent.right != null)
                    {
                        int drawn2 = sibling.parent.drawPos + 2*i + 2;
                        System.out.print(getSpace(drawn2-drawn-1) + "\\");
                        drawn = drawn2;
                    }
                }
                System.out.println();
            }
        }
        int offset=0;
        int numDigits = (int)Math.ceil(Math.log10(ele.data));
        if (ele.parent.left == ele)
        {
            ele.drawPos = ele.parent.drawPos - H_SPREAD*(depth-currDrawLevel+1);
            //offset = 2;
            offset += numDigits/2;
        }
        else
        {
            ele.drawPos = ele.parent.drawPos + H_SPREAD*(depth-currDrawLevel+1);
            //offset = -2;
            offset -= numDigits;
        }

        System.out.print (getSpace(ele.drawPos - currSpaceCount + offset) + ele.data);
        currSpaceCount = ele.drawPos + numDigits/2;
    }

    public void inOrderInteger (String sep)
    {
        if (left != null)
            left.inOrderInteger(sep);
        System.out.print(data + sep);
        if (right != null)
            right.inOrderInteger(sep);
    }

    public static int depth (TreeNode n)
    {
        if (n == null)
            return 0;
        n.depth = 1 + Math.max(depth(n.left), depth(n.right));
        return n.depth;
    }


    public static int countNodes (TreeNode n)
    {
        if (n == null)
            return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    static void setLevels (TreeNode r, int level)
    {
        if (r == null)
            return;
        r.level = level;
        setLevels (r.left, level+1);
        setLevels (r.right, level+1);
    }

    static String getSpace (int i)
    {
        String s = "";
        while (i-- > 0)
            s += " ";
        return s;
    }

}