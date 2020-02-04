package string;

/**
 * 字符串压缩算法
 * Input: HG[3|B[2|CA]]F
 * Output: HGBCACABCACABCACAF
 * 注意并列（外层和内层）的情况
 * @link https://www.nowcoder.com/questionTerminal/c27561e5b7e0441493adb9a54071888d
 */
public class Compression {
    public static void main(String[] args) {
        System.out.println(tar("HG[2|  B[2|CA]Q  [2|W]  ]F"));

    }

    public static String tar(String src) {
        int index = 0;
        while (index < src.length()) {
            if (src.charAt(index) == ']') {
                int i = index;
                int j = 0;
                while (i >= 0) {
                    if (src.charAt(i) == '|') j = i;
                    if (src.charAt(i) == '[') break;
                    i--;
                }
                int num = Integer.parseInt(src.substring(i + 1, j));
                String subStr = src.substring(j + 1, index);
                StringBuilder builder = new StringBuilder();
                for (int m = 0; m < num; m++) {
                    builder.append(subStr);
                }
                src = src.replace(src.substring(i, index + 1), builder.toString());
                index = j - 1;
            }
            index++;
        }
        return src;
    }
}
