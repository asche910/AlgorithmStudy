package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @see <a href="https://leetcode-cn.com/problems/binary-tree-preorder-traversal/">Here</a>
 */
public class PreOrderTraversal {
    public static void main(String[] args) {

    }

    /**
     *迭代实现前序遍历
     */
    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);

            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
        return res;
    }


    /**
     * 递归实现前序遍历
     */
    static List<Integer> res = new ArrayList<>();

    public static List<Integer> preOrderByRecursive(TreeNode root){
        if(root == null) return res;
        res.add(root.val);
        preOrderByRecursive(root.left);
        preOrderByRecursive(root.right);
        return res;
    }
}
