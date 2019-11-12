package main;

/* Given an array of integers A, return the largest integer that only occurs once.

If no integer occurs once, return -1.

 

Example 1:

Input: [5,7,3,9,4,9,8,3,1]
Output: 8
Explanation: 
The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it's the answer.
Example 2:

Input: [9,9,8,8]
Output: -1
Explanation: 
There is no number that occurs only once.
 

Note:

1 <= A.length <= 2000
0 <= A[i] <= 1000
 * 
 */

class Solution {
    public int largestUniqueNumber(int[] A) {
    	int n = A.length;
    	if(n == 1)
    		return A[0];
    	
    	int []count = new int[1001];
    	for(int a : A)
    		count[a]++;
    	for(int i = 1000;i >= 0;i--)
    		if(count[i] == 1)
    			return i;
    	return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {5,7,3,9,4,9,8,3,1};
		System.out.println(new Solution().largestUniqueNumber(A));
	}
}