import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test...");

        System.out.println(2 << 3);

        List<Integer> list = new ArrayList<>();


        int[] dp = new int[]{1,2,3,4};
//        new Test().test(dp);
        test2(dp);
        System.out.println(Arrays.toString(dp));
    }

    public void test(int[] array){
        array[0] = 111;
    }

    public static void test2(int[] arr){
        System.out.println(arr);
        arr[0] = 111111;
    }
}
