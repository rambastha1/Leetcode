package main;

import java.util.Stack;

class Solution {
	public String simplifyPath(String path) {
        if (path == null || path.length() == 0) 
        	return "";
        
        String[] paths = path.split("/");
        Stack<String> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        
        for (String cur : paths) {
            if (cur.equals(".") || cur.equals("")) 
            	continue;
            else if (cur.equals("..")) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else {
                stack.push(cur);
            }
        }
        
        if (stack.empty()) 
        	return "/";
        for (String str : stack) {
            res.append("/" + str);
        }
        return res.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		//String path = "/a/../../b/../c//.//";
		String path = "/a//b////c/d//././/..";
		System.out.println(new Solution().simplifyPath(path));
	}
}
