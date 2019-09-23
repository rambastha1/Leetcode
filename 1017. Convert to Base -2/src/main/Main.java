package main;

/*
 * base2 function is quite basis of basis.
 * check last digit, shift to right.
 * base-2 is totally no difference, just add a sign -.
 */

class Solution {
    public String baseNeg2(int N) {
    	StringBuilder res = new StringBuilder();
        while (N != 0) {
            res.append(N & 1);
            //System.out.println(res.toString());
            //This is divide by -2
            N = -(N >> 1);
        }        
        return res.length() > 0 ? res.reverse().toString() : "0";
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 32;
		System.out.println(new Solution().baseNeg2(N));
	}
}