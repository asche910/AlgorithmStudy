import tool.Tools;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("Test...");

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.poll());
    }

    public void heapSort(int[] nums){
        int len = nums.length;
        for(int i = len / 2 - 1; i >= 0; i--){
            heapify(nums, i, len - 1);
        }
        System.out.println(Arrays.toString(nums));


        for(int i = len - 1; i > 0; i--){
            Tools.swap(nums, 0, i);
            heapify(nums, 0, i - 1);
        }
    }

    private void heapify(int[] nums, int start, int end){
        int root = start;
        while(root * 2 + 1 <= end){
            int child = 2 * root + 1;
            if (child + 1 <= end && nums[child + 1] > nums[child]) child++;
            if(nums[root] < nums[child]){
                Tools.swap(nums, root, child);
                root = child;
            }else{
                root = end;
            }
        }
    }
}

