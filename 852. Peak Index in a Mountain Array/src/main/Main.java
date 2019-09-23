package main;

class Solution {
    public int peakIndexInMountainArray(int[] A) {
    	int low = 0, high = A.length-1;
		while(low <= high) {
			int mid = low + (high-low)/2;
			if(A[mid] > A[mid+1] && A[mid] > A[mid-1])
				return mid;
			else if(A[mid] > A[mid-1] && A[mid] < A[mid+1])
				low = mid;
			else if(A[mid] < A[mid-1] && A[mid] > A[mid+1])
				high = mid;
		}
		return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {0,1,0};
		int []A = {0,2,1,0};
		System.out.println(new Solution().peakIndexInMountainArray(A));
	}
}
