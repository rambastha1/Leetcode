package main;

/* About why it is (n-i)*(i+1)
because for ith element to be present in a subarray we can have the starting point from o to i that is (i+1) number of ways
and the ending point must be from i to n-1 that is (n-i) ways
For each pair of starting and ending point there exists an array uniquely
so (n-i)*(i+1) ways
 * 
 * 
 * arr[] = [1, 2, 3], n = 3
All subarrays :  [1], [1, 2], [1, 2, 3], 
                 [2], [2, 3], [3]
here first element 'arr[0]' appears 3 times    
     second element 'arr[1]' appears 4 times  
     third element 'arr[2]' appears 3 times

Every element arr[i] appears in two types of subsets:
i)  In subarrays beginning with arr[i]. There are 
    (n-i) such subsets. For example [2] appears
    in [2] and [2, 3].
ii) In (n-i)*i subarrays where this element is not
    first element. For example [2] appears in 
    [1, 2] and [1, 2, 3].

Total of above (i) and (ii) = (n-i) + (n-i)*i 
                            = (n-i)(i+1)
                                  
For arr[] = {1, 2, 3}, sum of subarrays is:
  arr[0] * ( 0 + 1 ) * ( 3 - 0 ) + 
  arr[1] * ( 1 + 1 ) * ( 3 - 1 ) +
  arr[2] * ( 2 + 1 ) * ( 3 - 2 ) 

= 1*3 + 2*4 + 3*3 
= 20
 */

class Solution {
	public int sum(int []A) {
		int sum = 0, n = A.length;
		for(int i = 0;i < n;i++) {
			sum += A[i] * (i+1) * (n-i);
		}
		return sum;
	}
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,2,3};
		System.out.println(new Solution().sum(A));
	}
}
