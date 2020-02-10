import tool.Tools;

import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("Test...");

        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        System.out.println(Arrays.toString(nums));
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int[] nums) {
        for (int gap = nums.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < nums.length; i += gap) {
                int temp = nums[i];
                int j = i - gap;
                while (j >= 0 && temp < nums[j]) {
                    nums[j + gap] = nums[j];
                    j -= gap;
                }
                nums[j + gap] = temp;
            }
        }
    }
}
