package design;

import java.util.HashMap;
import java.util.Map;

/**
 * 最不经常使用 - LFU
 *
 *  维护一个频次递减的双向链表
 * @see <a href="https://leetcode-cn.com/problems/lfu-cache/">Here</a>
 */
public class LFUCache {
    class Node {
        int key;
        int val;
        int cnt;
        Node pre, next;

        public Node(int key, int val, int cnt) {
            this.key = key;
            this.val = val;
            this.cnt = cnt;
        }

        public void incr() {
            cnt++;
        }
    }

    Map<Integer, Node> map;
    Node head, tail;
    int capacity;
    int size;

    public LFUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(0, 0, Integer.MAX_VALUE);
        tail = new Node(-1, 0, -1);
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            use(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            use(node);
            return;
        } else if (size == capacity) {
            if (size == 0) return;
            removeLast();
            size--;
        }
        Node node = new Node(key, value, 0);
        addToLast(node);
        use(node);
        size++;
    }

    private void use(Node node) {
        node.incr();
        Node pre = node.pre;
        while (pre.cnt <= node.cnt) {
            Node start = pre.pre;
            Node end = node.next;

            start.next = node;
            node.pre = start;

            node.next = pre;
            pre.pre = node;

            pre.next = end;
            end.pre = pre;

            pre = start;
        }
    }

    private void addToLast(Node node) {
        Node pre = tail.pre;
        pre.next = node;
        node.pre = pre;

        node.next = tail;
        tail.pre = node;
        map.put(node.key, node);
    }

    private void removeLast() {
        Node last = tail.pre;
        last.pre.next = tail;
        tail.pre = last.pre;
        map.remove(last.key);
    }
}