package graph;

import java.util.*;

/**
 * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，
 * 并返回计算结果。如果结果不存在，则返回 -1.0。
 * 示例 :
 * 给定 a / b = 2.0, b / c = 3.0
 * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
 * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * @see <a href="https://leetcode-cn.com/problems/evaluate-division/">Here</a>
 */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equ = equations.get(i);
            String s = equ.get(0);
            String t = equ.get(1);
            if (!graph.containsKey(s)) graph.put(s, new HashMap<>());
            if (!graph.containsKey(t)) graph.put(t, new HashMap<>());
            graph.get(s).put(t, values[i]);
            graph.get(t).put(s, 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        Set<String> set = new HashSet<>();
        int index = 0;
        for (int i = 0; i < queries.size(); i++) {
            List<String> que = queries.get(i);
            res[i] = dfs(graph, set, que.get(0), que.get(1));
            set.clear();
        }
        return res;
    }

    private double dfs(Map<String, Map<String, Double>> graph, Set<String> visited, String from, String to) {
        if (!graph.containsKey(from)) return -1;
        if (from.equals(to)) return 1;
        for (String key : graph.get(from).keySet()) {
            if (key.equals(to)) return graph.get(from).get(to);
            else if (!visited.contains(key)) {
                visited.add(key);
                double val = dfs(graph, visited, key, to);
                if (val != -1) return graph.get(from).get(key) * val;
            }
        }
        return -1;
    }
}
