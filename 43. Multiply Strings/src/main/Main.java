package main;

// https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
class Solution {
    public String multiply(String num1, String num2) {
    	int m = num1.length(), n = num2.length();
    	int []res = new int[m+n];
    	for(int i = m-1;i >= 0;i--) {
    		for(int j = n-1;j >= 0;j--) {
    			int p1 = i+j, p2 = i+j+1;
    			int mul = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
    			// adds existing value to get overall sum
    			int sum = res[p2] + mul;
    			res[p1] += sum/10;
    			res[p2] = sum%10;
    		}
    	}
    	StringBuilder sb = new StringBuilder();
    	for(int i : res) {
    		// will omit all leading zeroes
    		if(!(sb.length() == 0 && i == 0))
    			sb.append(i);
    	}
    	return sb.length()==0?"0":sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String num1 = "123", num2 = "456";
		System.out.println(new Solution().multiply(num1, num2));
	}
}
