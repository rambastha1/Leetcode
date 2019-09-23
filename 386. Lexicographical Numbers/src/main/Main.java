package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
	
	//100 before 11
	// Time 0(N)
	public List<Integer> lexicalOrder(int n) {
		List<Integer> res = new ArrayList<>();
		for(int i = 1;i < 10;i++)
			dfs(res, n, i);
		return res;
	}
	
	void dfs(List<Integer> res, int n, int curr) {
		if(curr > n)
			return;
		res.add(curr);
		for(int i = 0;i < 10;i++) {
			if(10*curr+i > n)
				break;
			dfs(res, n, 10*curr+i);
		}
	}
	
	//11 before 100
    /*public List<Integer> lexicalOrder(int n) {
    	List<Integer> res = new ArrayList<>();
    	
    	for(int i = 1;i <= 9;i++) {
    		int j = 1;
    		while(j <= n) {
    			for(int m = 0;m < j;m++) {
    				if((m + j *i) <= n)
    					res.add(m+j*i);
    			}
    			j *= 10;
    		}
    	}	
    	return res;
    }*/
}

public class Main {
	public static void main(String[] args) {
		int n = 100;
		System.out.println(new Solution().lexicalOrder(n));
	}
}