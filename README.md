# AlgorithmStudy

Some common algorithms.







## Array



### 递增子序列

[491. Increasing Subsequences](https://leetcode.com/problems/increasing-subsequences/)

> 求数组中的递增子序列

注意去重

```java
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> sub, int[] nums, int cur){
        if(sub.size() > 1) res.add(new ArrayList(sub));

        Set<Integer> set = new HashSet<>();
        for(int i = cur; i < nums.length; i++){
            if(set.contains(nums[i])) continue;
            // 注意条件
            if(sub.size() == 0 || nums[i] >= sub.get(sub.size() - 1)){
                set.add(nums[i]);
                
                sub.add(nums[i]);
                dfs(res, sub, nums, i + 1);
                sub.remove(sub.size() - 1);
            }
        }
    }
}
```





[1395. Count Number of Teams](https://leetcode.com/problems/count-number-of-teams/)

> 求递增或递减，且长度为三的子序列个数

当要求长度为3时，可以以中间数字为枢纽，判断左边比它小，右边比它大的个数。

```java
class Solution {
    public int numTeams(int[] rating) {
        int res = 0;
        for(int i = 1; i < rating.length - 1; i++){
            int[] less = new int[2], greater = new int[2];
            for(int j = 0; j < rating.length; j++){
                if(rating[i] > rating[j]){
                    less[j > i ? 1 : 0]++;
                }
                if(rating[i] < rating[j]){
                    greater[j > i ? 1 : 0]++;
                }
            }
            res += less[0] * greater[1] + greater[0] * less[1];
        }
        return res;
    }
}
```

常规思路，三重循环分别代表三个数，O(n^3)

```java
class Solution {
    public int numTeams(int[] rating) {
        int count = 0;
        boolean greater = false;
        for (int i = 0; i < rating.length - 2; i++) {
            for (int j = i + 1; j < rating.length - 1; j++) {
                if (rating[j] > rating[i])
                    greater = true;
                else
                    greater = false;
                for (int k = j + 1; k < rating.length; k++) {
                    if (greater && rating[k] > rating[j])
                        count += 1;
                    if (!greater && rating[k] < rating[j])
                        count += 1;
                }
            }
        }
        return count;
    }
}
```

