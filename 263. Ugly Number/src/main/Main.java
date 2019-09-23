package main;

class Solution {
    public boolean isUgly(int num) {
		for(int i = 2;i < 6;i++) {
			while(num%i==0)
				num /= i;
		}    	
    	return num==1;
    }
}

public class Main {
	public static void main(String[] args) {
		int num = 20;
		System.out.println(new Solution().isUgly(num));
	}
}
