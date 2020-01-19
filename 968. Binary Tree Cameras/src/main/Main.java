package main;

// https://leetcode.com/problems/binary-tree-cameras/discuss/211966/Super-Clean-Java-solution-beat-100-DFS-O(n)-time-complexity
class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	int cam = 0;
	int NOT_MONITORED = 0;
	int MONITORED_WITHOUTCAM = 1;
	int MONITORED_WITHCAM = 2;
    public int minCameraCover(TreeNode root) {
    	if(root == null)
    		return 0;
    	int val = dfs(root);
    	return cam + (val == NOT_MONITORED?1:0);
    }
    
    private int dfs(TreeNode root) {
    	if(root == null)
    		return MONITORED_WITHOUTCAM;
    	
    	int l = dfs(root.left);
    	int r = dfs(root.right);
    	if(l == MONITORED_WITHOUTCAM && r == MONITORED_WITHOUTCAM)
    		return NOT_MONITORED;
    	
    	if(l == NOT_MONITORED || r == NOT_MONITORED) {
    		cam++;
    		return MONITORED_WITHCAM;
    	}
    	return MONITORED_WITHOUTCAM;
    }
    
    TreeNode root;
}
public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(0);
		s.root.left = s.new TreeNode(0);
		s.root.left.left = s.new TreeNode(0);
		s.root.left.right = s.new TreeNode(0);
		System.out.println(new Solution().minCameraCover(s.root));
	}
}
