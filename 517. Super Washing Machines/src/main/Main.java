package main;

/* https://leetcode.com/problems/super-washing-machines/discuss/99181/C%2B%2B-16ms-O(n)-solution-(with-trivial-proof)
 * 
 * First we check the sum of dresses in all machines. if that number cannot be divided by count of machines, there is no solution.

Otherwise, we can always transfer a dress from one machine to another, one at a time until every machines reach the same number, 
so there must be a solution. In this way, the total actions is sum of operations on every machine.

Since we can operate several machines at the same time, the minium number of moves is the maximum number of necessary operations 
on every machine.

For a single machine, necessary operations is to transfer dresses from one side to another until sum of both sides and itself 
reaches the average number. We can calculate (required dresses) - (contained dresses) of each side as L and R:

L > 0 && R > 0: both sides lacks dresses, and we can only export one dress from current machines at a time, so result is abs(L) + abs(R)
L < 0 && R < 0: both sides contains too many dresses, and we can import dresses from both sides at the same time, so result is 
max(abs(L), abs(R))
L < 0 && R > 0 or L >0 && R < 0: the side with a larger absolute value will import/export its extra dresses from/to current 
machine or other side, so result is max(abs(L), abs(R))

For example, [1, 0, 5], average is 2
for 1, L = 0 * 2 - 0 = 0, R = 2 * 2 - 5= -1, result = 1
for 0, L = 1 * 2 - 1= 1, R = 1 * 2 - 5 = -3, result = 3
for 5, L = 2 * 2 - 1= 3, R = 0 * 2 - 0= 0, result = 3
so minium moves is 3
 * 
 * 
 * for machines[i]
left side sum is sum[i], required number is i * avg, so l = i * avg - sum[i]
right side sum is (sum[len] - sum[i] - machines[i]), sum[len] is total sum, sum[i] is left side sum, machines[i] is 
current machine, and required number is (len - i - 1) * avg, so r = (len - i - 1) * avg - (sum[len] - sum[i] - machines[i])
 */

class Solution {
	// 0(n) time and space
    public int findMinMoves(int[] machines) {
    	int n = machines.length;
    	int []pre = new int[n+1];
    	for(int i = 0;i < n;i++)
    		pre[i+1] = pre[i] + machines[i]; 
    	
    	if(pre[n]%n != 0)
    		return -1;
    	
    	int avg = pre[n]/n;
    	int ans = 0;
    	for(int i = 0;i < n;i++) {
    		int l = (i*avg) - pre[i];
    		int r = (n - i - 1)*avg - (pre[n] - pre[i] - machines[i]);
    		if(l > 0 && r > 0)
    			ans = Math.max(ans, l + r);
    		else
    			ans = Math.max(ans, Math.max(l, r));
     	}
    	return ans;
    }
    
    //0(n) time and 0(1) space
    // https://leetcode.com/problems/super-washing-machines/discuss/99185/Super-Short-and-Easy-Java-O(n)-Solution
    public int findMinMoves1(int[] machines) {
        int sum = 0;
        for(int m:machines){
            sum += m;
        }
        if(sum % machines.length != 0) return -1;
        int avg = sum/machines.length;
        
        int min = 0, cur = 0;        
        for(int m:machines){
            int diff = m - avg;
            min = Math.max(min, diff);
            cur += diff;            
            min = Math.max(min, Math.abs(cur));
        }
        return min;
    }
}

public class Main {
	public static void main(String[] args) {
		int[] machines = {8,0,0,8};
		System.out.println(new Solution().findMinMoves(machines));
		System.out.println(new Solution().findMinMoves1(machines));
	}
}
