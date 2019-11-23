package sort;

import tool.Tools;

import java.util.Arrays;

/**
 * 从未排序列表中选择一个最小的元素放到已排序列表中的末尾
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        System.out.println(Arrays.toString(nums));
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void selectionSort(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            int index = i + 1;
            for(int j = i + 1; j < nums.length; j++){
                if (nums[j] < nums[index]){
                    index = j;
                }
            }
            Tools.swap(nums, i, index);
        }
    }
}
