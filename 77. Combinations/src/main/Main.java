package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
    	int []arr = new int[n];
    	for(int i = 0;i < arr.length;i++)
    		arr[i] = i+1;
    	List<List<Integer>> res = new ArrayList<>();
    	dfs(arr, res, new ArrayList<>(), k, 0);    	
    	return res;
    }
    
    void dfs(int []arr, List<List<Integer>> res, List<Integer> curr, int k, int index) {
    	if(index > arr.length)
    		return;
    	
    	if(curr.size() == k) {
    		res.add(new ArrayList<>(curr));
    		return;
    	}
    	
    	for(int i = index;i < arr.length;i++) {
    		if(curr.contains(arr[i]))
    			continue;
    		curr.add(arr[i]);
    		dfs(arr, res, curr, k, i+1);
    		curr.remove(curr.size()-1);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 4, k = 2;
		System.out.println(new Solution().combine(n, k));
	}
}