package main;

/*
 * 1,n,2,n-1,3,n-2,4... ==> Diff: n-1, n-2, n-3, n-4, n-5...
 * By following this pattern, k numbers will have k-1 distinct difference values;
 * and all the rest numbers should have |ai - a_i-1| = 1;
 * In total, we will have k-1+1 = k distinct values.
 */

class Solution {
	public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        for (int i = 0, l = 1, r = n; l <= r; i++)
        	//Till k > 1 (also mean k-1 distinct) it will use this then incremental with diff = 1
            res[i] = k > 1 ? (k-- % 2 != 0 ? l++ : r--) : l++;
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 9, k = 8;
		int []res = new Solution().constructArray(n, k);
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}