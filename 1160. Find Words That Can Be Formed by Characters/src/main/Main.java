package main;

class Solution {
    public int countCharacters(String[] words, String chars) {
    	int ans = 0;
    	
    	int []count = new int[26];
    	for(char c : chars.toCharArray())
    		count[c-'a']++;
    	
    	for(int i = 0;i < words.length;i++) {
    		int []arr = getcount(words[i]);
    		for(int j = 0;j < 26;j++) {
    			if((count[j] == 0 && arr[j] != 0) || count[j] < arr[j])
    				break;
    			if(j == 25)
    				ans += words[i].length();
    		}
    	}
    	return ans;
    }
    
    int []getcount(String word) {
    	int []count = new int[26];
    	for(char c : word.toCharArray())
    		count[c-'a']++;
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		String []words = {"hello","world","leetcode"};
		String chars = "welldonehoneyr";
		System.out.println(new Solution().countCharacters(words, chars));
	}
}
