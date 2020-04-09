package main;

class Solution {
	public int numberOfSubstrings(String s) {
        int st = 0, ans = 0;
        int []count = new int[3];
        for(int e = 0;e < s.length();e++) {
            char c = s.charAt(e);
            count[c-'a']++;
            while(count[0] > 0 && count[1] > 0 && count[2] > 0) {
                char c1 = s.charAt(st);
                count[c1-'a']--;
                st++;
            }
            // explain
            ans += st;
        }
        return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "abcabc";
		System.out.println(new Solution().numberOfSubstrings(s));
	}
}
