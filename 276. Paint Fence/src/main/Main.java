package main;

// https://leetcode.com/problems/paint-fence/discuss/71156/O(n)-time-java-solution-O(1)-space
class Solution {
    public int numWays(int n, int k) {
    	if(n <= 0 || k <= 0)
    		return 0;
    	if(n == 1)
    		return k;
    	
    	int diff = k*(k-1), same = k;
    	for(int i = 3;i <= n;i++) {
    		int temp = diff;
    		diff = (same + diff)*(k-1);
    		same = temp;
    	}
    	return same+diff;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 3, k = 2;
		System.out.println(new Solution().numWays(n, k));
	}
}