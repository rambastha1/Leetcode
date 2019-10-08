package main;

// https://leetcode.com/problems/filling-bookcase-shelves/discuss/323315/Java-DP-solution
/* dp[i]: the min height for placing first books i - 1 on shelves
 * for every i, loop back till total width <= shelf_width
 * keep track of maximum height, compare dp[i] with all such subproblems
 * 
 * 
 * The key idea of this algorithm goes as follows

Start placing books one by one, always use a new shelve to begin with
After you stored the new height value at this position in your dp array, start looking back at previous books one by one, 
and see while the width permits, how many books you can fit on this new level.
Check to see if bringing said books down reduced the overall height, if it did, update the new loest height value at your dp array.
return the last element of your dp array
 */
class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
    	int n = books.length;
    	int []dp = new int[n+1];
    	dp[0] = 0;
    	for(int i = 1;i <= n;i++) {
    		int width = books[i-1][0];
    		int height = books[i-1][1];
    		dp[i] = dp[i-1] + height;
    		for(int j = i-1;j > 0 && width + books[j-1][0] <= shelf_width;j--) {
    			height = Math.max(height, books[j-1][1]);
    			width += books[j-1][0];
    			dp[i] = Math.min(dp[i], dp[j-1] + height);
    		}
    	}
    	return dp[n];
    }
}

public class Main {
	public static void main(String[] args) {
		int [][]books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
		int shelf_width = 4;
		System.out.println(new Solution().minHeightShelves(books, shelf_width));
	}
}