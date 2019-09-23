package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode head;
	
    public int[] nextLargerNodes(ListNode head) {
    	if(head == null)
    		return new int[0];
    	List<Integer> list = new ArrayList<>();
    	int len = 0;
    	while(head != null) {
    		list.add(head.val);
    		len++;
    		head = head.next;
    	}
    	int []res = new int[len];
    	Stack<Integer> stack = new Stack<>();
    	
    	for(int i = 0;i < res.length;i++) {
    		while(!stack.isEmpty() && list.get(i) > list.get(stack.peek())) {
    			res[stack.pop()] = list.get(i);
    		}
    		stack.push(i);
    	} 		
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.head = s.new ListNode(2);
		s.head.next = s.new ListNode(7);
		s.head.next.next = s.new ListNode(4);
		s.head.next.next.next = s.new ListNode(3);
		s.head.next.next.next.next = s.new ListNode(5);
		int []res = s.nextLargerNodes(s.head);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}