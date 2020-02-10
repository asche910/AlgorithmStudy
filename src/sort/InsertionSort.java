package sort;

import java.util.Arrays;

/**
 * 选择未排序列表中的第一个数字插入到已排序列表中的指定位置
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        System.out.println(Arrays.toString(nums));
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void insertionSort(int[] nums){
        for(int i = 1; i < nums.length; i++){
            int target = nums[i];
            int j = i - 1;
            while(j >= 0 && target < nums[j]){
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = target;
        }
    }
}
