package main;

import java.util.Random;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode head;
	
	public Solution(ListNode head) {
    	this.head = head;    	
    }
    
    /** Returns a random node's value. */
	/*
	 * Need to return any element from k items of reservoir prob = 1/k
	 * Thus last index is used for this purpose
	 * It is not necessary that r.nextInt(i+1) == i.
	 * Has probability of 1/i (0 to i-1)
	 */
    public int getRandom() {
    	Random r = new Random();
    	ListNode node = this.head;
    	int temp = node.val;
    	for(int i = 1;node.next != null;i++) {
    		node = node.next;
    		if(r.nextInt(i+1) == i)
    			temp = node.val;    		
    	}
    	return temp;    	
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

public class Main {
	public static void main(String[] args) {		
		
	}
}