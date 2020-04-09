package main;
/* Condition 1. odd characters <= k
 * we need odd number of palindromes to make, odd > k we can adjust in any of k palindromes as the middle character 
 * 
 * Condition 2. k <= s.length() cannot divide into more than length
 */
class Solution {
    public boolean canConstruct(String s, int k) {
    	if(k > s.length())
    		return false;
    	int odd = 0;
    	int []arr =  new int[26];
    	for(char c : s.toCharArray()) {
    		arr[c-'a']++;
    	}
    	
    	for(int i = 0;i < 26;i++) 
    		odd += arr[i]%2 == 1?1:0;
    	return odd <= k;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "annabelle";
		int k = 2;
		System.out.println(new Solution().canConstruct(s, k));
	}
}
