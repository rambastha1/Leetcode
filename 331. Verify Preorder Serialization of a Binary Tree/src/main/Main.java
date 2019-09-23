package main;

/* https://www.youtube.com/watch?v=_mbnPPHJmTQ
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/discuss/78551/7-lines-Easy-Java-Solution
 * For a full binary tree, # of node = # of edges + 1
 * For a full binary tree, # of leaves = # of nonleaves + 1
 */

class Solution {
	// outgoing, incoming edges
    public boolean isValidSerialization(String preorder) {
    	if(preorder == null)
    		return false;
    	String []str = preorder.split(",");
    	int n = 1;
    	for(int i = 0;i < str.length;i++) {
    		if(--n < 0)
    			return false;
    		if(str[i].compareTo("#") != 0)
    			n += 2;
    	}
    	return n==0;
    }
    
	/* If we treat null's as leaves, then the binary tree will always be full. 
	 * A full binary tree has a good property that # of leaves = # of nonleaves + 1. 
	 * Since we are given a pre-order serialization, we just need to find the shortest prefix of 
	 * the serialization sequence satisfying the property above. If such prefix does not exist, 
	 * then the serialization is definitely invalid; otherwise, the serialization is valid if 
	 * and only if the prefix is the entire sequence.
	 * 
	 */
    public boolean isValidSerialization1(String preorder) {
    	int leaves = 0, nonleaves = 0;
    	String []str = preorder.split(",");
    	for(String s : str) {
    		if(leaves == nonleaves + 1)
    			return false;
    		if(s.compareTo("#") == 0)
    			leaves++;
    		else
    			nonleaves++;
    	}
    	return nonleaves+1==leaves; 
    }
}

public class Main {
	public static void main(String[] args) {
		String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		System.out.println(new Solution().isValidSerialization(preorder));
	}
}
