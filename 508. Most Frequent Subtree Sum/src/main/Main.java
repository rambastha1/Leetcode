package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	/*
	 * I used PQ but it can be done using maxcount. My code works kudos
	 */
	int maxcount = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
    	if(root == null)
    		return new int[0];
    	
    	Map<Integer, Integer> count_map = new HashMap<>();    	
    	util(root, count_map);
    	List<Integer> res = new ArrayList<>();
    	for(int count : count_map.keySet()) {
    		if(!res.contains(count) && count_map.get(count) == maxcount)
    			res.add(count);
    	}
    	int [] result = new int[res.size()];
        for(int i=0;i<res.size();i++){
            result[i] = res.get(i);
        }
        return result;
    }
    
    int util(TreeNode root, Map<Integer, Integer> count_map) {
    	if(root == null)
    		return 0;
    	int sum = root.val;
    	sum += util(root.left, count_map) + util(root.right, count_map);
    	count_map.put(sum, count_map.getOrDefault(sum, 0)+1);
    	maxcount = Math.max(maxcount, count_map.get(sum));
    	return sum;
    }
    
    public TreeNode root;
    
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.root = s.new TreeNode(4);
		s.root.left = s.new TreeNode(2);
		s.root.right = s.new TreeNode(-3);
		int []res = s.findFrequentTreeSum(s.root);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}