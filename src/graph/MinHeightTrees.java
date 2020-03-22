package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，
 * 具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。
 *
 * 依次删除叶子节点，最后剩下即为所求
 * @see <a href="https://leetcode-cn.com/problems/minimum-height-trees/">Here</a>
 */
public class MinHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n < 3) {
            for (int i = 0; i < n; i++) res.add(i);
            return res;
        }
        List<LinkedList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(new LinkedList<>());
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (list.get(i).size() == 1) leaves.add(i);
        }
        while (n > 2) {
            n -= leaves.size();
            List<Integer> next = new ArrayList<>();
            for (Integer leave : leaves) {
                Integer temp = list.get(leave).pop();
                list.get(temp).remove(leave);
                if (list.get(temp).size() == 1) next.add(temp);
            }
            leaves = next;
        }
        return leaves;
    }
}
