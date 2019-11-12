package main;

/* You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.

You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, 
each piece consists of some consecutive chunks.

Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.

Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.

 

Example 1:

Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
Example 2:

Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.
Example 3:

Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 

Constraints:

0 <= K < sweetness.length <= 10^4
1 <= sweetness[i] <= 10^5
 * 
 */

/* Understand why Binary Search will work?
 * mid -> how many pieces
 * count number of pieces by partitioning the array if greater move l to right
 * else move r left
 * we need to find maximum of minimum subarray sum i.e l
 * 
 * Heuristics Nature of Binary search
 * For any given mid, if all curs in for loop are > mid, then left and right boundaries would not converge, 
 * that is, l < r; the while loop would run till mid increases to AT LEAST one value of curs.
 * https://leetcode.com/problems/divide-chocolate/discuss/408454/JavaPython-3-Binary-Search-w-brief-explanation-and-analysis
 * https://leetcode.com/problems/divide-chocolate/discuss/408503/JavaC%2B%2BPython-Binary-Search
 */

class Solution {
	// Time 0(NlgSum) Sum -> sum of sweetness
    public int maximizeSweetness(int[] sweetness, int K) {
    	int sum = 0;
    	for(int a : sweetness)
    		sum += a;
    	
    	int l = 0, r = (int)sum/(K+1);
    	while(l < r) {
    		int mid = l + (r-l+1)/2;
    		int curr = 0, count = 0;
    		for(int a : sweetness) {
    			curr+= a;
    			if(curr >= mid) {
    				curr = 0;
    				count++;
    			}
    		}
    		if(count >= K+1)
    			l = mid;
    		else
    			r = mid - 1;
    	}
    	return l;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []sweetness = {1,2,2,1,2,2,1,2,2};
		int K = 2;*/
		/*int []sweetness = {1,2,3,4,5,6,7,8,9};
		int K = 5;*/
				
		int []sweetness = {52832,63820,96186,1623,88717};
		int K = 3;
		System.out.println(new Solution().maximizeSweetness(sweetness, K));
	}
}