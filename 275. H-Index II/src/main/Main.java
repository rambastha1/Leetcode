package main;

class Solution {
    public int hIndex(int[] citations) {
    	if(citations.length == 0)
            return 0;
    	int l = 0, r = citations.length-1, n = citations.length;
    	while(l <= r) {
    		int m = l + (r-l)/2;
    		if(citations[m] >= n-m)
    			r = m-1;
    		else
    			l = m+1;
    	}
    	return citations.length-l;
    }
}

public class Main {
	public static void main(String[] args) {
		int []citations = {0,1,3,5,6};
		System.out.println(new Solution().hIndex(citations));
	}
}
