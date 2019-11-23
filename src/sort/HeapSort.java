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
        for(int i = (len - 2) / 2; i >= 0; i--){
            heapify(nums, i, len - 1);
        }

        // 最大值依次放到末尾，然后heapify剩余的堆
        for(int i = len - 1; i > 0; i--){
            Tools.swap(nums, 0, i);
            heapify(nums, 0, i - 1);
        }
    }

    /**
     * heapify nums from i to m
     */
    private static void heapify(int[] nums, int i, int m){
        int j;
        while(i * 2 + 1 <= m){
            // 左孩子的索引
            j = i * 2 + 1;
            if(j < m){
                // 找到两个孩子中的最大者索引
                if(nums[j] < nums[j + 1]){
                    j++;
                }
            }
            // 孩子节点大于父节点则交换，否则结束操作
            if(nums[i] < nums[j]){
                Tools.swap(nums, i, j);
                i = j;
            }else {
                i = m;
            }
        }
    }
}
