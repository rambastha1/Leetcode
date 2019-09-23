package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/articles/word-subsets/


class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {
    	List<String> res = new ArrayList<>();
    	if(A == null || A.length == 0 || B == null || B.length == 0)
    		return res;
    	int []bmax = new int[26];
    	for(String str : B) {
    		int []count = count(str);
    		for(int i = 0;i < 25;i++)
    			bmax[i] = Math.max(bmax[i], count[i]);
    	}
    	
    	Loop:
    	for(String str : A) {
    		int []count = count(str);
    		for(int i = 0;i < 25;i++) {
    			if(bmax[i] > count[i])
    				continue Loop;
    		}
    		res.add(str);
    	}
    	return res;
    }
    
    int []count(String b) {
    	int []ans = new int[26];
    	for(char c : b.toCharArray())
    		ans[c-'a']++;
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String []A = {"amazon","apple","facebook","google","leetcode"}, B = {"ec","oc","ceo"};
		System.out.println(new Solution().wordSubsets(A, B));
	}
}