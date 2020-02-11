package dp;

import java.util.Arrays;

/**
 * 0-1背包问题
 * 递推：物品由少增多，价值由少增多
 *
 * @see <a href="https://www.nowcoder.com/discuss/3574?type=all&order=time&pos=&page=1">Nowcoder</a>
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] w = {2, 2, 6, 5, 4}; //物品重量
        int[] v = {6, 3, 5, 4, 6}; //物品价值
        int size = 10; // 背包容量

        int[] x = new int[w.length];  // 记录物品装入情况，0表示不转入，1表示装入
        x[0] = 1; // 初始值表示第一个物品已装入背包

        //需要维护的二维表，为了方便计算加入一列，其中第0列表示背包容量为0时背包的最大价值为0
        // m[i][j], i表示物品(从0开始)，j表示背包容量（从1开始）, 整个表示当前的最大价值
        int[][] m = new int[5][size + 1];

        // 初始化，表示只有第一件物品的情况
        for (int j = 1; j <= size; j++) {
            if (j >= w[0]) m[0][j] = v[0];
        }

        for (int i = 1; i < w.length; i++) {
            for (int j = 1; j <= size; j++) {
                if (j < w[i]) {
                    // 无法装入背包
                    m[i][j] = m[i - 1][j];
                } else {
                    // 价值更高则装入，否则不装入
                    m[i][j] = Math.max(m[i - 1][j - w[i]] + v[i], m[i - 1][j]);
                }
            }
        }

        System.out.println("Max value: " + m[w.length - 1][size]);

        for (int i = w.length - 1; i >= 1; i--) {
            // 大于表明装入了背包
            if (m[i][size] > m[i - 1][size]) {
                x[i] = 1;
                size -= w[i];
            }
        }
        System.out.println("Load situation: " + Arrays.toString(x));
    }
}
