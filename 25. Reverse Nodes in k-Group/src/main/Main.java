package main;

// https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11413/Share-my-Java-Solution-with-comments-in-line

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
    public ListNode reverseKGroup(ListNode head, int k) {
    	if(head == null || head.next == null || k <= 1)
    		return head;
    	
    	ListNode res = new ListNode(0);
    	res.next = head;
    	
    	ListNode prev = res, curr = head, next = null, tail = res, temp = null;
    	while(true) {
    		for(int i = 1;i <= k;i++) {
	    		if(tail == null)
	    			return res.next;
	    		tail = tail.next;
    		}
    		
    		if(tail == null)
    			break;
    		// head keeps track of start of reversal node
    		// temp the last node
    		// run through code
     		head = prev.next;
    		while(prev.next != tail) {
    			// pre.next or head will be the node that in end stands at last currently it's in beginning
    			temp = prev.next;
    			prev.next = temp.next;
    			temp.next = tail.next;
    			tail.next = temp;
    		}
    		// prev is moved to previous of next starting node
    		// and also tail so that after executing for loop tail points to current last node of reversal
    		tail = head;
    		prev = head;
    	}
    	return res.next;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}