import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public static int kthSmallest(int[][] mat, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            pq.add(mat[i][0]);
        }
        int[] idxs = new int[m];
        while(--k > 0){

            pq.poll();
            int min = -1;
            for (int i = 0; i < m; i++) {
                if(idxs[i] + 1 < n){
                    if(min == -1 || mat[i][idxs[i] + 1] < mat[min][idxs[min] + 1]){
                        min = i;
                    }
                }
            }
            if(min == -1){
                System.out.println(k);
                break;
            }
            idxs[min]++;
            pq.add(mat[min][idxs[min]]);
        }
        int sum = 0;
        while(pq.size() > 0) sum += pq.poll();
        return sum;
    }
}