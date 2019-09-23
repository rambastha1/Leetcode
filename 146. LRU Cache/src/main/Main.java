package main;

import java.util.HashMap;
import java.util.Map;

/* https://www.geeksforgeeks.org/design-a-data-structure-for-lru-cache/
 * create a pseudo head and tail to mark the boundary, so that we don't need to check the NULL node during the update.
 * head -> most recent tail -> most outdated
 */

class LRUCache {
	class DLLNode {
		int key, value;
		DLLNode prev, next;
		public DLLNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
	
	DLLNode head, tail;
	Map<Integer, DLLNode> map;
	int capacity, count;
	
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        // use ConcurrentHashMap for thread safe
        map = new HashMap<>();
        head = new DLLNode(0, 0);
        tail = new DLLNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
    	if(map.get(key) != null) {
    		DLLNode node = map.get(key);
    		int res = node.value;
    		// move to most recent position
    		deletenode(node);
    		addtohead(node);
    		return res;
    	}
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.get(key) != null) {
        	DLLNode node = map.get(key);
        	// put most recent value
        	node.value = value;
        	deletenode(node);
        	addtohead(node);
        } else {
        	DLLNode node = new DLLNode(key, value);
        	map.put(key, node);
        	if(count < capacity) {
        		count++;
        		addtohead(node);
        	} else {
        		map.remove(tail.prev.key);
        		deletenode(tail.prev);
        		addtohead(node);
        	}
        }
    }
    
    private void deletenode(DLLNode node) {
    	node.prev.next = node.next;
    	node.next.prev = node.prev;
    }
    
    private void addtohead(DLLNode node) {
    	node.next = head.next;
    	node.prev = head;
    	head.next.prev = node;
    	head.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class Main {
	public static void main(String[] args) {
		int capacity = 2;
		LRUCache l = new LRUCache(capacity);
		l.put(1, 1);
		l.put(2, 2);
		System.out.println(l.get(1));
		l.put(3, 3);
		System.out.println(l.get(2));
		l.put(4, 4);
		System.out.println(l.get(1));
		System.out.println(l.get(3));
		System.out.println(l.get(4));
	}
}
