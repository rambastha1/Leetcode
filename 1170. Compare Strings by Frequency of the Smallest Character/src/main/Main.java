package main;

import java.util.Arrays;

class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
    	int m = queries.length, n = words.length;
    	int []arr = new int[n];
     	for(int i = 0;i < n;i++)
     		arr[i] = count(words[i]);
     	Arrays.sort(arr);
     	
     	int []res = new int[m];
     	for(int i = 0;i < m;i++) {
     		int func = count(queries[i]);
     		int l = 0, r = n-1;
     		while(l <= r) {
     			int mid = l + (r-l)/2;
     			if(arr[mid] <= func)
     				l = mid+1;
     			else
     				r = mid-1;
     		}
     		res[i] = n - l;
     	}
    	return res;
    }
    
    private int count(String str) {
    	if(str.length() == 0)
    		return 0;
    	int count = 1;
    	char min = str.charAt(0);
    	for(int i = 1;i < str.length();i++) {
    		char c = str.charAt(i);
    		if(c < min) {
    			min = c;
    			count = 1;
    		} else if(c == min)
    			count++;
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		String []queries = {"bbb","cc"}, words = {"a","aa","aaa","aaaa"};
		System.out.println(Arrays.toString(new Solution().numSmallerByFrequency(queries, words)));
	}
}
