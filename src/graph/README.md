


207.Course Schedule
```python
class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        res = [[]for i in range(numCourses)]
        for arr in prerequisites:
            res[arr[1]].append(arr[0])
        flags = [0] * numCourses
        def dfs(i):
            if flags[i] == 1: return False
            if flags[i] == -1: return True
            flags[i] = 1
            for j in res[i]:
                if not dfs(j): return False
            flags[i] = -1
            return True
        for i in range(numCourses):
            if not dfs(i): return False
        return True
```