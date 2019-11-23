
public class TreeTest {
    public static void main(String[] args) {

    }

    public int rob(TreeNode root) {
        int sum1 = reRob(root, true);
        int sum2 = reRob(root, false);
        return Math.max(sum1, sum2);
    }

    public int reRob(TreeNode node, boolean canRob) {
        if (node == null) {
            return 0;
        }
        if (canRob) {
            int sum1 = node.val + reRob(node.left, false) + reRob(node.right, false);
            int sum2 = reRob(node.left, true) + reRob(node.right, true);
            return Math.max(sum1, sum2);
        } else {
            return reRob(node.left, true) + reRob(node.right, true);
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}