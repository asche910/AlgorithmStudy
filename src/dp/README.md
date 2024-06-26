# DP 动态规划



#### [312. 戳气球](https://leetcode-cn.com/problems/burst-balloons/)

> 给定一数组，依次选择一个数nums[i]，求所有nums[i - 1] * nums[i] * nums[i + 1]的累加最大值。
>
> ```
> 输入: [3,1,5,8]
> 输出: 167 
> 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
>      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
> ```

`dp[i][j]`表示区间(i,  j)中的最大值，不戳破边界i和j

```c++
class Solution {
public:
    int maxCoins(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> dp(n + 2, vector<int>(n + 2, 0));
        nums.insert(nums.begin(), 1);
        nums.push_back(1);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = max(
                            dp[i][j],
                            (dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]));
                }
            }
        }
        return dp[0][n + 1];
    }
};

```





## 区间问题

#### [516. Longest Palindromic Subsequence](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)

> 求给定字符串的最长回文子序列的长度

`dp[i][j]`表示从索引`i`到索引`j`之间字符串的最长回文子序列长度。倒着递推。

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i = len - 1; i >= 0; i--){
            dp[i][i] = 1;
            for(int j = i + 1; j < len; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
```



#### [1024. 视频拼接](https://leetcode-cn.com/problems/video-stitching/)



> 给定区间数组，求能够拼接成目标区间[0, T]的最小区间个数。



```c++
class Solution {
public:
    int videoStitching(vector<vector<int>>& clips, int T) {
        vector<int> dp(T + 1, 1e9);
        dp[0] = 0;
        for(int i = 1; i <= T; ++i){
            for(auto &it: clips){
                if(i >= it[0] && i <= it[1]){
                    dp[i] = min(dp[i], dp[it[0]] + 1);
                }
            }
        }
        return dp[T] == 1e9 ? -1 : dp[T];
    }
};
```





## 编辑距离



#### [72. Edit Distance](https://leetcode-cn.com/problems/edit-distance/)

> 给定两字符串，求字符串1经过多少次操作可以变成字符串2，操作包含：删除、修改、添加一个字符



```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 1; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int i = 1; i <= len2; i++){
            dp[0][i] = i;
        }
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
```



类似：

[1143. Longest Common Subsequence](https://leetcode-cn.com/problems/longest-common-subsequence/)

[1035. Uncrossed Lines](https://leetcode-cn.com/problems/uncrossed-lines/)

[583. Delete Operation for Two Strings](https://leetcode-cn.com/problems/delete-operation-for-two-strings/)







## 背包问题



### 0-1 背包

* 0-1背包，由于金币有限，为防止金币重复使用，循环金币时应该逆序

* 初始化`dp[0] = 1;` 



#### [416. Partition Equal Subset Sum](https://leetcode-cn.com/problems/partition-equal-subset-sum/)

> 给定正整数数组，判断能否分成两个和相等的子集



```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n: nums) sum += n;
        if(sum % 2 != 0) return false;
        sum /= 2;
        
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++){
            for(int j = sum; j - nums[i] >= 0; j--){
                dp[j] |= dp[j - nums[i]];
            }
            if(dp[sum] == 1) return true;
        }
/* c++
        for (auto& i : nums) {
        	// 这里逆序
            for (int j = sum - i; j >= 0; j--) {
                dp[j + i] |= dp[j];
            }
            if (dp[sum]) return true;
        }
*/
        return dp[sum] == 1;
    }
}
```

#### [494. Target Sum](https://leetcode-cn.com/problems/target-sum/)

> You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
> Find out how many ways to assign symbols to make sum of integers equal to target S.

A - B = S
A + B = Sum  -->  A = (S + sum) / 2
即给定非负数组，返回子集和为目标值的个数

```java
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum < S || (sum + S) % 2 == 1) return 0;
        
        int w = (sum + S) / 2;
        int[] dp = new int[w + 1];
        // 初始化，很重要
        dp[0] = 1;
        for (int num : nums) {
            // 这里是倒着，正序可能导致同一个硬币使用两次
            for (int j = w; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[w];
    }
}
```

递推公式中，可理解为**新的硬币**带来的增量，因此不会重复。



### 完全背包

* 金币无限，一般情况金币放在内层循环



#### [322. Coin Change](https://leetcode-cn.com/problems/coin-change/)

> 使用最少数量的硬币组成目标值，硬币可重复使用，返回需要的**最少硬币数量**

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for(int i = 1; i <= amount; i++){
            int val = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; j++){
                if(i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE){
                    val = Math.min(val, dp[i - coins[j]] + 1);
                }
            }
            dp[i] = val;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
```



```c++
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        vector<int> dp(amount + 1);
        for (int i = 1; i <= amount; i++) {
            int val = INT_MAX;
            for (auto& j : coins) {
                if (i - j >= 0 && dp[i - j] != INT_MAX)
                    val = min(val, dp[i - j] + 1);
            }
            dp[i] = val;
        }
        return dp[amount] == INT_MAX ? -1 : dp[amount];
    }
};
```





####  [518. Coin Change 2](https://leetcode-cn.com/problems/coin-change-2/)

> 使用硬币组成目标值，硬币可重复使用，返回**组合方案数量**

不限硬币数量，因此amount 可以由小到大的迭代

```c++
class Solution {
public:
    int change(int amount, vector<int>& coins) {
        vector<int> dp(amount + 1);
        dp[0] = 1;
        for (auto& i : coins) {
            for (int j = i; j <= amount; j++) {
                dp[j] += dp[j - i];
            }
        }
        return dp[amount];
    }
};

```



#### [377. 组合总和 Ⅳ](https://leetcode-cn.com/problems/combination-sum-iv/)

> 给定不重复的正整数数组，找出和为目标正整数target的组合个数。
>
> 例如[1,  2]和[2, 1]算2个。

```c++
class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        vector<unsigned long long> dp(target + 1); // 防止溢出
        dp[0] = 1;
        for(int i = 1; i <= target; ++i){
            for(int num: nums){
                if(i - num >= 0){
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
};
```



## 股票问题





#### [188. 买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)

> 给定一个数组，它的第 *i* 个元素是一支给定的股票在第 *i* 天的价格。
>
> 设计一个算法来计算你所能获取的最大利润。你最多可以完成 **k** 笔交易。

`dp[i][j][0]`表示第i天，第j笔交易，手上**没有**股票的最大利润；

```c++
class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        if(prices.empty()) return 0;
        if(k >= prices.size() / 2){
            int sum = 0;
            for(int i = 1; i < prices.size(); ++i){
                if(prices[i] > prices[i - 1]){
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }
        vector<vector<vector<int>>> dp(prices.size(), vector<vector<int>>(k + 1, vector<int>(2, 0)));
        for(int i = 0; i < prices.size(); ++i){
            for(int j = k; j >= 1; --j){
                if(!i){
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[prices.size() - 1][k][0];
    }
};

```

巧解

```c++
class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        vector<int> buy(k + 1, INT_MIN), sell(k + 1, 0);
        for(int n: prices){
            for(int i = 1; i <= k; i++){
                buy[i] = max(buy[i], sell[i - 1] - n);
                sell[i] = max(sell[i], buy[i] + n);
            }
        }
        return sell[k];
    }
};
```





#### [309. 买卖股票的最佳时机含冷冻期](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

> 无限次交易，不过卖出后的一天，不能接着买入



在正常无限交易的基础上，有一天冷冻期，那就加一个变量，保存sell的上一个值。

```c++
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int buy = INT_MIN, pre_sell = 0, sell = 0;
        for(int n: prices){
            buy = max(buy, pre_sell - n);
            pre_sell = sell;
            sell = max(sell, buy + n);
        }
        return sell;
    }
};
```





## 状态压缩



#### [526. Beautiful Arrangement](https://leetcode-cn.com/problems/beautiful-arrangement/)

> 求优美排列的个数。1-N中，每个位置能够被上面的值整除或整除上面的值

```java
class Solution {
    public int countArrangement(int N) {
        int[] dp = new int[1 << N];
        for(int i = 0; i < N; i++) dp[1 << i] = 1;
        int res = 0;
        for(int i = 1; i < 1 << N; i++){
            if(dp[i] == 0) continue;
            int pos = 0;
            for(int j = 0; j < N; j++){
                if((i & (1 << j)) != 0) pos++;
            }
            for(int j = 0; j < N; j++){
                if((i & (1 << j)) == 0 && ((pos + 1) % (j + 1) == 0 || (j + 1) % (pos + 1) == 0)){
                    dp[i | 1 << j] += dp[i];
                }
            }
        }
        return dp[(1 << N) - 1];
    }
}
```



More：

464,935,1349





