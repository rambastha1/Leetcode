package main;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/restore-ip-addresses/discuss/30972/WHO-CAN-BEAT-THIS-CODE
class Solution {
	// Time 0(N) -> only string concatenation
    public List<String> restoreIpAddresses(String s) {
    	List<String> res = new ArrayList<>();
    	if(s.length() > 12 || s.length() < 4)
    		return res;
    	StringBuilder sb = new StringBuilder();
    	for(int a = 1;a < 4;a++) {
    		for(int b = 1;b < 4;b++) {
    			for(int c = 1;c < 4;c++) {
    				for(int d = 1;d < 4;d++) {
    					if(a+b+c+d == s.length()) {
    						int n1 = Integer.parseInt(s.substring(0, a));
    						int n2 = Integer.parseInt(s.substring(a, a+b));
    						int n3 = Integer.parseInt(s.substring(a+b, a+b+c));
    						int n4 = Integer.parseInt(s.substring(a+b+c));
    						if(n1 <= 255 && n2 <= 255 && n3 <= 255 && n4 <= 255) {
    							sb.append(String.valueOf(n1)).append(".").append(String.valueOf(n2)).append(".").
    									append(String.valueOf(n3)).append(".").append(String.valueOf(n4));
    							if(sb.length() == s.length()+3)
    								res.add(sb.toString());
    							sb.delete(0, sb.length());
    						}
    					}
    				}
    			}
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "25525511135";
		System.out.println(new Solution().restoreIpAddresses(s));
	}
}
