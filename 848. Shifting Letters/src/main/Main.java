package main;

class Solution {
    public String shiftingLetters(String S, int[] shifts) {
    	if(S == null || S.length() == 0 || shifts == null || shifts.length == 0)
    		return S;
    	//char []a = S.toCharArray();
    	int n = shifts.length;
    	for(int i = n-2;i >= 0;i--) {
    		shifts[i] = (shifts[i] + shifts[i+1])%26;
    	}
    	StringBuilder sb = new StringBuilder(S);
    	for(int i = 0;i < S.length();i++) {
    		sb.setCharAt(i, (char)((S.charAt(i) - 'a' + shifts[i])%26 + 'a'));
    		//a[i] = (char)((a[i] - 'a' + shifts[i])%26 + 'a');
    	}    	
    	return sb.toString();
    	//return new String(a);
    }
}

public class Main {
	public static void main(String[] args) {
		/*String S = "abc";
		int []shifts = {3,5,9};*/

		String S = "ruu";
		int []shifts = {26,9,17};
		System.out.println(new Solution().shiftingLetters(S, shifts));
	}
}
