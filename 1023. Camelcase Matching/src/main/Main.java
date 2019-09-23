package main;

import java.util.ArrayList;
import java.util.List;

class Solution {
	
	/*
	 * using KMP algorithm
	 * Time 0(queries.len * pattern.len)
	 */
	public List<Boolean> camelMatch(String[] queries, String pattern) {
		List<Boolean> res = new ArrayList<>();
		for(String query : queries) {
			res.add(kmp(query, pattern));
		}
		return res;
	}
	
	boolean kmp(String query, String pattern) {
		int j = 0;
		for(int i = 0;i < query.length();i++) {
			if(j < pattern.length() && query.charAt(i) == pattern.charAt(j))
				j++;
			else if(query.charAt(i) >= 'A' && query.charAt(i) <= 'Z')
				return false;
		}
		return j >= pattern.length();
	}
	
	/*
	 * counting sort passes 35/36 cases
	 * fails if ordering is important as it counts only frequency
	 */
	
    /*public List<Boolean> camelMatch(String[] queries, String pattern) {
    	List<Boolean> res = new ArrayList<>();
    	if(queries == null || pattern == null || queries.length == 0)
    		return res;
    	
    	int []arr = new int[256];
    	for(int i = 0;i < queries.length;i++) {
    		if(isvalid(queries[i], pattern, arr))
    			res.add(true);
    		else
    			res.add(false);
    	}
    	return res;
    }
    
    boolean isvalid(String query, String pattern, int []arr) {
    	if(query == null && pattern == null || query.length() == 0 && pattern.length() == 0)
    		return true;
    	if(query == null || pattern == null || query.length() == 0)
    		return false;
    	Arrays.fill(arr, 0);
    	for(char c : pattern.toCharArray())
    		arr[c]++;
    	for(char c : query.toCharArray()) {
    		arr[c]--;
    		if(c>='A'&& c<='Z') {    			
    			if(arr[c] < 0)
    				return false;
    		}
    	}
    	for(int i = 0;i < arr.length;i++)
			if(arr[i] > 0)
				return false;
    	return true;
    }*/
}

public class Main {
	public static void main(String[] args) {
		//String []queries = {"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
		//String []queries = {"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
		String []queries = {"CompetitiveProgramming","CounterPick","ControlPanel"};
		String pattern = "CooP";//"FB";
		System.out.println(new Solution().camelMatch(queries, pattern));
	}
}