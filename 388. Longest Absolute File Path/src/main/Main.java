package main;

import java.util.Stack;
// 
class Solution {
	public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        String[] arr = input.split("\n");
        int maxLen = 0;
        stack.push(0);
        for (String s: arr) {
        	/*
            numOfTabs is the number of "\t", numOfTabs = 0 
            when "\t" is not found, because s.lastIndexOf("\t") returns -1.
            So normally, the first parent "dir" have numOfTabs 0.
            */
            int numOfTabs = s.lastIndexOf("\t") + 1;
            /* Level is defined as numOfTabs + 1. 
            For example, in "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext", 
            dir is level 1, subdir1 and subdir2 are level 2, file.ext is level3
            */
            int level = numOfTabs + 1;
            /*
            The following part of code is the case that we want to consider when there are
            several sub-directories in a same level. We want to remove
            the path length of the directory or the file of same level
            that we added during previous step, and calculate 
            the path length of current directory or file that we are currently looking at.
            */
            while (level < stack.size()) 
            	stack.pop(); 
            int curLen = stack.peek() + s.length() - numOfTabs + 1;
            stack.push(curLen);
            //Only update the maxLen when a file is discovered, And remove the "/" at the end of file 
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
