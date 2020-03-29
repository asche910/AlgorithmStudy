package design;

import java.util.HashMap;
import java.util.Map;

/***
 * HashMap外部维护一个双向链表
 *
 * head <--> node1 <---> ... <---> tail
 *
 * @see <a href="https://leetcode-cn.com/problems/lru-cache/">Here</a>
 */
public class LRUCache {
    static class Node {
        int key;
        int val;
        Node pre, next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> cache = new HashMap<>();
    int size;
    int capacity;
    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, -1);
        tail = new Node(0, -1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        remove(node);
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            size--;
        } else if (size == capacity) {
            removeTail();
            size--;
        }
        Node node = new Node(key, value);
        addToHead(node);
        cache.put(key, node);
        size++;
    }

    private void addToHead(Node node) {
        Node next = head.next;

        head.next = node;
        node.pre = head;

        node.next = next;
        next.pre = node;
    }

    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void removeTail() {
        Node node = tail.pre;
        remove(node);
        cache.remove(node.key);
    }
}
