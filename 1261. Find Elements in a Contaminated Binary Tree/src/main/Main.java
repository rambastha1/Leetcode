package main;

import java.util.HashSet;
import java.util.Set;

class FindElements {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	Set<Integer> set;
    public FindElements(TreeNode root) {
        this.set = new HashSet<>();
        recover(root, 0);
    }
    
    private void recover(TreeNode root, int val) {
    	if(root == null)
    		return;
    	root.val = val;
    	set.add(val);
    	recover(root.left, 2*val+1);
    	recover(root.right, val*2+2);
    }
    
    public boolean find(int target) {
        return set.contains(target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */

public class Main {
	public static void main(String[] args) {

	}
}
