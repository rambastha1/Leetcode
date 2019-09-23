package main;

/*
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 * Example:
 * Input:
 * 1->2->3
 * 
 * Output:
 * 1->2->4
 * 
 * Basically return num + 1
 */

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode head;
	
	public ListNode plusOne(ListNode head) {
		ListNode res = new ListNode(0);
		res.next = head;
		
		ListNode curr = head, lastdigitnot9 = null;
		while(curr != null) {
			if(curr.val != 9)
				lastdigitnot9 = curr;
			curr = curr.next;
		}
		
		//If digit is 9
		if(lastdigitnot9 == null) {
			res.next = new ListNode(0);
			res.val = 0;
			return res;
		}
		curr = lastdigitnot9.next;
		lastdigitnot9.val++;
		while(curr != null) {
			curr.val = 0;
			curr = curr.next;
		}
		
		if(res.val == 1)
			return res;
		return res.next;
	}
	
	void print(ListNode head) {
		while(head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.head = s.new ListNode(9);
		s.head.next = s.new ListNode(3);
		s.head.next.next = s.new ListNode(9);
		s.head = s.plusOne(s.head);
		s.print(s.head);
	}
}