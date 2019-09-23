package main;

class Solution {
    public int[] countBits(int num) {
    	int []res = new int[num+1];
    	res[0] = 0;
    	for(int i = 1;i <= num;i++) {
    		int count = 0, temp = i;
    		while(temp > 0) {
    			temp &= (temp-1);
    			count++;
    		}
    		res[i] = count;
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