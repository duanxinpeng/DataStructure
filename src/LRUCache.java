import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// 通过继承 LinkedHashMap
//public class LRUCache extends LinkedHashMap<Integer,Integer> {
//    int capacity;
//    public LRUCache(int capacity) {
//        super(capacity,0.75f,true);
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        return super.getOrDefault(key,-1);
//    }
//
//    public void put(int key, int value) {
//        super.put(key,value);
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry eldest) {
//        return size() > this.capacity;
//    }
//}
class LRUCache {
    class Node {
        // key是必须的，删除链表尾部节点时，同样需要从map中删掉，需要利用key来删除
        int key;
        int val;
        Node pre, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> map;
    Node head, tail;
    int size, capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.pre = this.head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node tmp = map.get(key);
        afterGet(tmp);
        return tmp.val;
    }

    public void afterGet(Node node) {
        // 删除
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        // 插入
        node.pre = this.head;
        node.next = this.head.next;
        this.head.next = node;
        node.next.pre = node;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node tmp = map.get(key);
            tmp.val = value;
            afterGet(tmp);
        } else {
            Node tmp = new Node(key, value);
            map.put(key, tmp);
            insert(tmp);
            this.size++;
            if (this.size > this.capacity) {
                removeEldestNode();
                this.size--;
            }
        }
    }

    public void insert(Node node) {
        node.pre = this.head;
        node.next = this.head.next;
        this.head.next = node;
        node.next.pre = node;
    }

    public void delete(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    public void removeEldestNode() {
        Node tmp = this.tail.pre;
        map.remove(tmp.key);
        delete(tmp);
    }
}
