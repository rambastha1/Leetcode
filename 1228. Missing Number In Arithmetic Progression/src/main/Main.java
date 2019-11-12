package main;

/* In some array arr, the values were in arithmetic progression: the values arr[i+1] - arr[i] are all equal for 
 * every 0 <= i < arr.length - 1.

Then, a value from arr was removed that was not the first or last value in the array.

Return the removed value.

 

Example 1:

Input: arr = [5,7,11,13]
Output: 9
Explanation: The previous array was [5,7,9,11,13].
Example 2:

Input: arr = [15,13,12]
Output: 14
Explanation: The previous array was [15,14,13,12].
 

Constraints:

3 <= arr.length <= 1000
0 <= arr[i] <= 10^5

https://leetcode.com/problems/missing-number-in-arithmetic-progression/discuss/408474/JavaC%2B%2BPython-Arithmetic-Sum-and-Binary-Search
 * 
 */

class Solution {
	// 0(lgN) A[d] = A[0] + (n-1)diff of AP
    public int missingNumber(int[] arr) {
    	int n = arr.length;
    	int l = 0, r = n, diff = (arr[n-1] - arr[0])/n;;
    	while(l < r) {
    		int m = l + (r-l)/2;
    		if(arr[m] == arr[0] + m*diff)
    			l = m+1;
    		else
    			r = m;
    	}
    	return arr[0] + diff*l;
    }
    
    // 0(N) solution
    // sum of AP series = n/2(first term + last term)
    public int missingNumber1(int[] arr) {
    	int sum = 0, n = arr.length;
    	for(int a : arr)
    		sum += a;
    	return (arr[0] + arr[n-1])*(n+1)/2 - sum;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []arr = {5,7,11,13};
		int []arr = {15,13,12};
		System.out.println(new Solution().missingNumber(arr));
		System.out.println(new Solution().missingNumber1(arr));
	}
}