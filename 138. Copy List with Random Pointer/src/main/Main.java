package main;

import java.util.HashMap;
import java.util.Map;

class Solution {
	class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
	}
	
	RandomListNode head;
	
	/*
	 * Time 0(n) and constant space
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		if(head == null || head.next == null)
			return head;
		
		RandomListNode curr = head, temp = null;
		while(curr != null) {
			temp = curr.next;
			curr.next = new RandomListNode(curr.label);
			curr.next.next = temp;
			curr = temp;
		}
		
		curr = head;
		while(curr != null) {
			if(curr.next != null) 
				curr.next.random = (curr.random != null)?curr.random.next:curr.random;
			curr = (curr.next != null)?curr.next.next:curr.next;
		}
		
		RandomListNode original = head, copy = head.next;
		temp = copy;
		
		while(original != null && copy != null) {
			original.next = (original.next != null)?original.next.next:original.next;
			copy.next = (copy.next != null)?copy.next.next:copy.next;
			original = original.next;
			copy = copy.next;
		}		
		return temp;
	}	
	
	
	/*
	 * 0(n) time and 0(n) space using hashmap 
	 */
	
    /*public RandomListNode copyRandomList(RandomListNode head) {
    	if(head == null || head.next == null)
    		return head;
    	
    	RandomListNode origcurr = head, clonecurr = null;
    	Map<RandomListNode, RandomListNode> map = new HashMap<>();
    	while(origcurr != null) {
    		clonecurr = new RandomListNode(origcurr.label);
    		map.put(origcurr, clonecurr);
    		origcurr = origcurr.next;
    	}
    	
    	origcurr = head;
    	while(origcurr != null) {
    		clonecurr = map.get(origcurr);
    		clonecurr.next = map.get(origcurr.next);
    		clonecurr.random = map.get(origcurr.random);
    		origcurr = origcurr.next;
    	}
        return map.get(head);
    }*/	
    
    void print(RandomListNode node) {
    	if(node == null)
    		return;
    	while(node != null) {
    		System.out.print(node.label + " ");
    		node = node.next;
    	}
    	System.out.println();
    }
    
    /*void printrandom(RandomListNode node) {
    	if(node == null)
    		return;
    	while(node != null) {
    		System.out.print(node.label + " ");
    		node = node.random;
    	}
    }*/
}

public class Main {
	public static void main(String[] args) {
		
		Solution s = new Solution();
		s.head = s.new RandomListNode(1);
		s.head.next = s.new RandomListNode(2);
		s.head.next.next = s.new RandomListNode(3);
		s.head.next.next.next = s.new RandomListNode(4);
		s.head.next.next.next.next = s.new RandomListNode(5);
		s.head.random = s.head.next.next;
		s.head.next.random = s.head;
		s.head.next.next.random = s.head.next.next.next.next;
		s.head.next.next.next.random = s.head.next.next;
		s.head.next.next.next.next.random = s.head.next;
		s.print(s.head);
		Solution s1 = new Solution();
		s1.head = s.copyRandomList(s.head);
		s1.print(s1.head);
	}
}