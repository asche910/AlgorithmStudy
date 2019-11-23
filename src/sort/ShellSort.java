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

    public static void shellSort(int[] nums){
        int gap = nums.length / 2;
        while (gap > 0){
            for(int i = gap; i < nums.length; i += gap){
                int j = i;
                int target = nums[i];
                while (j > 0 && target < nums[j - gap]){
                    nums[j] = nums[j - gap];
                    j -= gap;
                }
                nums[j] = target;
            }
            gap /= 2;
        }
    }
}
