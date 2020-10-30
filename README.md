# AlgorithmStudy

Some common algorithms.



## Array



### Basic

#### [689.Maximum Sum of 3 Non-Overlapping Subarrays](https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/)

> 给定一数组，求最大的3个区间和，返回对应区间开始位置

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

### 



### 递增子序列

#### [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

> 给定一数组，求递增子序列的最大长度

常规解法：dp[i]表示以nums[i]结尾的最大递增子序列长度，O(n^2)

```java
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n, 1);
        int res = 0;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < i; ++j){
                if(nums[j] < nums[i]){
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            } 
            res = max(res, dp[i]);
        }
        return res;
    }
};
```

巧解：使用tails数组，tails[i]表示长度为`i + 1`的所有最长上升子序列的结尾最小值（因此tails数组并不一定是最长子序列的值）。tails数组大小即为所求。O(n log n)

参考[这里](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/)



```c++
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        if(nums.empty()) return 0;
        vector<int> tails{nums[0]};
        for(int i = 1; i < nums.size(); ++i){
            if(nums[i] > tails.back()){
                tails.push_back(nums[i]);
            }else{
                int low = 0, high = tails.size() - 1;
                while(low < high){
                    int mid = low + (high - low) / 2;
                    // 注意先后条件，新值插入位置为：第一个大于等于新值的数所在位置
                    if(tails[mid] < nums[i]){
                        low = mid + 1;
                    }else{
                        high = mid;
                    }
                }
                tails[low] = nums[i];
            }
        }
        return tails.size();
    }
};
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



### 子序列问题



#### [516. Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/)

最长回文串的长度





### 区间问题

#### [56. 合并区间](https://leetcode-cn.com/problems/merge-intervals/)

> ```
> 给出一个区间的集合，请合并所有重叠的区间。
> 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
> 输出: [[1,6],[8,10],[15,18]]
> ```

头部排序

```c++
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        if(intervals.empty()) return intervals;
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> res;
        vector<int> temp = intervals[0];
        for(auto& i: intervals){
            if(i[0] <= temp[1]){
                temp[1] = max(temp[1], i[1]);
            }else{
                res.push_back(temp);
                temp = i;
            }
        }
        res.push_back(temp);
        return res;
    }
};
```



#### [452. 用最少数量的箭引爆气球](https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/)

尾部排序

```c++
class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        if(points.empty()) return 0;
        sort(points.begin(), points.end(), [](auto& a, auto& b){
            return a[1] < b[1] || a[1] == b[1] && a[0] < b[0];
        });
        int cnt = 1;
        vector<int> temp = points[0];
        for(auto &i: points){
            if(i[0] > temp[1]){
                cnt++;
                temp = i;
            }
        }
        return cnt;
    }
};
```







#### [646. 最长数对链](https://leetcode-cn.com/problems/maximum-length-of-pair-chain/)

> 给定一系列区间集合，找到最大的数对链长度。区间[a, b]，[c, d]，c > d则符合。

尾部优先排序

```c++
class Solution {
public:
    int findLongestChain(vector<vector<int>>& pairs) {
        sort(pairs.begin(), pairs.end(), [](auto& a, auto& b){
            return a[1] < b[1] || a[1] == b[1] && a[0] < b[0];
        });
        int cnt = 0;
        vector<int> tail(pairs[0]);
        for(int i = 0; i < pairs.size(); ++i){
            if(i == 0 || pairs[i][0] > tail[1]){
                tail = pairs[i];
                cnt++;
            }
        }
        return cnt;
    }
};
```







### 旋转数组

#### 排序的旋转数组求目标值

##### [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

> 旋转排序数组（无相同值）中，搜寻目标值。

旋转数组中先找到递增的那部分，复杂情况用else来应对。



```c++
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int low = 0, high = nums.size() - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] >= nums[low]){
                if(nums[low] <= target && target < nums[mid]){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }else{
                if(nums[mid] < target && target <= nums[high]){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
};
```



##### [81. 搜索旋转排序数组 II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)

> 旋转排序数组（含有相同值）中，搜寻目标值。

与上一题不同的是，多了一种特殊情况需要处理，即[3，2，3，3，3]这种。nums[low] <= nums[mid] 中包括了递增和上述特殊情况。

```c++
class Solution {
public:
    bool search(vector<int>& nums, int target) {
        int low = 0, high = nums.size() - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[mid] == target) return true;

            if(nums[low] == nums[mid] && nums[mid] == nums[high]){
                low++;
                high--;
            }else if(nums[low] <= nums[mid]){
                if(nums[low] <= target && target < nums[mid]) high = mid - 1;
                else low = mid + 1;
            }else if(nums[mid] <= nums[high]){
                if(nums[mid] < target && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            }
        }
        return false;
    }
};
```







#### 排序的旋转数组求最小值

##### [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)

```c++
class Solution {
public:
    int findMin(vector<int>& nums) {
        int low = 0, high = nums.size() - 1;
        while(low < high){
            int mid = low + (high - low) / 2;
            if(nums[mid] > nums[high]){
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        return nums[low];
    }
};
```



##### [154. Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)
> 其中数组包含重复元素

对于`[2,2,1]`用例，常规判断无效。

必须判断两种确切的特殊情况。

```c++
class Solution {
public:
    int findMin(vector<int>& nums) {
        int low = 0, high = nums.size() - 1;
        while(low < high){
            int mid = low + (high - low) / 2;
            if(nums[mid] > nums[high]){
                low = mid + 1;
            }else if(nums[mid] < nums[low]){
                high = mid;
            }else high--;
        }
        return nums[low];
    }
};
```






#### 山脉数组求目标值







## Binary Indexed Tree

也称为**树状数组**

它可以以 *O*(log *n*) 的时间得到任意前缀和

并同时支持在 *O*(log *n*) 时间内支持动态单点值的修改



https://www.cnblogs.com/xenny/p/9739600.html



```c++
#include <bits/stdc++.h>
using namespace std;

// Binary Indexed Tree
class BITree {
private:
    // index start from 1
    vector<int> c = { 0 };
    int n;
public:
    BITree(int size) {
        n = size;
        c.resize(size + 1);
    }

    int lowbit(int x) {
        return x & (-x);
    }

    // change k
    void update(int i, int k) {
        while (i <= n) {
            c[i] += k;
            i += lowbit(i);
        }
    }

    int getSum(int i) {
        int res = 0;
        while (i > 0) {
            res += c[i];
            i -= lowbit(i);
        }
        return res;
    }
};

int main() {
    vector<int> nums = { 3, 5, 8, 1, 2, 7 };
    BITree tree = BITree(nums.size() + 1);
    for (int i = 0; i < nums.size(); ++i) {
        tree.update(i + 1, nums[i]);
    }
    // get sum from nums[0] to nums[2]
    cout << tree.getSum(3) << endl; // 16
    // change nums[2] to 8 - 6 = 2
    tree.update(3, -6);
    cout << tree.getSum(3) << endl; // 10
    return 0;
}

```







## Bit Manipulation

- ```-1 & n  --> n ```
  -1 & 1  --> 1
  -1 % 2  --> -1
- `n & (n - 1)`将最右边一个1变为0
- `n & -n`求得最右边一个1的部分

```c++
	6 & -6 = 2
	   0 0110
	 & 1 1010
	 = 0 0010
```

- `x | (1 << (i-1))`将第i位变为1
-  `~`为二进制取反运算符， `x ^ 0 = x` 、`x ^ 1 = -（~x）`



### 任意进制转换

先转换成十进制，然后转换成目标进制

```c++
// s 原数字，d1为s表示的进制，d2为转换的目标进制
string convert(string s, int d1, int d2) {
    long long num = 0;
    int temp;
    for (int i = 0; i < s.size(); ++i) {
        if (isdigit(s[i])) {
            temp = s[i] - '0';
        } else {
            temp = s[i] - 'A' + 10;
        }
        num = num * d1 + temp;
    }
    string res;
    while (num) {
        temp = num % d2;
        if (temp <= 9) {
            res += (temp + '0');
        } else {
            res += (temp + 'A' - 10);
        }
        num /= d2;
    }
    reverse(res.begin(), res.end());
    return res;
}
// cout << convert("3A", 16, 10) << endl;  --> 58

```









## Depth-first Search





#### [526. Beautiful Arrangement](https://leetcode-cn.com/problems/beautiful-arrangement/)

> 求优美排列的个数。1-N中，每个位置能够被上面的值整除或整除上面的值

```c++
class Solution {
public:
    int countArrangement(int N) {
        vector<bool> flags(N + 1);
        return dfs(flags, 1);
    }

    int dfs(vector<bool> &flags, int cur){
        if(cur == flags.size()) return 1;
        int res = 0;
        for(int i = 1; i < flags.size(); ++i){
            if(!flags[i] && (i % cur == 0 || cur % i == 0)){
                flags[i] = true;
                // 此处为cur + 1
                res += dfs(flags, cur + 1);
                flags[i] = false;
            }
        }
        return res;
    }
};
```

### 全排列

#### [47. 全排列 II](https://leetcode-cn.com/problems/permutations-ii/)

> 给定可能重复的数字序列，求所有不重复的全排列

经典回溯算法

```c++
class Solution {
    vector<vector<int>> res;
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        dfs(nums, 0);
        return res;
    }

    void dfs(vector<int>& nums, int cur) {
        if (cur == nums.size()) {
            res.push_back(nums);
            return;
        }
        for (int i = cur; i < nums.size(); i++) {
            if (!check(nums, cur, i)) continue;
            swap(nums[cur], nums[i]);
            dfs(nums, cur + 1);
            swap(nums[cur], nums[i]);
        }
    }

    bool check(vector<int>& nums, int start, int end) {
        for (int i = start; i < end; i++) {
            if (nums[i] == nums[end]) return false;
        }
        return true;
    }
};
```





### 指定和



[39. Combination Sum](https://leetcode.com/problems/combination-sum/)

> 给定一集合，求所有累加和为指定值的集合

dfs过程中，使目标和逐渐趋于0

```c++
class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
         sort(candidates.begin(), candidates.end());
        return dfs(candidates, target, 0);
    }
    
    vector<vector<int>> dfs(vector<int>& nums, int target, int idx){
        vector<vector<int>> res;
        for(int i = idx; i < nums.size(); ++i){
            if(nums[i] < target){
                auto subs = dfs(nums, target - nums[i], i);
                for(auto sub: subs){
                    sub.push_back(nums[i]);
                    res.push_back(sub);
                }
            }else if(nums[i] == target){
                res.push_back({nums[i]});
                break;
            }else{
                break;
            }
        }
        return res;
    }
};

```









## Dynamic Programming

[Here](./src/dp/README.md)





## Graph

使用邻接表数组



#### [207. 课程表](https://leetcode-cn.com/problems/course-schedule/)

> [i, j]表示课程i依赖于课程j，判断该课程表数组能否学习完成

实质为判断图中是否存在环。

未访问用0，访问中用1，以访问用2。

```c++
class Solution {
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> graph(numCourses);
        for(auto& i: prerequisites){
            graph[i[1]].push_back(i[0]);
        }
        vector<int> visited(numCourses);
        for(int i = 0; i < numCourses; ++i){
            if(!dfs(graph, visited, i)) return false;
        }
        return true;
    }

    bool dfs(vector<vector<int>>& graph, vector<int>& visited, int cur){
        visited[cur] = 1;
        for(int i: graph[cur]){
            if(visited[i] == 0){
                if(!dfs(graph, visited, i)) return false;
            }else if(visited[i] == 1){
                return false;
            }
        }
        visited[cur] = 2;
        return true;
    }
};
```





## Greedy



### 求字典排序最小的结果

使用**栈**，新进来的元素，先和栈顶比较，符合某种条件则弹出栈顶元素，再入栈



#### [316. 去除重复字母](https://leetcode-cn.com/problems/remove-duplicate-letters/)

> 删除重复字母，使其只出现一次，并且最终结果字典序最小

```c++
class Solution {
public:
    string removeDuplicateLetters(string s) {
        string stk;
        for (int i = 0; i < s.size(); ++i) {
            if(stk.find(s[i]) != string::npos) continue;
            while (!stk.empty() && stk.back() > s[i] &&
                s.find(stk.back(), i) != string::npos) {
                stk.pop_back();
            }
            stk.push_back(s[i]);
        }
        return stk;
    }
};
```

插入一个字符时，想办法删除前面比自己大并且后面再次出现的字符。





#### [402. 移掉K位数字](https://leetcode-cn.com/problems/remove-k-digits/)

> 给定一个以字符串表示的非负整数 *num*，移除这个数中的 *k* 位数字，使得剩下的数字最小。

```c++
class Solution {
public:
    string removeKdigits(string num, int k) {
        if (k >= num.size()) return "0";
        string stk;
        for (int i = 0; i < num.size(); i++) {
            while (!stk.empty() && stk.back() > num[i] && k) {
                stk.pop_back();
                k--;
            }
            stk.push_back(num[i]);
        }
        while(k--) {
            stk.pop_back();
        }
        while(!stk.empty() && stk[0] == '0') {
            stk.erase(stk.begin());
        }
        return stk.empty() ? "0" : stk;
    }
};

```







### 相邻元素的约束

相邻元素拥有制约关系，则可采取先**左右扫描**，再贪心结合

#### [135. Candy](https://leetcode.com/problems/candy/)

左右扫描，取两次的最大值

```c++
class Solution {
public:
    int candy(vector<int>& ratings) {
        int len = ratings.size();
        vector<int> left(len), right(len);
        for(int i = 1; i < len; ++i){
            if(ratings[i] > ratings[i - 1]){
                left[i] = left[i - 1] + 1;
            }
        }
        for(int i = len - 2; i >= 0; --i){
            if(ratings[i] > ratings[i + 1]){
                right[i] = right[i + 1] + 1;
            }
        }
        int res = len;
        for(int i = 0; i < len; ++i){
            res += max(left[i], right[i]);
        }
        return res;
    }
};
```









## Hash Table





### 连续子数组

* 前缀和



#### [560. 和为K的子数组](https://leetcode-cn.com/problems/subarray-sum-equals-k/)

> 给定一个整数数组和一个整数 **k**，你需要找到该数组中和为 **k** 的连续的子数组的个数。

使用**前缀和**求解

```c++
class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> sumMap;
        sumMap[0] = 1;
        int sum = 0, res = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums[i];
            res += sumMap[sum - k];
            sumMap[sum]++;
        }
        return res;
    }
};
```

类似有

[1248. 统计「优美子数组」](https://leetcode-cn.com/problems/count-number-of-nice-subarrays/)





## Heap



### 区间重叠



#### 253. Meeting Rooms II

> Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
>
> For example,
> Given [[0, 30],[5, 10],[15, 20]],
> return 2.

即求最大重叠区间的个数：对**起始时间**进行排序，使用**最小堆**来记录当前会议的**结束时间**，当新会议的**开始时间**小于最小堆中最早的结束时间，说明该区间重叠。

```c++
class Solution {
public:
    int minMeetingRooms(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        priority_queue<int, vector<int>, greater<int>> q;
        int cnt = 0;
        for (auto& i : intervals) {
            q.push(i[1]);
            if (i[0] < q.top()) ++cnt;
            else q.pop();
        }
        return cnt;
    }
};

```



### TOP K问题

#### [373. 查找和最小的K对数字](https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/)

> 从两个数组中各取一个数组成一对，找出和最小的k对数字

```c++
class Solution {
public:
    vector<vector<int>> kSmallestPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        priority_queue<vector<int>, vector<vector<int>>, cmp> pq;
        for(int a: nums1){
            for(int b: nums2){
                vector<int> temp {a, b};
                pq.push(temp);
            }
        }
        vector<vector<int>> res;
        while(!pq.empty() && k){
            res.push_back(pq.top());
            pq.pop();
            k--;
        }
        return res;
    }

    struct cmp{
        bool operator()(vector<int> &a, vector<int> &b){
            return a[0] + a[1] > b[0] + b[1];
        }
    };
};
```







## Math



* 判断n是否是素数，判断`[2,sqrt(n)]`内的数是否能整除n

* 1 = 1
  4 = 1 + 3
  9 = 1 + 3 + 5
  16 = 1 + 3 + 5 + 7
  25 = 1 + 3 + 5 + 7 + 9
  36 = 1 + 3 + 5 + 7 + 9 + 11
* 

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





### 矩形相交





#### [223. 矩形面积](https://leetcode-cn.com/problems/rectangle-area/)

> 给出两个矩形坐标，求重叠后所有的面积

关键在于交点坐标，高的最小值，低的最大值

```c++
    int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B), area2 = (G - E) * (H - F);
        if (C <= E || F >= D || B >= H || A >= G) {
            return area1 + area2; 
        }
        int topX = min(G, C), topY = min(H, D);
        int bottomX = max(E, A), bottomY = max(B, F);
        return area1 - (topX - bottomX) * (topY - bottomY) + area2;
    }
```



## Segment Tree



**线段树**是一种二叉树形数据结构，用以存储区间或线段，并且允许快速查询结构内包含某一点的所有区间。



```c++
class SegmentTreeNode {
public:
    int start, end;
    int count; // 权值，这里示例为区间范围内的数字的个数

    SegmentTreeNode* left, * right;

    SegmentTreeNode(int s, int e) : start(s), end(e) {
        count = 0;
    }
};

SegmentTreeNode* build(int start, int end) {
    if (start > end)
        return NULL;
    SegmentTreeNode* root = new SegmentTreeNode(start, end);

    if (start == end) {
        root->count = 0;
    } else {
        int mid = start + (end - start) / 2;
        root->left = build(start, mid);
        root->right = build(mid + 1, end);
    }
    return root;
}

int getCount(SegmentTreeNode* root, int start, int end) {
    if (root == NULL || start > end)
        return 0;
    if (start == root->start && end == root->end) {
        return root->count;
    }
    int mid = root->start + (root->end - root->start) / 2;
    int leftcount = 0, rightcount = 0;

    if (start <= mid) {
        if (mid < end)
            leftcount = getCount(root->left, start, mid);
        else
            leftcount = getCount(root->left, start, end);
    }

    if (mid < end) {
        if (start <= mid)
            rightcount = getCount(root->right, mid + 1, end);
        else
            rightcount = getCount(root->right, start, end);
    }
    return (leftcount + rightcount);
}

void insert(SegmentTreeNode* root, int index, int val) {
    if (root->start == index && root->end == index) {
        root->count += val;
        return;
    }

    int mid = root->start + (root->end - root->start) / 2;
    if (index >= root->start && index <= mid) {
        insert(root->left, index, val);
    }
    if (index > mid && index <= root->end) {
        insert(root->right, index, val);
    }

    root->count = root->left->count + root->right->count;
}

int main() {
    SegmentTreeNode* tree = build(0, 5);
    insert(tree, 4, 1);
    insert(tree, 3, 1);
    cout << getCount(tree, 0, 3) << endl;
    return 0;
}
```





#### [307. 区域和检索 - 数组可修改](https://leetcode-cn.com/problems/range-sum-query-mutable/)

```c++
class NumArray {
    int n;
    vector<int> tree;
public:
    NumArray(vector<int>& nums) : n(nums.size()), tree(n << 1) {
        for (int i = n, j = 0; j < n; ++i, ++j) tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i) tree[i] = tree[i << 1] + tree[(i << 1) + 1];
    }

    void update(int i, int val) {
        i += n;
        val -= tree[i];
        while (i) {
            tree[i] += val;
            i >>= 1;
        }
    }

    int sumRange(int i, int j) {
        int res = 0;
        for (i += n, j += n; i <= j; i >>= 1, j >>= 1) {
            if (i & 1) res += tree[i++];
            if(!(j & 1)) res += tree[j--];
        }
        return res;
    }
};

```





## Stack



* n个不同的数进栈，出栈顺序种数有：` C(2n, n) / (n + 1)`

 

### 模拟操作

#### 检查出栈顺序合法性

```java
    private static boolean check(int[] in, int[] out){
        int i = 0, j = 0;
        Stack<Integer> stack = new Stack<>();
        for (; i < in.length; i++) {
            stack.push(in[i]);
            while (!stack.isEmpty() && stack.peek() == out[j]){
                stack.pop();
                j++;
            }
        }
        return i == j;
    }
```





### 单调栈



* 单调栈中，形成单调递增/减子序列



#### [739. 每日温度](https://leetcode-cn.com/problems/daily-temperatures/)

> 给定温度数组，返回温度升高需要的天数

单调递减栈的应用，寻找下一个更大的数

```c++
class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& T) {
        stack<int> stk;
        vector<int> res(T.size());
        for(int i = 0; i < T.size(); ++i){
            while(!stk.empty() && T[i] > T[stk.top()]){
                res[stk.top()] = i - stk.top();
                stk.pop();
            }
            stk.push(i);
        }
        return res;
    }
};
```



#### [逛街](https://www.nowcoder.com/questionTerminal/35fac8d69f314e958a150c141894ef6a)

> 判断每个位置能够看到的高楼数量。比所在楼低或者高但没有更高的挡住。
>
> ```
> Input:
> 6
> 5 3 8 3 2 5
> Output:
> 3 3 5 4 4 4
> ```

判断每个位置左右两边的**递增子序列**的长度，再加上1（自己）即为所求。

使用单调递减栈，从左往右，从右往左。

```c++
int main() {
    vector<int> nums{ 5, 3, 8, 3, 2, 5 };

    vector<int> res(nums.size(), 1);
    stack<int> leStk;
    for (int i = 0; i < nums.size(); ++i) {
        res[i] += leStk.size();
        while (!leStk.empty() && nums[i] > nums[leStk.top()]) {
            leStk.pop();
        }
        leStk.push(i);
    }
    stack<int> riStk;
    for (int i = nums.size() - 1; i >= 0; --i) {
        res[i] += riStk.size();
        while (!riStk.empty() && nums[i] > nums[riStk.top()]) {
            riStk.pop();
        }
        riStk.push(i);
    }
    output(res);
}
```



#### [456. 132 Pattern](https://leetcode.com/problems/132-pattern/)

> 给定数组中，判断是否存在132模式。i < j < k，nums[i] < nums[k] < nums[j] 。

单调**递减栈**，栈顶存最小值，底部保存最大值，然后同时记录第二大的值sec，当前值再和第二大的值比较。

```c++
class Solution {
public:
    bool find132pattern(vector<int>& nums) {
        stack<int> stk;
        int sec = INT_MIN;
        for(int i = nums.size() - 1; i >= 0; --i){
            if(nums[i] < sec) return true;
            while(!stk.empty() && nums[i] > stk.top()){
                sec = stk.top();
                stk.pop();
            }
            stk.push(nums[i]);
        }
        return false;
    }
};
```



#### [84. 柱状图中最大的矩形](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/)

> 给定 *n* 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
>
> 求在该柱状图中，能够勾勒出来的矩形的最大面积。

单调**递增**栈

```c++
class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        heights.insert(heights.begin(), 0);
        heights.push_back(0);
        stack<int> stk;
        int res = 0;
        for(int i = 0; i < heights.size(); ++i){
            while(stk.size() && heights[i] < heights[stk.top()]){
                int top = stk.top();
                stk.pop();
                res = max(res, heights[top] * (i - stk.top() - 1));
            }
            stk.push(i);
        }
        return res;
    }
};
```





### 括号问题



#### [1249. 移除无效的括号](https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses/)

> 给定一字符串（包含括号和字符），移除多余的括号，使括号有效，并且保留原来的字符。



```c++
class Solution {
public:
    string minRemoveToMakeValid(string s) {
        vector<bool> rem(s.size(), false);
        stack<int> stk;
        for(int i = 0; i < s.size(); ++i){
            if(s[i] == '('){
                rem[i] = true;
                stk.push(i);
            }else if(s[i] == ')'){
                if(!stk.empty()){
                    rem[stk.top()] = false;
                    stk.pop(); 
                }else{
                    rem[i] = true;
                }
            }
        }
        string res;
        for(int i = 0; i < s.size(); ++i){
            if(!rem[i]) res += s[i];
        }
        return res;
    }
};
```



## String



### KMP

#### [459. 重复的子字符串](https://leetcode-cn.com/problems/repeated-substring-pattern/)

> 判断字符串是否是由重复子串构成

```c++
class Solution {
public:
    bool repeatedSubstringPattern(string s) {
        if (s.empty()) return false;
        vector<int> next = getNext(s);
        int len = s.size();
        return next[len - 1] != -1 && len % (len - (next[len - 1] + 1)) == 0;
    }

    vector<int> getNext(string& s) {
        vector<int> next(s.size());
        next[0] = -1;
        int j = -1;
        for (int i = 1; i < s.size(); i++) {
            while (j >= 0 && s[i] != s[j + 1]) {
                j = next[j];
            }
            if (s[i] == s[j + 1]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
};
```

https://leetcode-cn.com/problems/repeated-substring-pattern/solution/459-zhong-fu-de-zi-zi-fu-chuan-kmpjing-dian-wen-ti/





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





## Union-Find



并查集，顾名思义，一个实现了合并和查询集合方法的数据结构。最常见的方式是用数组来实现。

例如，节点 ① ② ③ ④，已知 ① 与 ② 相连， ② 与 ④ 相连，经过并查集操作，应该形成两个集合，其中一个集合是 {① ② ④}，另一个是 {③}。





#### [684. 冗余连接](https://leetcode-cn.com/problems/redundant-connection/)

> 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。返回一条可以删去的边，使得结果图是一个有着N个节点的树。
>

无向图中，删除最早的边，该边原本就能够连通。

```c++
class Solution {
public:
    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        vector<int> arr(edges.size() + 1);
        for(int i = 1; i < arr.size(); ++i) arr[i] = i;
        for(auto& i: edges){
            if(arr[i[0]] == arr[i[1]]) return i;
            // 需要引入temp变量，因为arr数组可能会改变
            int temp = arr[i[0]];
            for(int j = 0; j < arr.size(); ++j){
                if(arr[j] == temp){
                    arr[j] = arr[i[1]];
                }
            }
        }
        return {};
    }
};
```





## ZZZ

### 装载货物

> a, b, k, v。   a个物品，b个隔板，每个箱子最多有k个隔间，每个隔间最多可装v个物品。其中箱子不放隔板也等于有一个隔间，放b - 1个隔板可有b个隔间。所有参数都大于等于1。求装完所有物品所需最少箱子数量。

不要尝试一口吃成胖子，对于复杂点的情况，可以简化成一次次的**模拟操作**。

```java
public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            int k = cin.nextInt();
            int v = cin.nextInt();
            int res=0;
            while(a>0){
                if(b>=k-1){
                    b=b-(k-1);
                    a-=k*v;
                }else if(b<(k-1) && b!=0){
                    a-=v*(b+1);
                    b=0;
                }else{
                    a-=v;
                }
                res++;
            }
            System.out.println(res);
        }
    }
```



### 最小的交换次数

> 给定仅包含‘B’或‘G’的字符数组，可以相邻交换，求最小交换次数，使得‘B’和‘G’分别在两端（即彼此分开）。如“BBGGG”或“GGGBB”。

分别考虑将‘B’和‘G’移到前面的次数，取最小。由于只有两种对象，移完一种，另外一种也ok了。

```c++
    int lineup(string peoples) {
        int ge1 = 0, ge2 = 0, n = peoples.length();
        int res1 = 0, res2 = 0;
        for (int i = 0; i < n; ++i) {
            if (peoples[i] == 'G') {
                res1 += i - ge1;
                ++ge1;
            } else {
                res2 += i - ge2;
                ++ge2;
            }
        }
        return min(res1, res2);
    }
```







## Other



二叉树遍历，输入为数组非TreeNode时，可以使用`2 * i + 1`和`2 * i + 2`来遍历子节点。





pair、vector可以作为map的key，但不能作为unordered_map的key

```c++
map<pair<int, int>, int> mp;
mp[make_pair(1, 2)] = 11;
```




[求不大于k的最大子数组和](https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k)





C++输入数组处理

```c++
int main() {
    vector<int> nums(100);
    int size = 0, tmp = 0;
    while(cin >> tmp) {
        nums[size++] = tmp;
        if (getchar() == '\n') break;
    }
    cout << "size: " << size << endl;
    nums.resize(size);
    output(nums);
    return 0;
}
```



```c++
    string str;
	// 处理多行字符串（可能为空字符）
    while (getline(cin, str)) {...}
```

```java
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){ }
```

