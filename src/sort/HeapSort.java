package sort;

import tool.Tools;

import java.util.Arrays;

/**
 * 大根堆，首数字序号从0开始
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        System.out.println(Arrays.toString(nums));
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void heapSort(int[] nums){
        int len = nums.length;
        // 构造堆，对非叶子节点heapify（不能从低到高遍历）
        // 从第一个非叶子节点开始
        for(int i = len / 2  - 1; i >= 0; i--){
            heapify(nums, i, len - 1);
        }

        // 最大值依次放到末尾，然后heapify剩余的堆
        for(int i = len - 1; i > 0; i--){
            Tools.swap(nums, 0, i);
            heapify(nums, 0, i - 1);
        }
    }

    /**
     * heapify nums from start to end
     */
    private static void heapify(int[] nums, int start, int end){
        int root = start;
        while(root * 2 + 1 <= end){
            // 左孩子的索引
            int child = root * 2 + 1;
            // 找到两个孩子中的最大者索引
            if(child + 1 <= end && nums[child] < nums[child + 1]) child++;
            // 孩子节点大于父节点则交换，否则结束操作
            if(nums[root] < nums[child]){
                Tools.swap(nums, root, child);
            }else{
                root = end;
            }
        }
    }
}
