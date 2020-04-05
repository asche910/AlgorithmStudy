# AlgorithmStudy

Some common algorithms.







## Array



### 递增子序列

#### [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

> 给定一数组，求递增子序列的最大长度

常规解法：dp[i]表示以nums[i]结尾的最大递增子序列长度，O(n^2)

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len < 2) return len;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        
        int res = 0;
        for(int i = 1; i < len; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

巧解：使用tails数组，tails[i]表示长度为`i + 1`的所有最长上升子序列的结尾最小值（因此tails数组并不一定是最长子序列的值）。tails数组大小即为所求。O(n log n)

参考[这里](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/)

```java
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;
        int[] tails = new int[len];
        tails[0] = nums[0];
        int size = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > tails[size - 1]) {
                tails[size++] = nums[i];
            } else {
                int low = 0, high = size - 1;
                while (low < high) {
                    int mid = (low + high) / 2;
                    // 注意先后条件，新值插入位置为：第一个大于等于新值的数所在位置
                    if (nums[i] > tails[mid]) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                tails[low] = nums[i];
            }
        }
        return size;
    }
```





#### [491. Increasing Subsequences](https://leetcode.com/problems/increasing-subsequences/)

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





#### [1395. Count Number of Teams](https://leetcode.com/problems/count-number-of-teams/)

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



## Stack



### 单调栈

#### [739. 每日温度](https://leetcode-cn.com/problems/daily-temperatures/)

> 给定温度数组，返回温度升高需要的天数

单调递减栈的应用

```java
class Solution {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];
        for(int i = 0; i < T.length; i++){
            while(!stack.isEmpty() && T[i] > T[stack.peek()]){
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }
        return res;
    }
}
```



## Tree



### 二叉树删除节点



#### [1325. Delete Leaves With a Given Value](https://leetcode.com/problems/delete-leaves-with-a-given-value/)

> 删除所有叶子节点值为target的节点，新的叶子节点若符合也删除

递归，返回孩子节点（不变或返回null节点）

```java
class Solution {
    public TreeNode removeLeafNodes(TreeNode node, int target) {
        if(node == null || node.val == target && node.left == null && node.right == null){
            return null;
        }
        node.left = removeLeafNodes(node.left, target);
        node.right = removeLeafNodes(node.right, target);
        if(node.left == null && node.right == null && node.val == target) return null;
        return node;   
    }
}
```



## Bit Manipulation

- ```-1 & n  --> n ```
	-1 & 1  --> 1
	-1 % 2  --> -1

- `n & (n - 1)`将最右边一个1变为0

- `n & -n`求得最右边一个1的部分
```
	6 & -6 = 2
	   0 0110
	 & 1 1010
	 = 0 0010
```

- `x | (1 << (i-1))`将第i位变为1




## Math

#### [738. Monotone Increasing Digits](https://leetcode.com/problems/monotone-increasing-digits/)

> 非负整数N，返回不大于N的数m，且m的各个位上数字呈递增状态

```java
    public int monotoneIncreasingDigits(int N) {
        int i = 1, res = N;
        while(res / 10 >= i) {
            int n = res / i % 100; // 每次取两个位
            i *= 10;
            if(n / 10 > n % 10) // 高位大于低位
                res = res / i * i - 1; // 低位变0，整体减1
        }
        return res;
    }
```





