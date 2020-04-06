package graph;

import java.util.Arrays;

/**
 * 网络延迟时间
 * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点，
 * w 是一个信号从源节点传递到目标节点的时间。
 * 现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
 *
 * 使用Dijkstra算法解决有权路径问题
 *
 * @see <a href="https://leetcode-cn.com/problems/network-delay-time/">Here</a>
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(graph[i], -1);
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }
        int[] distance = new int[N + 1];
        Arrays.fill(distance, -1);
        for (int i = 1; i <= N; i++) {
            distance[i] = graph[K][i];
        }
        distance[K] = 0;

        boolean[] visited = new boolean[N + 1];
        visited[K] = true;
        for (int i = 1; i <= N; i++) {
            int minDis = Integer.MAX_VALUE;
            int minInx = 1;
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && distance[j] != -1 && distance[j] < minDis) {
                    minDis = distance[j];
                    minInx = j;
                }
            }
            visited[minInx] = true;
            for (int j = 1; j <= N; j++) {
                if (graph[minInx][j] != -1) {
                    if (distance[j] != -1) {
                        distance[j] = Math.min(distance[j], distance[minInx] + graph[minInx][j]);
                    } else {
                        distance[j] = distance[minInx] + graph[minInx][j];
                    }
                }
            }
        }
        int maxDis = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == -1) return -1;
            maxDis = Math.max(maxDis, distance[i]);
        }
        return maxDis;
    }

    /**
     * 简化版 Dijkstra 算法
     *
     * 最短距离在原数组上更新
     */
    public int networkDelayTime2(int[][] times, int N, int K) {
        // 用Integer_MAX_VALUE可能会溢出
        int INF = 1000_000_000;
        int[][] graph = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                graph[i][j] = i == j ? 0 : INF;
            }
        }
        graph[K][0] = INF;
        for(int[] time: times){
            graph[time[0]][time[1]] = time[2];
        }
        boolean[] visited = new boolean[N + 1];
        int min = K, newMin = 0;
        while(min > 0){
            visited[min] = true;
            for(int j = 1; j <= N; j++){
                graph[K][j] = Math.min(graph[K][j], graph[K][min] + graph[min][j]);
                if(!visited[j] && graph[K][j] < graph[K][newMin]){
                    newMin = j;
                }
            }
            min = newMin;
            newMin = 0;
        }
        int res = -1;
        for(int j = 1; j <= N; j++){
            res = Math.max(res, graph[K][j]);
            if(res == INF) return -1;
        }
        return res;
    }
}
