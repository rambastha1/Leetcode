package main;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode head;
	
    public ListNode swapPairs(ListNode head) {
    	if(head == null || head.next == null)
    		return head;
    	
    	ListNode prev = new ListNode(0), temp = prev, curr = head, next = null;
    	while(curr != null) {
    		next = curr.next;
    		if(next == null)
    			break;
    		prev.next = next;
    		curr.next = next.next;
    		next.next = curr;    		    		
    		prev = curr;
    		curr = curr.next;
    	}
    	//print(temp.next);
    	return temp.next;
    }
    
    void print(ListNode node) {
    	while(node != null) {
    		System.out.print(node.val + " ");
    		node = node.next;
    	}	
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.head = s.new ListNode(1);
		s.head.next = s.new ListNode(2);
		s.head.next.next = s.new ListNode(3);
		//s.head.next.next.next = s.new ListNode(4);
		s.print(s.head);
		s.head = s.swapPairs(s.head);
		s.print(s.head);
	}
}
