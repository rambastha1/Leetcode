package main;

import java.util.Arrays;

class Solution {
    public int maxNumberOfApples(int[] arr) {
    	Arrays.sort(arr);
    	int weight = 0, count = 0;
    	for(int a : arr) {
    		if(weight + a <= 5000) {
    			weight += a;
    			count++;
    		} else
    			break;
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []arr = {100,200,150,1000};
		int []arr = {900,950,800,1000,700,800};
		System.out.println(new Solution().maxNumberOfApples(arr));
	}
}