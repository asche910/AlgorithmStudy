# DP 动态规划



## 区间问题

### [516. Longest Palindromic Subsequence](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)

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





## 编辑距离



### [72. Edit Distance](https://leetcode-cn.com/problems/edit-distance/)

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



类似有：

[1143. Longest Common Subsequence](https://leetcode-cn.com/problems/longest-common-subsequence/)

[1035. Uncrossed Lines](https://leetcode-cn.com/problems/uncrossed-lines/)

[583. Delete Operation for Two Strings](https://leetcode-cn.com/problems/delete-operation-for-two-strings/)







## 背包问题



### 0-1 背包

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
        return dp[sum] == 1;
    }
}
```

#### [494. Target Sum](https://leetcode-cn.com/problems/target-sum/)

> You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
>
> Find out how many ways to assign symbols to make sum of integers equal to target S.
>
> 给定非负数组，返回子集和为S的个数



```java
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum < S || (sum + S) % 2 == 1) return 0;
        
        int w = (sum + S) / 2;
        int[] dp = new int[w + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = w; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[w];
    }
}
```



