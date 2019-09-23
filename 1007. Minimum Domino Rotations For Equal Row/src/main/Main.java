package main;

// https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/discuss/252633/Java-one-pass-counting-O(A-%2B-B)

class Solution {
	
	public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        for (int i = 0, a = 0, b = 0; i < n && (A[i] == A[0] || B[i] == A[0]); ++i) {
            if (A[i] != A[0]) a++;
            if (B[i] != A[0]) b++;
            if (i == n - 1) return Math.min(a, b);
        }
        for (int i = 0, a = 0, b = 0; i < n && (A[i] == B[0] || B[i] == B[0]); ++i) {
            if (A[i] != B[0]) a++;
            if (B[i] != B[0]) b++;
            if (i == n - 1) return Math.min(a, b);
        }
        return -1;
    }
	
    
	public int minDominoRotations1(int[] A, int[] B) {
    	if(A.length != B.length)
    		return -1;
    	int []countA = new int[7], countB = new int[7], same = new int[7];
    	for(int i = 0;i < A.length;i++) {
    		countA[A[i]]++;
    		countB[B[i]]++;
    		if(A[i] == B[i])
    			same[A[i]]++;
    	}
    	
    	int count = 0;
    	for(int i = 1;i < 7;i++) {
    		// A Union B = A + B - A&B 
    		if(countA[i] + countB[i] - same[i] >= A.length)
    			return Math.min(countA[i], countB[i]) - same[i];
    	}
    	return -1;
    }
    
}

public class Main {
	public static void main(String[] args) {
		int []A = {2,1,2,4,2,2}, B = {5,2,6,2,3,2};
		//int []A = {1,2,3,4,6}, B = {6,6,6,6,5};
		//int []A = {1,2,1,1,1,2,2,2}, B = {2,1,2,2,2,2,2,2};
		System.out.println(new Solution().minDominoRotations(A, B));
	}
}