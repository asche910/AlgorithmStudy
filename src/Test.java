import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("Test...");
        String str = "abccbcag", pattern = "cbca";

        int[] next = calNext(pattern.toCharArray());
        int i = 0, j = 0;
        while(i < str.length() && j < pattern.length()){
            if(j == -1 || str.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        System.out.println(i + " " + j);
    }

    public static int[] calNext(char[] chs){
        int[] next = new int[chs.length];
        next[0] = -1;
        int i = -1, j = 0;
        while(j < chs.length - 1){
            if(i == -1 || chs[i] == chs[j]){
                i++;
                j++;
                if(chs[i] != chs[j]){
                    next[j] = i;
                }else{
                    next[j] = next[i];
                }
            }else {
                i = next[i];
            }
        }
        System.out.println(Arrays.toString(next));
        return next;
    }
}

