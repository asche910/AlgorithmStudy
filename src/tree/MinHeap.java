package tree;

import java.util.Arrays;


/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 * @see <a href="https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/">Here</a>
 */
public class MinHeap {

    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0) return new int[0];
        int[] res = Arrays.copyOfRange(arr, 0, k);
        for(int i = k / 2 - 1; i >= 0; i--){
            heapify(res, i, k - 1);
        }
        for(int i = k; i < arr.length; i++){
            if(arr[i] < res[0]){
                res[0] = arr[i];
                heapify(res, 0, k - 1);
            }
        }
        return res;
    }

    private void heapify(int[] nums, int m, int n){
        while(m * 2 + 1 <= n){
            int child = m * 2 + 1;
            if(child < n && nums[child] < nums[child + 1]) child++;
            if(nums[m] < nums[child]){
                swap(nums, m, child);
                m = child;
            }else m = n;
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
