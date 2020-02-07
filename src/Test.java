import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("Test...");

        Map<Short, String> map = new HashMap<>();
        for (short i = 0; i < 100; i++) {
            map.put(i, String.valueOf(i));
            map.remove( "");
        }
        System.out.println(map.size());
    }

    public int compress(char[] chars) {
        if (chars.length == 0) return 0;
        if (chars.length == 1) return 1;
        int size = 0;
        int count = 1;
        int i = 1;
        for (; i <= chars.length; i++) {
            if (i == chars.length || chars[i] != chars[i - 1]) {
                if (count > 1) {
                    chars[size++] = chars[i - 1];
                    System.out.println(count);
                    if (count < 10) {
                        chars[size++] = (char) ('0' + count);
                    } else if (count < 100) {
                        chars[size++] = (char) ('0' + count / 10);
                        chars[size++] = (char) ('0' + count % 10);
                    } else if (count < 1000) {
                        chars[size++] = (char) ('0' + count / 100);
                        chars[size++] = (char) ('0' + count % 100 / 10);
                        chars[size++] = (char) ('0' + count % 10);
                    }
                    count = 1;
                } else {
                    chars[size++] = chars[i - 1];
                }
            } else {
                count++;
            }
        }
        return size;
    }
}
