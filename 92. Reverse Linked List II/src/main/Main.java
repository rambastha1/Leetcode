package main;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode head;
	
    public ListNode reverseBetween(ListNode head, int m, int n) {
    	if(head == null || head.next == null)
    		return head;
    	ListNode prev = null, curr = head, next = null;
    	int i = 1;
    	while(curr != null && i < m) {
    		prev = curr;
    		curr = curr.next;
    		i++;
    	}
    	
    	if(curr == null)
    		return head;
    	
    	ListNode ptemp = prev, ctemp = curr;
    	while(curr != null && i >= m && i <= n) {
    		next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;
    		i++;
    	}
    	if(ptemp != null)
    		ptemp.next = prev;
    	if(ctemp != null)
    		ctemp.next = curr;
    	return ptemp==null?prev:head;
    }
    
    void display(ListNode head) {
    	while(head != null) {
    		System.out.print(head.val + " ");
    		head = head.next;
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
		s.head.next.next.next = s.new ListNode(4);
		s.head.next.next.next.next = s.new ListNode(5);
		int m = 1, n = 4;
		s.head = s.reverseBetween(s.head, m, n);
		s.display(s.head);
	}
}
