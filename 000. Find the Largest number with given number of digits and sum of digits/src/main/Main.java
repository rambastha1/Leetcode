package main;

class Solution {
	public int largest(int sum, int digits) {
		if(sum <= 0 || digits <= 0 || (digits == 1 && sum > 9))
			return -1;
		int result = 0;
		for(int i = 0;i < digits;i++) {
			if(sum >= 9) {
				result = result*10 + 9;
				sum -= 9;
			} else {
				result = result*10 + sum;
				sum = 0;
			}
		}
		return result; 
	}
}

public class Main {
	public static void main(String[] args) {
		int sum = 7, digits = 3;
		System.out.println(new Solution().largest(sum, digits));
	}
}