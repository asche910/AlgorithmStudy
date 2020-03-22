import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        int check = check(100);
        System.out.println(check);
    }

    public static int check(int n) {
        int count = 0;
        for (int j = 2; j * j <= n; j++) {
            while (n % j == 0) {
                n /= j;
                System.out.println(n + " " + j);
                count++;
            }
        }
        if (n > 1) count++;
        return count;
    }
}
