package main;

class Solution {
    public int[] countBits(int num) {
    	int []res = new int[num+1];
    	for(int i = 1;i <= num;i++) {
    		// remove last number + take mod of last number
    		res[i] = res[i>>1] + i%2;
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int num = 7;
		int []res = new Solution().countBits(num);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}