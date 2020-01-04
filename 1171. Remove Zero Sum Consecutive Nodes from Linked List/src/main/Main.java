package main;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/discuss/366319/JavaC%2B%2BPython-Greedily-Skip-with-HashMap
class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	/* if 1->2->-3->3->1->null
	 * removes 1->2->-3 resulting in 3->1
	 * when encounter already calculated sum, remove nodes from that to curr
	 * 
	 */
	public ListNode removeZeroSumSublists(ListNode head) {
    	if(head == null || (head.next == null && head.val == 0))
    		return null;
    	ListNode res = new ListNode(0);
    	res.next = head;
    	// important line as we need to delete nodes from next of map.get(sum)
    	ListNode curr = res;
    	// sum -> node
    	Map<Integer, ListNode> map = new HashMap<>();
    	int sum = 0;
    	
    	while(curr != null) {
    		sum += curr.val;
    		if(map.containsKey(sum)) {
    			// delete all nodes between these two
    			curr = map.get(sum).next;
    			int p = sum + curr.val;
    			while(p != sum) {
    				map.remove(p);
    				curr = curr.next;
    				p += curr.val;
    				
    			}
    			map.get(sum).next = curr.next;
    		} else {
    			map.put(sum, curr);
    		}
    		curr = curr.next;
    	}
    	return res.next;
    }
    
    public ListNode head;
    
    public void display(ListNode head) {
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
		s.head.next.next = s.new ListNode(-3);
		s.head.next.next.next = s.new ListNode(3);
		s.head.next.next.next.next = s.new ListNode(1);
		//s.head.next.next.next.next.next = s.new ListNode(-1);
		s.display(s.head);
		s.head = s.removeZeroSumSublists(s.head);
		s.display(s.head);
	}
}
