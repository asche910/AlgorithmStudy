package sort;

import java.util.Arrays;

/**
 * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，
 * 因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        System.out.println(Arrays.toString(nums));
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    /**
     * 传入nums数组是为了直接在原数组上操作，但仍需要额外空间
     */
    private static void merge(int[] nums, int low, int mid, int high) {
        int[] arr = new int[high - low + 1];
        int index = 0;
        int i = low;
        int j = mid + 1;
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                arr[index++] = nums[i++];
            } else {
                arr[index++] = nums[j++];
            }
        }
        while (i <= mid) {
            arr[index++] = nums[i++];
        }
        while (j <= high) {
            arr[index++] = nums[j++];
        }
        System.arraycopy(arr, 0, nums, 0, high - low + 1);
    }
}
