package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @see <a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal">Here</a>
 */
public class InOrderTraversal {
    public static void main(String[] args) {

    }

    /**
     * 迭代实现中序遍历
     */
    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 递归实现中序遍历
     */
    static List<Integer> res = new ArrayList<>();

    public static List<Integer> inOrderByRecursive(TreeNode root){
        if(root == null) return res;
        inOrderByRecursive(root.left);
        res.add(root.val);
        inOrderByRecursive(root.right);
        return res;
    }
}
