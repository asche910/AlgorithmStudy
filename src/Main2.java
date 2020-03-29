import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        int a = 2;
        a >>>= 1;
        System.out.println(a);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = sc.next();
            }
            char[][] map = new char[n][];
            for (int i = 0; i < n; i++) {
                map[i] = strs[i].toCharArray();
            }

            System.out.println(-1);
        }
    }

}
