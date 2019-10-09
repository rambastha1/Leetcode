package main;

class Solution {
    public boolean isPalindrome(int x) {
    	if(x < 0)
    		return false;
    	if(x == 0)
    		return true;
    	int orig = x, rev = 0;
    	while(x > 0) {
    		rev = rev*10 + (x%10);
    		x /= 10;
    	}
    	return orig == rev;
    }
}

public class Main {
	public static void main(String[] args) {
		int x = 121;
		System.out.println(new Solution().isPalindrome(x));
	}
}