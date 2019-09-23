package main;

// https://leetcode.com/problems/string-compression/discuss/92559/Simple-Easy-to-Understand-Java-solution
/* two pointer get the count of char and replace it
 */
class Solution {
    public int compress(char[] chars) {
    	if(chars == null || chars.length == 0)
    		return 0;
    	if(chars.length == 1)
    		return 1;
    	
    	int len = 0;
    	for(int i = 0;i < chars.length;) {
    		chars[len++] = chars[i];
    		int j = i+1;
    		while(j < chars.length && chars[i] == chars[j])
    			j++;
    		if(j - i > 1) {
    			String count = j-i + "";
    			for(char c : count.toCharArray())
    				chars[len++] = c;
    		}
    		i = j;
    	} 
    	return len;
    }
}

public class Main {
	public static void main(String[] args) {
		char []chars = {'a','a','b','b','c','c','c'};
		System.out.println(new Solution().compress(chars));
	}
}