package sort;

import tool.Tools;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        System.out.println(Arrays.toString(nums));
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void bubbleSort(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = 0; j < nums.length - 1 - i; j++){
                if(nums[j] > nums[j + 1]){
                    Tools.swap(nums, j, j + 1);
                }
            }
        }
    }
}
