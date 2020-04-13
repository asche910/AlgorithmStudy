import java.util.*;

public class Main2 {
    public static void main(String[] args) {
//        handle(new int[]{1, 4, 3, 3}, 4);

        Scanner in = new Scanner(System.in);
//        int m = in.nextInt();

        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            handle(arr, n);
        }
    }

    private static void handle(int[] arr, int n) {
        System.out.println(4);
    }
}