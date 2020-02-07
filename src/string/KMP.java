package string;

import java.util.Arrays;

/**
 * 关键在于求next数组
 *
 * @link https://blog.csdn.net/v_JULY_v/article/details/7041827
 */
public class KMP {
    public static void main(String[] args) {
        System.out.println(kmpSearch("abcdabce", "abce"));
    }

    public static int kmpSearch(String str, String subString){
        if(str == null || subString == null) return -1;
        if(subString.equals("")) return 0;
        int[] next = calNext(subString);
        int i = 0;
        int j = 0;
        int sLen = str.length();
        int pLen = subString.length();
        while(i < sLen && j < pLen){
            if(j == -1 || str.charAt(i) == subString.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        if(j == pLen){
            return i - j;
        }else{
            return -1;
        }
    }

    public static int[] calNext(String pattern){
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int k = -1; // 前缀索引
        int j = 0; // 后缀索引
        while(j < pattern.length() - 1){
            if(k == -1 || pattern.charAt(k) == pattern.charAt(j)){
                k++;
                j++;
                if(pattern.charAt(k) != pattern.charAt(j)){
                    next[j] = k; // 未加if前仅有这一行
                }else{
                    //因为不能出现p[j] = p[ next[j ]]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
                    next[j] = next[next[k]];
                }
            }else{
                k = next[k];
            }
        }
        return next;
    }
}
