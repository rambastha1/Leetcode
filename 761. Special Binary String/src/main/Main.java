package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	// https://leetcode.com/problems/special-binary-string/discuss/113212/Think-of-it-as-Valid-Parentheses
	// Time (n^2*lgn) Space 0(N)
    public String makeLargestSpecial(String S) {
    	List<String> list = new ArrayList<>();
    	int s = 0, bal = 0, n = S.length();
    	
    	for(int e = 0;e < n;e++) {
    		char c = S.charAt(e);
    		bal += c =='1'?1:-1;
    		if(bal == 0) {
    			list.add("1" + makeLargestSpecial(S.substring(s+1, e)) + "0");
    			s = e+1;
    		}
    	}
    	
    	Collections.sort(list, Collections.reverseOrder());
    	StringBuilder sb = new StringBuilder();
    	for(String str : list)
    		sb.append(str);
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "11011000";
		System.out.println(new Solution().makeLargestSpecial(S));
	}
}
