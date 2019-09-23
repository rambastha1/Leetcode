package main;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
    	List<Boolean> res = new ArrayList<>();
    	for(int []query : queries) {
    		if(query[2] >= 13) {
                res.add(true);
                continue;
            }
    		String str = s.substring(query[0], query[1]+1);
    		int count = count(str);
    		boolean val = false;
    		// need to change only half elements
    		if(query[2] >= count/2)
				val = true;
    		res.add(val);
    	}
    	return res;
    }
    
    private int count(String s) {
    	int ans = 0;
    	int []count = new int[26];
    	for(char c : s.toCharArray()) {
    		count[c-'a']++;
    		if((count[c-'a']%2) == 0)
    			ans--;
    		else
    			ans++;
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "abcda";
		int [][]queries = {{3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}};
		System.out.println(new Solution().canMakePaliQueries(s, queries));
	}
}
