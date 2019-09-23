package main;

// https://leetcode.com/problems/unique-substrings-in-wraparound-string/discuss/95439/Concise-Java-solution-using-DP
// count[index] -> counting for the length of the substring that ends with a character
class Solution {
    public int findSubstringInWraproundString(String p) {
    	if(p == null || p.length() == 0)
    		return 0;
    	if(p.length() == 1)
    		return 1;
    	int n = p.length(), maxlen = 0;
    	int []count = new int[26];
    	for(int i = 0;i < n;i++) {
    		if(i > 0 && (p.charAt(i) - p.charAt(i-1) == 1 || p.charAt(i-1) - p.charAt(i) == 25))
    			maxlen++;
    		else
    			maxlen = 1;
    		int index = p.charAt(i) - 'a';
    		count[index] = Math.max(count[index], maxlen);
    	}
    	
    	int sum = 0;
    	for(int c : count)
    		sum += c;
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {
		String p = "zab";
		//String p = "cac";
		System.out.println(new Solution().findSubstringInWraproundString(p));
	}
}
