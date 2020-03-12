package listnode;

public class ListNode {
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
