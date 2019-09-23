package main;

import java.util.Stack;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode root;
    
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1 == null && l2 == null)
			return null;
		
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		for(ListNode i = l1; i != null; i = i.next) 
			stack1.push(i.val);
		
		for(ListNode i = l2; i != null; i = i.next) 
			stack2.push(i.val);
		
		int sum = 0; // also does work of carry
		ListNode res = new ListNode(0);
		while(!stack1.isEmpty() || !stack2.isEmpty()) {
			if(!stack1.isEmpty()) sum += stack1.pop();
			if(!stack2.isEmpty()) sum += stack2.pop();
			res.val = sum%10;
			ListNode node = new ListNode(sum/10);
			node.next = res;
			res = node;
			sum /= 10;
		}
		return res.val == 0?res.next:res;
    }
    void print(ListNode root) {
    	while(root!= null) {
    		System.out.print(root.val + " ");
    		root = root.next;
    	}
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s1 = new Solution();
		s1.root = s1.new ListNode(7);
		s1.root.next = s1.new ListNode(2);
		s1.root.next.next = s1.new ListNode(4);
		s1.root.next.next.next = s1.new ListNode(3);
		
		Solution s2 = new Solution();
		s2.root = s1.new ListNode(5);
		s2.root.next = s1.new ListNode(6);
		s2.root.next.next = s1.new ListNode(4);
		
		Solution.ListNode node = s1.addTwoNumbers(s1.root, s2.root);
		s1.print(node);
	}
}