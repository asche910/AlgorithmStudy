package sort;

import tool.Tools;

import java.util.Arrays;

/**
 * 大根堆：每个节点的值都大于或等于其左右孩子节点的值的完全二叉树
 *
 * 从0开始计数，对节点n有：
 * 孩子节点为：2 * n + 1 和 2 * n + 2
 * 父节点为：(n - 1) / 2
 *
 * https://segmentfault.com/a/1190000017301113
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] nums = new int[]{34, 2, 43, 12, 50, 0, 32, 13, 56, 22, 53, 3, 1, 9, 23, 50, -5, 20};
        System.out.println(Arrays.toString(nums));
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void heapSort(int[] nums){
        int len = nums.length;
        // 构造堆，对非叶子节点heapify（不能从低到高遍历）
        //从最后一个节点len - 1的父节点（len - 1 - 1）/ 2开始
        for(int i = len / 2  - 1; i >= 0; i--){
            heapify(nums, i, len );
        }

        // 最大值依次放到末尾，然后heapify剩余的堆
        for(int i = len - 1; i > 0; i--){
            Tools.swap(nums, 0, i);
            heapify(nums, 0, i);
        }
    }

    /**
     * max Heap heapify
     *
     *  假设堆顶换进来一个很小的元素，需要把下面比它大的节点上移，然后把该元素放到对应的空位上去
     */
    private static void heapify(int[] nums, int p, int len){
        int temp = nums[p];

        for(int i = 2 * p + 1; i < len; i = 2 * i + 1){
            if(i + 1 < len && nums[i] < nums[i + 1]){
                i++;
            }
            if(temp >= nums[i]){
                break;
            }else{
                nums[p] = nums[i];
                p = i;
            }
        }
        nums[p] = temp;
    }
}


/**
 *
 * C++ version
 *
 * class Solution {
 * public:
 *     vector<int> getLeastNumbers(vector<int>& arr, int k) {
 *         vector<int> res;
 *         if(k == 0) return res;
 *         int len = arr.size();
 *         for(int i = len / 2 - 1; i >= 0; i--){
 *             heapify(arr, i, len);
 *         }
 *         for(int i = len - 1; i > 0; i--){
 *             swap(arr[i], arr[0]);
 *             heapify(arr, 0, i);
 *         }
 *         res.assign(arr.begin(), arr.begin() + k);
 *         return res;
 *     }
 *
 *     void heapify(vector<int> &arr, int p, int len){
 *         int temp = arr[p];
 *         for(int i = 2 * p + 1; i < len; i = 2 * i + 1){
 *             if(i + 1 < len && arr[i] < arr[i + 1]) i++;
 *             if(temp >= arr[i]){
 *                 break;
 *             }else{
 *                 arr[p] = arr[i];
 *                 p = i;
 *             }
 *         }
 *         arr[p] = temp;
 *     }
 * };
 *
 *
 *
 */