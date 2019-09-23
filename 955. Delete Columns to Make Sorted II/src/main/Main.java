package main;

/* https://leetcode.com/articles/delete-columns-to-make-sorted-ii/
 * if find sorted column(some may be equal), check right side for deletion
 * if earlier column has some equal elements, then current column should be sorted
 * if current column is strictly increasing return answer, no need to check right side 
 */

class Solution {
    public int minDeletionSize(String[] A) {
    	int ans = 0;
    	int n = A.length, w = A[0].length();
    	boolean []cut = new boolean[n-1];
    	Search :
    	for(int c = 0;c < w;c++) {
    		for(int r = 0;r < n-1;r++) {
    			if(!cut[r] && A[r].charAt(c) > A[r+1].charAt(c)) {
    				ans++;
    				continue Search;
    			}
    		}
    		
    		for(int r = 0;r < n-1;r++) {
    			if(A[r].charAt(c) < A[r+1].charAt(c))
    				cut[r] = true;
    		}
    		
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//String []A = {"ca","bb","ac"};
		//String []A = {"xc","yb","za"};
		//String []A = {"zyx","wvu","tsr"};
		//String []A = {"jwkwdc","etukoz"};
		String []A = {"xga","xfb","yfa"};
		System.out.println(new Solution().minDeletionSize(A));
	}
}
