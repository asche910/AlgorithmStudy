package tool;

import java.util.Arrays;
import java.util.List;

public class Tools {

    public static int[][] DIRS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static <T> void outputList(List<T> list){
        System.out.println(list.toString());
    }

    /**
     * output multiple dimension List
     * @param list
     * @param <T>
     */
    public static <T> void outputMultipleList(List<List<T>> list){
        String s = Arrays.deepToString(list.toArray());
        System.out.println(s);
    }
}
