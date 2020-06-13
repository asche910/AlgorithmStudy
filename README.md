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
-  `~`为二进制取反运算符， `x ^ 0 = x` 、`x ^ 1 = -（~x）`



## Depth-first Search





#### [526. Beautiful Arrangement](https://leetcode-cn.com/problems/beautiful-arrangement/)

> 求优美排列的个数。1-N中，每个位置能够被上面的值整除或整除上面的值

```java
class Solution {
    int cnt;

    public int countArrangement(int N) {
        permute(new boolean[N + 1], 1, N);
        return cnt;
    }

    public void permute(boolean[] visited, int cur, int N){
        if(cur > N){
            cnt++;
            return;
        }
        for(int i = 1; i <= N; i++){
            if(!visited[i] && (i % cur == 0 || cur % i == 0)){
                visited[i] = true;
                // 注意是cur不是i
                permute(visited, cur + 1, N);
                visited[i] = false;
            }
        }
    }
}
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







## Dynamic Programming

[Here](./src/dp/README.md)





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

相邻元素拥有制约关系，则可采取先左右扫描，再贪心结合

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

> 给定一个整数数组和一个整数 **k，**你需要找到该数组中和为 **k** 的连续的子数组的个数。

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









## Math



* 判断n是否是素数，判断`[2,sqrt(n)]`内的数是否能整除n



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

关键在于交点坐标，高的最小值，底的最大值

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

判断每个位置左右两边的**递增子序列**的长度，再加上1（自己）即为所求

```c++
int main() {
	int n, nums[100002];
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> nums[i];
	}
	vector<int> arr(n);
	stack<int> s1, s2;
	for (int i = n - 1; i >= 0; i--) {
		arr[i] = s1.size();
		while (!s1.empty() && nums[s1.top()] <= nums[i]) {
			s1.pop();
		}
		s1.push(i);
	}

	for (int i = 0; i < n; i++) {
		arr[i] += s2.size() + 1;
		while (!s2.empty() && nums[s2.top()] <= nums[i]) s2.pop();
		s2.push(i);
		cout << arr[i] << " ";
	}
	return 0;
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





## Other



[求不大于k的最大子数组和](https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k)