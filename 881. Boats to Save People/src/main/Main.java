package main;

import java.util.Arrays;

class Solution {
    
	// Bucket Sort and two pointers 0(N) 
	public int numRescueBoats(int[] people, int limit) {
		if(people.length == 1)
    		return 1;
		int []bucket = new int[limit+1];
		for(int p : people)
			bucket[p]++;
		
		int l = 0, r = limit-1;
		// transport in size limit only
		int ans = bucket[limit];
		while(l <= r) {
			while(l <=r && bucket[l] <= 0)
				l++;
			while(l <= r && bucket[r] <= 0)
				r--;
			if(l > r) break;
			
			if(l + r <= limit) {
				bucket[l]--;
				bucket[r]--;
			} else
				bucket[r]--;
			ans++;
		}
		return ans;
	}
	
	// Normal Sort and two pointers 0(NlgN)
	public int numRescueBoats1(int[] people, int limit) {
    	if(people.length == 1)
    		return 1;
    	Arrays.sort(people);
    	int n = people.length;
    	
    	int count = 0, curr = 0;
		int l = 0, r = n-1;
		while(l <= r) {
			if(people[l] + people[r] <= limit) {
				l++;
				r--;
			} else 
				r--;
			count++;
		}
		/*if(l == r)
			return count+1;*/
		return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int []people = {3,5,3,4};
		int limit = 5;
		
		/*int []people = {3,2,2,1};
		int limit = 3;*/
		
		/*int []people = {1,2};
		int limit = 3;*/
		
		/*int []people = {5,1,4,2};
		int limit = 6;*/
		
		System.out.println(new Solution().numRescueBoats(people, limit));
	}
}
