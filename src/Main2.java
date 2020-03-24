import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
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

//    private void reverse(char[][] map )
}
