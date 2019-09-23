package main;

/* A full binary tree is a tree in which every node other than leaves have 2 children.
 * Let T be a nonempty, full binary tree Then:
 * (a) Full binary tree always has odd number of nodes
 * (b) If T has I internal nodes, the number of leaves is L = I + 1.
 * (c) If T has I internal nodes, the total number of nodes is N = 2I + 1.
 * (d) If T has a total of N nodes, the number of internal nodes is I = (N – 1)/2.
 * (e) If T has a total of N nodes, the number of leaves is L = (N + 1)/2.
 * (f) If T has L leaves, the total number of nodes is N = 2L – 1.
 * (g) If T has L leaves, the number of internal nodes is I = L – 1.
 * (h) I is always odd N is always odd L is even
 * (i) At every level K, maximum number of nodes are 2^k
 * (j) with level L, maximum number of nodes are 2^L-1 (-1 because root level or 0 level has only 1 node)
 * 
 * A complete binary tree is a tree in which every level except possibly the last is completely filled
 * and is as left as possible - heap
 * A complete binary tree has 2^k nodes at every depth k < n and between 2^n and 2^(n+1)-1 nodes 
 * altogether. It can be efficiently implemented as an array, where a node at index i has 
 * children at indexes 2i and 2i+1 and a parent at index i/2, with 1-based indexing. 
 * If child index is greater than the number of nodes, the child does not exist.
 */

/* https://stackoverflow.com/questions/31172940/counting-number-of-nodes-in-a-complete-binary-tree
 * 
 */

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	// Time 0(lg^2(N)) Space 0(N)
    public int countNodes(TreeNode root) {
    	if(root == null)
    		return 0;
    	// it takes 0(lgN) and h = lgN thus 0(lg^2(N)) 
    	int lh = leftheight(root);
    	int rh = rightheight(root);
    	if(lh == rh)
    		return (1<<lh) -1;
    	// If its not full binary tree recursive left and right
    	// once it finds full binary tree, it returns (1<<lh) -1
    	
    	return countNodes(root.left) + countNodes(root.right) + 1;
    }
    
    private int leftheight(TreeNode root) {
    	int h = 0;
    	if(root == null)
    		return h;
    	while(root != null) {
    		root = root.left;
    		h++;
    	}
    	return h;
    }
    
    private int rightheight(TreeNode root) {
    	int h = 0;
    	if(root == null)
    		return h;
    	while(root != null) {
    		root = root.right;
    		h++;
    	}
    	return h;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
