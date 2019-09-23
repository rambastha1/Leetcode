package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* 95. 96
 * https://leetcode.com/articles/all-possible-full-binary-trees/
 * Nodes with N >= 3 have 2 child
 * Full binary tree have positive odd number of nodes (all child at left is complete binary tree)
 * Full is 2 child 
 * https://stackoverflow.com/questions/12359660/difference-between-complete-binary-tree-strict-binary-tree-full-binary-tre
 */

class Solution {
	
	public class TreeNode {
		int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	
	Map<Integer, List<TreeNode>> memo = new HashMap();
    public List<TreeNode> allPossibleFBT(int N) {
        if (!memo.containsKey(N)) {
            List<TreeNode> ans = new LinkedList();
            if (N == 1) {
                ans.add(new TreeNode(0));
            } else if (N % 2 == 1) {
                for (int x = 0; x < N; ++x) {
                	// x left childs then total number of right = N -1 - x
                    int y = N - 1 - x;
                    List<TreeNode> left =  allPossibleFBT(x);
                    List<TreeNode> right =  allPossibleFBT(y);
                    
                    for (TreeNode l : left) {
                        for (TreeNode r : right) {
                            TreeNode node = new TreeNode(0);
                            node.left = l;
                            node.right = r;
                            ans.add(node);
                        }
                    }
                }
            }
            memo.put(N, ans);
        }
        return memo.get(N);
    }
	
	void levelorder(TreeNode node) {
    	if(node == null)
    		return;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(node);
    	while(!q.isEmpty()) {
    		TreeNode n = q.poll();
    		System.out.print(n.val + " ");
    		if(n.left != null)
    			q.offer(n.left);
    		if(n.right != null)
    			q.offer(n.right);
    	}
    	System.out.println();
    }
}


public class Main {
	public static void main(String[] args) {
		int N = 7;
		List<Solution.TreeNode> list = new Solution().allPossibleFBT(N);
		Solution s = new Solution();
		for(Solution.TreeNode node : list) {
			s.levelorder(node);
		}
	}
}