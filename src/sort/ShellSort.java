package sort;

import java.util.Arrays;

/**
 * 插入排序的改进版，也称为“缩小增量排序”
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        System.out.println(Arrays.toString(nums));
        shellSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    protected static void shellSort(int[] nums){
        for (int gap = nums.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < nums.length; i++) {
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
