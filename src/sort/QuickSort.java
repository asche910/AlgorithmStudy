package sort;

import tool.Tools;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        System.out.println(Arrays.toString(nums));
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] nums){
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int i = start;
        int j = end;
        while (i < j) {
            // 基准为start，则必须离其远的一方先执行while，以确保最后相遇节点小于基准
            while (nums[j] >= nums[start] && i < j) j--;
            while (nums[i] <= nums[start] && i < j) i++;
            if (i < j){
                Tools.swap(nums, i, j);
            }
        }
        Tools.swap(nums, start, i);

        quickSort(nums, start, i - 1);
        quickSort(nums, i + 1, end);
    }

}
