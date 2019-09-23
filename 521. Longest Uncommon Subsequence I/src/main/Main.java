package main;

class Solution {
	public int findLUSlength(String a, String b) {
    	if(a == null || b == null || (a.length() == 0 && b.length() == 0) || a.compareTo(b) == 0)
    		return -1;
    	int m = a.length(), n = b.length();
    	return m>n?m:n;
    }
}

public class Main {
	public static void main(String[] args) {
		String a = "aefawfawfawfaw", b = "aefawfeawfwafwaef";
		System.out.println(new Solution().findLUSlength(a, b));
	}
}
