package main;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
    public ListNode partition(ListNode head, int x) {
    	if(head == null || head.next == null)
    		return head;
    	ListNode less = new ListNode(0), greater = new ListNode(0);
    	ListNode l = less, g = greater, curr = head;
    	
    	while(curr != null) {
    		if(curr.val < x) {
    			l.next = curr;
    			curr = curr.next;
    			l = l.next;
    			l.next = null;
    		} else {
    			g.next = curr;
    			curr = curr.next;
    			g = g.next;
    			g.next = null;
    		}
    	}
    	if(less.next == null)
    		return greater.next;
    	if(greater.next == null)
    		return less.next;
    	less = less.next;
    	greater = greater.next;
    	l.next = greater;
    	return less;
    }
    
    public ListNode head;
    void print(ListNode head) {
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
		s.head.next = s.new ListNode(4);
		s.head.next.next = s.new ListNode(3);
		s.head.next.next.next = s.new ListNode(2);
		s.head.next.next.next.next = s.new ListNode(5);
		s.head.next.next.next.next.next = s.new ListNode(2);
		int x = 3;
		s.head = s.partition(s.head, x);
		s.print(s.head);
	}
}
