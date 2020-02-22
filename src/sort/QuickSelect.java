package sort;

import tool.Tools;

import java.util.Random;


/**
 * 快速选择算法
 * 寻找数组中第K大的数字，其中 1 <= K <= len
 */
public class QuickSelect {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};

        System.out.println(findKthLargest(nums, 2));
    }

    public static int findKthLargest(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        int target = nums.length - k;
        while(true){
            int index = partition(nums, low, high);
            if(index == target){
                return nums[index];
            }else if(index > target){
                high = index - 1;
            }else{
                low = index + 1;
            }
        }
    }

    private static int partition(int[] nums, int left, int right){
        int i = left, j = right;
        if(left < right){
            // 随机枢纽，防止特殊情况算法退化到O(n^2)
            Tools.swap(nums, left, new Random().nextInt(right - left + 1) + left);
        }
        while(i < j){
            while(i < j && nums[j] >= nums[left]) j--;
            while(i < j && nums[i] <= nums[left]) i++;
            if(i < j){
                Tools.swap(nums, i, j);
            }
        }
        Tools.swap(nums, left, j);
        return j;
    }
}
