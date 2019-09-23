package main;

import java.util.Stack;

class Solution {
	public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        String[] arr = input.split("\n");
        int maxLen = 0;
        stack.push(0);
        for (String s: arr) {
            int numOfTabs = s.lastIndexOf("\t") + 1;
            int level = numOfTabs + 1;
            while (level < stack.size()) 
            	stack.pop(); 
            int curLen = stack.peek() + s.length() - numOfTabs + 1;
            stack.push(curLen);
            if (s.contains(".")) 
            	maxLen = Math.max(maxLen, curLen - 1);
        }
        return maxLen;
    }
}

public class Main {
	public static void main(String[] args) {
		String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		System.out.println(new Solution().lengthLongestPath(input));
	}
}
