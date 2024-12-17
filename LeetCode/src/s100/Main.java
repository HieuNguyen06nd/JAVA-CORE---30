package s100;

public class Main {

    public static void main(String[] args) {
        MyTreeNode tree1 = new MyTreeNode(1);
        tree1.left = new MyTreeNode(2);
        tree1.right = new MyTreeNode(3);

        MyTreeNode tree2 = new MyTreeNode(1);
        tree2.left = new MyTreeNode(2);
        tree2.right = new MyTreeNode(3);

        System.out.println(isSameTree(tree1, tree2)); // Should print true

        tree2.right.val = 4;
        System.out.println(isSameTree(tree1, tree2)); // Should print false
    }


    public static class MyTreeNode {
        int val;
        MyTreeNode left;
        MyTreeNode right;
        MyTreeNode() {}
        MyTreeNode(int val) { this.val = val; }
        MyTreeNode(int val, MyTreeNode left, MyTreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSameTree(MyTreeNode p, MyTreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
