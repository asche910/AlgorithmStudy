import java.util.*;

public class Main {
    public static void main(String[] args) {
        dfs(new int[]{1, 2, 3}, new ArrayDeque<>(), 0, "");
    }

    private static void dfs(int[] in, ArrayDeque<Integer> stack, int cur, String out){
        if(cur == in.length){
            while (!stack.isEmpty()) out = out + stack.pop();
            System.out.println(out);
            return;
        }

        ArrayDeque<Integer> copy1 = new ArrayDeque<>(stack);
        ArrayDeque<Integer> copy2 = new ArrayDeque<>(stack);

        copy1.push(in[cur]);
        dfs(in, copy1, cur + 1, out);

        while (!copy2.isEmpty()){
            out += copy2.pop();
        }
        dfs(in, copy2, cur + 1, out);
    }
}