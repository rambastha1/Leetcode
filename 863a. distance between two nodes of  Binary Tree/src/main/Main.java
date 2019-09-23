package main;


class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode root;
	int d1 = -1, d2 = -1, dist = 0;
	
	int finddistance(TreeNode root, int node1, int node2) {
		if(root == null)
			return 0;
		TreeNode lca = dfs(root, node1, node2, 0);
		if(d1 != -1 && d2 != -1)
			return dist;
		
		if(d1 != -1)
			return findlevel(lca, node2, 0);
		if(d2 != -1)
			return findlevel(lca, node1, 0);
		return -1;
	}
	
	TreeNode dfs(TreeNode root, int node1, int node2, int temp) {
		if(root == null)
			return root;
		
		if(root.val == node1) {
			d1 = temp;
			return root;
		}
		
		if(root.val == node2) {
			d2 = temp;
			return root;
		}
		
		TreeNode left = dfs(root.left, node1, node2, temp+1);
		TreeNode right = dfs(root.right, node1, node2, temp+1);
		
		if(left != null && right != null) {
			dist = (d1+d2) - 2*temp;
			return root;
		}
		return left == null?right:left;
	}
	
	int findlevel(TreeNode root, int node, int dist) {
		if(root == null)
			return 0;
		if(root.val == node)
			return dist;
		int left = findlevel(root.left, node, dist+1);
		return left!= 0?left : findlevel(root.right, node, dist+1);
	}	
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(1);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(3);
		s.root.left.left = s.new TreeNode(4);
		s.root.left.right = s.new TreeNode(5);
		s.root.right.left = s.new TreeNode(6);
		s.root.right.right = s.new TreeNode(7);
		s.root.right.left.right = s.new TreeNode(8);
		
		System.out.println("Dist(4, 5) = " + s.finddistance(s.root, 4, 5)); 
        System.out.println("Dist(4, 6) = " + s.finddistance(s.root, 4, 6)); 
        System.out.println("Dist(3, 4) = " + s.finddistance(s.root, 3, 4)); 
        System.out.println("Dist(2, 4) = " + s.finddistance(s.root, 2, 4)); 
        System.out.println("Dist(8, 5) = " + s.finddistance(s.root, 8, 5)); 
		
	}
}