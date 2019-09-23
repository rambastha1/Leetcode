package main;

class Solution {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode root;
	
    public ListNode[] splitListToParts(ListNode root, int k) {
    	
    	if(root == null)
    		return null;
    	int len = getlength(root);
    	ListNode []res = new ListNode[k];
    	ListNode temp = root, next = root;
    		
    	int parts = len/k, extra = len%k;
    	temp = root;
    	
    	for(int i = 0;i < k;i++) {
    		res[i] = next;
    		temp = next;
    		for(int j = 0;j < parts + ((extra>0)?1:0) - 1 && temp != null;j++) {
    			temp = temp.next;
    			
    		}
    		if(temp != null) {
				next = temp.next;
				temp.next = null;
    		}
    		extra--;
    	}
    	return res;
    }
    
    int getlength(ListNode root) {
    	if(root == null)
    		return 0;
    	ListNode temp = root;
    	int count = 0;
    	while(temp != null) {
    		count++;
    		temp = temp.next;
    	}
    	return count;
    }
    
    void print(ListNode root) {
    	if(root == null)
    		return;
    	ListNode temp = root;
    	while(temp != null) {
    		System.out.print(temp.val + " ");
    		temp = temp.next;
    	}
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new ListNode(1);
		s.root.next = s.new ListNode(2);
		s.root.next.next = s.new ListNode(3);
		s.root.next.next.next = s.new ListNode(4);
		s.root.next.next.next.next = s.new ListNode(5);
		s.root.next.next.next.next.next = s.new ListNode(6);
		s.root.next.next.next.next.next.next = s.new ListNode(7);
		s.root.next.next.next.next.next.next.next = s.new ListNode(8);
		s.root.next.next.next.next.next.next.next.next = s.new ListNode(9);
		s.root.next.next.next.next.next.next.next.next.next = s.new ListNode(10);
		
		//s.print(s.root);
		int k = 3;
		Solution.ListNode []node = s.splitListToParts(s.root, k);
		if(node != null) {
			for(int i = 0;i < node.length;i++)
				s.print(node[i]);
		}
		
	}
}