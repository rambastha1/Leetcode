package main;

import java.util.HashSet;
import java.util.Set;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public int numComponents(ListNode head, int[] G) {
    	Set<Integer> set = new HashSet<>();
    	for(int g : G)
    		set.add(g);
    	int ans = 0;
    	while(head != null) {
    		if(set.contains(head.val) && (head.next == null ||!set.contains(head.next.val)))
    			ans++;
    		head = head.next;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
