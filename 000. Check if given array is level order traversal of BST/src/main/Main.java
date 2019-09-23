package main;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
	
	class Node {
		int val, min, max;
		public Node(int val, int min, int max) {
			this.val = val;
			this.min = min;
			this.max = max;
		}
	}
	
	boolean islevelorder(int []arr) {
		if(arr == null || arr.length == 0)
			return false;
		if(arr.length == 1)
			return true;
		Queue<Node> q = new LinkedList<>();
		int i = 0;
		q.offer(new Node(arr[i++], Integer.MIN_VALUE, Integer.MAX_VALUE));
		
		while(i != arr.length && !q.isEmpty()) {
			Node node = q.poll();
			if(i < arr.length && arr[i] > node.min && arr[i] < node.val) {
				q.offer(new Node(arr[i++], node.min, node.val));
			} 
			if (i < arr.length && arr[i] < node.max && arr[i] > node.val) {
				q.offer(new Node(arr[i++], node.val, node.max));
			}
		}
		if(i == arr.length)
			return true;
		return false;
	}
}

public class Main {
	public static void main(String[] args) {
		int []arr = {7, 4, 12, 3, 6, 8, 1, 5, 10};
		System.out.println(new Solution().islevelorder(arr));
	}
}