package main;

// https://leetcode.com/problems/reverse-words-in-a-string/discuss/47706/My-accepted-Java-solution
class Solution {
    public String reverseWords(String s) {
    	String []strs = s.split("\\s+");
    	print(strs);
    	
    	StringBuilder sb = new StringBuilder();
    	// It reverses the string
    	for(int i = 1;i <= strs.length;i++) {
    		if(strs[strs.length-i].equals(""))
    			continue;
    		sb.append(strs[strs.length - i] + " ");
    	}
    	return sb.toString().trim();
    }
    
    void print(String []strs) {
    	System.out.println("printing");
    	for(String str : strs)
    		System.out.print(str + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		//String s = "the sky is blue ";
		String s = "  hello world!  ";
		System.out.println(new Solution().reverseWords(s));
	}
}
