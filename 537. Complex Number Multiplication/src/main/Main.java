package main;

class Solution {
    public String complexNumberMultiply(String a, String b) {
    	int []ab = get(a);
    	int []cd = get(b);
    	int number = ab[0]*cd[0] - ab[1]*cd[1];
    	int complex = ab[0]*cd[1] + ab[1]*cd[0];    	
    	return number + "+" + complex + "i";
    }
    
    public int[] get(String s) {
    	int plus = s.indexOf("+");
    	int i = s.indexOf("i");
    	return new int[] {Integer.parseInt(s.substring(0, plus)), Integer.parseInt(s.substring(plus+1, i))};
    }
}

public class Main {
	public static void main(String[] args) {
		String a = "1+-1i", b = "1+-1i";
		System.out.println(new Solution().complexNumberMultiply(a, b));
	}
}