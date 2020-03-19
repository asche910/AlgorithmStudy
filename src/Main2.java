import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            arr[i] = new int[]{a, b};
        }
        if (n == 1) {
            System.out.println(1);
            return;
        }
        Arrays.sort(arr, (aa, bb) -> {
            if (aa[0] != bb[0]) return aa[0] - bb[0];
            else return aa[1] - bb[1];
        });

        int res = 1;
        int[] last = arr[0];
        for (int j = 1; j < n; j++) {
            if (arr[j][0] > last[0] && arr[j][1] > last[1]) {
                if (j != n - 1 && arr[j][1] > arr[j + 1][1]) {

                } else {
                    res++;
                    last = arr[j];
                }
            }
        }
        System.out.println(res);
    }

    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length < 2) return envelopes.length;
        Arrays.sort(envelopes, (a, b) -> {
            if(a[0] == b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        });
        int[] dp = new int[envelopes.length];
        for(int i = 1; i < envelopes.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[envelopes.length - 1];

    }
}
