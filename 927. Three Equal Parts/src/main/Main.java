package main;

import java.util.Arrays;
// https://leetcode.com/problems/three-equal-parts/discuss/183922/C%2B%2B-O(n)-time-O(1)-space-12-ms-with-explanation-and-comments
class Solution {
	public int[] threeEqualParts(int[] A) {
        int ones = 0;
        int N = A.length;
        for (int i = 0; i < N; i++) 
            ones += A[i];
        
        if (ones == 0) 
            return new int[]{0, N-1};
        
        if (ones % 3 != 0) 
            return new int[]{-1,-1};
        
        ones /= 3;
        
        int i;
        for (i = 0; i < N; i++) 
            if (A[i] == 1) 
            	break;
        //Found the first 1 of the array.
        int start = i; //[start: ]
        //Find the (k+1)th 1 of the array.
        int count = 0;
        for (i = 0; i < N; i++){
            count += A[i];
            if (count == ones+1) break;
        }
        //[mid: ]
        int mid = i;

        //Find (2k + 1)th '1' in the array
        count = 0;
        
        for (i = 0; i < A.length; i++){
            count += A[i];
            if (count == 2*ones+1) break;
        }
        
       int end = i; //[end: ]
       //Now we have found the beggning of each interval, let's see if we have a match.
        while (end < N && A[start] == A[mid] && A[mid] == A[end]){
            start++;
            mid++;
            end++;
        }
        
        if (end == N) return new int[]{start - 1, mid};
        
        return new int[]{-1,-1};
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {0,0,1,1,1,1,0,1,1,0,0,1,0,0,0,1,0,0,1,1,0,0,1,0,1};
		System.out.println(Arrays.toString(new Solution().threeEqualParts(A)));
	}
}
