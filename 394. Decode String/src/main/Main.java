package main;

import java.util.Stack;

class Solution {
	
	// Time 0(N) Space 0(N)
    public String decodeString(String s) {
    	if(s == null || s.length() == 0)
    		return "";
    	Stack<String> res = new Stack<>();
    	Stack<Integer> stack = new Stack<>();
    	int i = 0;
    	String ans = "";
    	while(i < s.length()) {
    		if(Character.isDigit(s.charAt(i))) {
    			int count = 0;
    			while(Character.isDigit(s.charAt(i))) {
    				count = count*10 + (s.charAt(i)- '0');
    				i++;
    			}
    			stack.push(count);
    		} else if(s.charAt(i) == '[') {
    			res.push(ans);
    			ans = "";
    			i++;
    		} else if(s.charAt(i) == ']') {
    			StringBuilder sb = new StringBuilder(res.pop());
    			int repeat = stack.pop();
    			for(int j = 0;j < repeat;j++)
    				sb.append(ans);
    			ans = sb.toString();
    			i++;
    		} else {
    			ans += s.charAt(i++); 
    		}
    	}    	
		return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "3[a2[c]]";
		System.out.println(new Solution().decodeString(s));
	}
}