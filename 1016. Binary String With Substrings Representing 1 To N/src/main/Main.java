package main;

class Solution {
    public boolean queryString(String S, int N) {
    	for(int i = 1;i <= N;i++) {
    		if(!S.contains(Integer.toBinaryString(i)))
    			return false;
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "0110";
		int N = 4;
		System.out.println(new Solution().queryString(S, N));
	}
}