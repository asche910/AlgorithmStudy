package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PostOrderTraversal {
    public static void main(String[] args) {

    }


    /**
     * 迭代实现后续遍历：利用链表反向输出
     * 类似先序遍历
     */
    public static List<Integer> postOrderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.addFirst(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return res;
    }

    /**
     * 递归实现后序遍历
     */
    static List<Integer> res = new ArrayList<>();

    public static List<Integer> postOrderByRecursive(TreeNode root){
        if(root == null) return res;
        postOrderByRecursive(root.left);
        postOrderByRecursive(root.right);
        res.add(root.val);
        return res;
    }
}
