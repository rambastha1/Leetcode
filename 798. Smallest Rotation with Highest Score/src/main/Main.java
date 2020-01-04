package main;
/* https://leetcode.com/problems/smallest-rotation-with-highest-score/discuss/118725/C%2B%2BJavaPython-Solution-with-Explanation
 * (i-A[i]+n)%n is the index till A[i] will contribute a point
 * from ((i-A[i]+n)%n lose points
 * 
 * https://leetcode.com/articles/smallest-rotation-with-highest-score/
 * If we are trying to plot an interval like [2, 3, 4], then instead of doing bad[2]--; bad[3]--; bad[4]--;, 
 * what we will do instead is keep track of the cumulative total: bad[2]--; bad[5]++. 
 * For "wrap-around" intervals like [8, 9, 0, 1, 2], we will keep track of this as two separate intervals: bad[8]--, 
 * bad[10]++, bad[0]--, bad[3]++. (Actually, because of our implementation, we don't need to remember the bad[10]++ part.)
 * 
 * https://leetcode.com/problems/smallest-rotation-with-highest-score/discuss/118839/Simple-Java-O(n)-Solution
 */
class Solution {
    public int bestRotation(int[] A) {
    	int n = A.length;
    	int []arr = new int[n];
    	for(int i = 0;i < n;i++) {
    		arr[(i+1)%n]++;
    		arr[(i-A[i]+n+1)%n]--;
    	}
    	
    	int count = 0, max = -1, res = -1;
    	for(int i = 0;i < n;i++) {
    		count += arr[i];
    		if(count > max) {
    			res = i;
    			max = count;
    		}
    	}
    	return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {1,3,0,2,4};
		System.out.println(new Solution().bestRotation(A));
	}
}
