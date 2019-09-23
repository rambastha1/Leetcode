package main;

// https://leetcode.com/problems/h-index/discuss/70768/Java-bucket-sort-O(n)-solution-with-detail-explanation
class Solution {
    public int hIndex(int[] citations) {
    	int n = citations.length;
    	int []bucket = new int[n+1];
    	for(int c : citations) {
    		if(c >= n)
    			bucket[n]++;
    		else
    			bucket[c]++;
    	}
    	
    	int count = 0;
    	for(int i = n;i >= 0;i--) {
    		count += bucket[i];
    		if(count >= i)
    			return i;
    	}
    	return 0;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []citations = {3,0,6,1,5};
		int []citations = {5,5,5,5,5};
		System.out.println(new Solution().hIndex(citations));
	}
}
