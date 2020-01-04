package main;

class Solution {
    public int findKthNumber(int m, int n, int k) {
    	int l = 1, r = m*n;
    	while(l <= r) {
    		int mid = l + (r-l)/2;
    		int count = 0, j = n;
    		for(int i = 1;i <= m;i++) {
    			while(j >= 1 && i*j > mid) {
    				j--;
    			}
    			count += j;
    		}
    	
    		if(count < k)
    			l = mid + 1;
    		else
    			r = mid-1;
    	}
    	return l;
    }
}

public class Main {
	public static void main(String[] args) {
		int m = 3, n = 3, k = 5;
		System.out.println(new Solution().findKthNumber(m, n, k));
	}
}
