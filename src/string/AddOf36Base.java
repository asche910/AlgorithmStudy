package string;

import java.io.IOException;
import java.util.HashMap;

/**
 * 36进制加法
 * 分别为 0-9 、a-z
 * <p>
 * e.g. "1b" + "2x" = "48"
 */
public class AddOf36Base {
    public static void main(String[] args) throws IOException {
        System.out.println("Test...");

        System.out.println(add("1b", "2x"));
    }

    public static String add(String str1, String str2) {
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();

        // 字符到数字和数字到字符的映射
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = new char[36];
        for (int i = 0; i < 10; i++) {
            map.put((char) (i + '0'), i);
            arr[i] = (char) (i + '0');
        }
        for (int i = 10; i < 36; i++) {
            map.put((char) ('a' + i - 10), i);
            arr[i] = (char) ('a' + i - 10);
        }

        int i = chs1.length - 1, j = chs2.length - 1;
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                carry += map.get(chs1[i--]);
            }
            if (j >= 0) {
                carry += map.get(chs2[j--]);
            }
            builder.append(arr[carry % 36]);
            carry = carry > 35 ? 1 : 0;
        }
        return builder.reverse().toString();
    }
}
