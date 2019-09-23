package main;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
    public ListNode detectCycle(ListNode head) {
    	if(head == null)
    		return head;
    	ListNode slow = head, fast = slow.next;
    	boolean iscycle = false;
    	while(slow != null && fast != null) {
    		if(slow == fast) {
    			iscycle = true;
    			break;
    		}
    		slow = slow.next;
    		if(fast.next != null)
    			fast = fast.next.next;
    		else
                break;
    	}
    	
    	if(!iscycle)
    		return null;
    	
    	slow = head;
    	while(slow != null && fast != null && fast.next != slow) {
    		slow = slow.next;
    		fast = fast.next;
    	}
    	return slow;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
