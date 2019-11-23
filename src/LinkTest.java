import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LinkTest {
    public static void main(String[] args) {
        System.out.println("start:...");

        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;


        System.out.println(intToRoman(1994));
    }

    public static String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();

        HashMap<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');

        int flag = 1000;
        while (num > 0) {
            if (num >= flag && num < 10 * flag) {
                int n = num / flag;
                System.out.println("n--->" + n);
                if (n == 9) {
                    builder.append(map.get(flag)).append(map.get(flag * 10));
                    num -= flag * 9;
                } else if(n > 4){
                    builder.append(map.get(flag * 5));
                    num -= flag * 5;
                }else if (n == 4) {
                    builder.append(map.get(flag)).append(map.get(flag * 5));
                    num %= flag;
                } else {
                    for (int i = 0; i < n; i++) {
                        builder.append(map.get(flag));
                    }
                    num %= flag;
                }
            }else {
                flag /= 10;
                if (flag == 0){
                    flag = 1;
                }
            }
        }
        return builder.toString();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(val + "  ");
        ListNode temp = next;
        while (temp != null) {
            builder.append(temp.val).append("  ");
            temp = temp.next;
        }
        return builder.toString();
    }
}