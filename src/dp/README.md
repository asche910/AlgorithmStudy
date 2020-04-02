
## DP 动态规划



### 给定一数组，求最大的3个区间和，返回对应区间开始位置
[689.Maximum Sum of 3 Non-Overlapping Subarrays](https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/)

```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length, maxSum = 0;
        int[] sum = new int[len + 1], posLeft = new int[len], posRight = new int[len], res = new int[3];
        for(int i = 0; i < nums.length; i++) sum[i + 1] = sum[i] + nums[i];
        
        for(int i = k, cur = sum[k] - sum[0]; i < len - 2 * k; i++){
            if(sum[i + 1] - sum[i + 1 - k] > cur){
                posLeft[i] = i + 1 - k;
                cur = sum[i + 1] - sum[i + 1 - k];
            }else posLeft[i] = posLeft[i - 1];
        }
        
        posRight[len - k] = len- k;
        for(int i = len - k - 1, cur = sum[len] - sum[len - k]; i >= 2 * k; i--){
            if(sum[i + k] - sum[i] >= cur){
                posRight[i] = i;
                cur = sum[i + k] - sum[i];
            }else posRight[i] = posRight[i + 1];
        }
        
        for(int i = k; i <= len - 2 * k; i++){
            int l = posLeft[i - 1], r = posRight[i + k];
            int cur = (sum[i + k] - sum[i]) + (sum[l + k] - sum[l]) + (sum[r + k] - sum[r]);
            if(cur > maxSum){
                maxSum = cur;
                res[0] = l;
                res[1] = i;
                res[2] = r;
            }
        }
        return res;
    }
}

```