package main;

import java.util.LinkedList;
import java.util.TreeMap;

/* https://leetcode.com/articles/max-stack/
 * 
 */

class MaxStack {
	class Node {
		int val, max;
		Node prev, next;
		public Node(int val) {
			this.val = val;
		}
	}
	
	TreeMap<Integer, LinkedList<Node>> map;
	
	public Node head, tail;
	
	public MaxStack() {
		head = new Node(0);
		tail = new Node(0);
		head.next = tail;
		tail.prev = head;
		map = new TreeMap<>();
	}

	public void push(int x) {
		Node newnode = new Node(x);
		newnode.next = tail;
		newnode.prev = tail.prev;
		tail.prev.next = newnode;
		tail.prev = newnode;
		if(!map.containsKey(x))
			map.put(x, new LinkedList<>());
		map.get(x).add(newnode);
	}

	public int pop() {
		Node node = tail.prev;
		if(node == head)
			return 0;
		node.prev.next = node.next;
		node.next.prev = node.prev;
		map.get(node.val).removeLast();
		if(map.get(node.val).size() == 0)
			map.remove(node.val);
		return node.val;
	}

	public int top() {
		return tail.prev.val;
	}

	public int peekMax() {
		return map.lastKey();
	}

	public int popMax() {
		int max = peekMax();
		Node node = map.get(max).removeLast();
		node.prev.next = node.next;
		node.next.prev = node.prev;
		if(map.get(max).size() == 0)
			map.remove(max);
		return max;
	}
}


public class Main {
	public static void main(String[] args) {

	}
}
