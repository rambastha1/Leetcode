package main;

import java.util.Arrays;

class Solution {
    public String reverseWords(String s) {
    	String []arr = s.split("\\s");
    	for(int i = 0;i < arr.length;i++)
    		arr[i] = reverse(arr[i]);
    	StringBuilder sb = new StringBuilder();
    	for(String str : arr) {
    		sb.append(str);
    		sb.append(" ");
    	}
    	String ans = sb.toString();
    	return ans.trim();
    }
    
    private String reverse(String str) {
    	char []arr = str.toCharArray();
    	int l = 0, r = arr.length-1;
    	while(l < r) {
    		char temp = arr[l];
    		arr[l] = arr[r];
    		arr[r] = temp;
    		l++;
    		r--;
    	}
    	return String.valueOf(arr);
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "Let's take LeetCode contest";
		System.out.println(new Solution().reverseWords(s));
	}
}