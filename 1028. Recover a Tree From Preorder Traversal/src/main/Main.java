package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javafx.util.Pair;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	TreeNode root;
	/* traverse string keeping level counter
	 * if level is lower than peek pop else push
	 * return first element
	 */
	int index = 0;
    public TreeNode recoverFromPreorder(String S) {
    	if(S.length() == 0)
    		return null;
    	Stack<TreeNode> stack = new Stack<>();
    	
    	int num = getval(S);
    	stack.push(new TreeNode(num));
    	
    	int level = 0;
    	while(index < S.length()) {
    		if(S.charAt(index) == '-') {
    			level++;
    			index++;
    		} else { 
    			int val = getval(S);
    			TreeNode node = new TreeNode(val);
    			while(!stack.isEmpty() && stack.size() > level)
    				stack.pop();
    			TreeNode temp = stack.peek();
    			if(temp.left == null) {
    				temp.left = node;
    			} else
    				temp.right = node;
    			stack.push(node);
    			level = 0;
    		}
    	}
    	while(stack.size() > 1)
    		stack.pop();
        return stack.peek();
    }
    
    private int getval(String S) {
    	int num = 0;
    	while(index < S.length() && Character.isDigit(S.charAt(index))) {
    		num = num*10 + S.charAt(index) - '0';
    		index++;
    	}
    	return num;
    }
    
    public TreeNode recoverFromPreorder1(String S) {
    	// level -> Node
    	Map<Integer, TreeNode> map = new HashMap<>();
    	int i = 0, level = 0;
    	
    	while(i < S.length()) {
    		if(S.charAt(i) == '-') {
    			i++;
    			level++;
    		} else {
    			int j = i;
    			while(j < S.length() && Character.isDigit(S.charAt(j)))
    				j++;
    			int val = Integer.parseInt(S.substring(i, j));
    			TreeNode node = new TreeNode(val);
    			i = j;
    			map.put(level, node);
    			if(level > 0) {
    				TreeNode parent = map.get(level-1);
    				if(parent.left == null)
    					parent.left = node;
    				else
    					parent.right = node;
    			}
    			level = 0;
    		}
    	}
    	return map.get(0);
    }
    
    void preorder(TreeNode root) {
    	if(root == null) {
    		//System.out.print("null ");
    		return;
    	}
    	
    	System.out.print(root.val + " ");
    	preorder(root.left);
    	preorder(root.right);
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "1-401--349---90--88";
		Solution s = new Solution();
		s.root = s.recoverFromPreorder(S);
		s.preorder(s.root);
		s.root = s.recoverFromPreorder(S);
		s.preorder(s.root);
	}
}