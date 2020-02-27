package backtrack;

import tool.Tools;

import java.util.*;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 * @see <a href="https://leetcode.com/problems/permutations-ii/">Here</a>
 */
public class Permutation {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Permutation().permuteUnique(new int[]{1, 1, 2, -1});
        Tools.outputMultipleList(lists);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, int[] nums, int index){
        if(index == nums.length){
            List<Integer> sub = new ArrayList<>();
            for(int n: nums) sub.add(n);
            list.add(sub);
        }else{
            Set<Integer> set = new HashSet<>();
            for(int i = index; i < nums.length; i++){
                if(set.contains(nums[i])) continue;
                set.add(nums[i]);
                swap(nums, index, i);
                backtrack(list, nums, index + 1);
                swap(nums, index, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
