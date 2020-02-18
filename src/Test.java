import tool.Tools;

import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("Test...");
        int[] nums = new int[]{3, 2, 4, 1, 5, 0};
        selectionSort(nums);
    }

    public static void selectionSort(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            int min = i;
            for(int j = i + 1; j < nums.length; j++){
                if(nums[j] < nums[min]){
                    min = j;
                }
            }
            Tools.swap(nums, min, i);
        }
        System.out.println(Arrays.toString(nums));
    }

}

