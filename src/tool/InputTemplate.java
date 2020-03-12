package tool;

import java.util.Scanner;

public class InputTemplate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M, N;
        while (scanner.hasNextInt()) {
            M = scanner.nextInt();
            N = scanner.nextInt();
            scanner.nextLine();
            int[] nums1 = new int[M];
            int[] nums2 = new int[N];
            for (int i = 0; i < M; i++) {
                nums1[i] = scanner.nextInt();
            }
            for (int i = 0; i < N; i++) {
                nums2[i] = scanner.nextInt();
            }

        }
    }
}
