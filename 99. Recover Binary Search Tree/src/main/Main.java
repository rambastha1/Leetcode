package main;
/* https://www.geeksforgeeks.org/fix-two-swapped-nodes-of-bst/
 * https://www.geeksforgeeks.org/two-nodes-of-a-bst-are-swapped-correct-the-bst-set-2/
 * https://leetcode.com/problems/recover-binary-search-tree/discuss/32559/Detail-Explain-about-How-Morris-Traversal-Finds-two-Incorrect-Pointer
 */
class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	// use morris traversal to find 2 swapped nodes -> swap values
	// time 0(n) space 0(1)
    public void recoverTree(TreeNode root) {
        if(root == null)
        	return;
        TreeNode curr = root, pre = null;
        // question specific
        TreeNode temp = null, first = null, sec = null;
        
        while(curr != null) {
        	if(curr.left == null) {
        		// questions part
        		if(temp != null && temp.val > curr.val) {
        			if(first == null) {
	        			first = temp;
	        			sec = curr;
        			} else {
        				sec = curr;
        			}
        		}
        		temp = curr;
        		curr = curr.right;
        	} else {
        		pre = curr.left;
        		while(pre.right != null && pre.right != curr)
        			pre = pre.right;
        		
        		if(pre.right == null) {
        			pre.right = curr;
        			curr = curr.left;
        		} else {
        			// questions part
        			if(temp != null && temp.val > curr.val) {
        				if(first == null) {
        					first = temp;
        					sec = curr;
        				} else {
        					sec = curr;
        				}
        			}
        			temp = curr;
        			pre.right = null;
        			curr = curr.right;
        		}
        			
        	}
        }
        // questions part
        if(first != null && sec != null) {
        	int val = first.val;
        	first.val = sec.val;
        	sec.val = val;
        }
    }
}

public class Main {
	public static void main(String[] args) {
		
	}
}
