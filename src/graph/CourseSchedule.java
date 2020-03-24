package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 判断能否学习完所有课程。
 *
 * 建图，寻找是否存在环
 * @see <a href="https://leetcode-cn.com/problems/course-schedule/">Here</a>
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for(int[] pre: prerequisites){
            graph[pre[0]].add(pre[1]);
        }
        int[] flags = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            if(!dfs(graph, flags, i)) return false;
        }
        return true;
    }

    private boolean dfs(List<Integer>[] graph, int[] flags, int cur){
        if(flags[cur] == 1) return false;
        if(flags[cur] == -1) return true;
        flags[cur] = 1;
        for(Integer next: graph[cur]){
            if(!dfs(graph, flags, next)) return false;
        }
        flags[cur] = -1;
        return true;
    }
}
