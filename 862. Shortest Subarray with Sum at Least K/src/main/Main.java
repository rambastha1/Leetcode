package main;
import java.util.ArrayDeque;
import java.util.Deque;

/* Why keep the deque increase?
A: If B[i] <= B[d.back()] and moreover we already know that i > d.back(), it means that compared with d.back(),
B[i] can help us make the subarray length shorter and sum bigger. So no need to keep d.back() in our deque.

More detailed on this, we always add at the LAST position
B[d.back] <- B[i] <- ... <- B[future id]
B[future id] - B[d.back()] >= k && B[d.back()] >= B[i]
B[future id] - B[i] >= k too

so no need to keep B[d.back()]

https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/189039/Detailed-intuition-behind-Deque-solution
https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque
 * 
 */

class Solution {
	public int shortestSubarray(int[] A, int K) {
        int n = A.length, res = n + 1;
        
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) 
        	pre[i + 1] = pre[i] + A[i];
        // if changed to n -> handle case at the end
        // thus loop one extra time
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n + 1; i++) {
            while (d.size() > 0 && pre[i] - pre[d.getFirst()] >=  K)
                res = Math.min(res, i - d.pollFirst());
            while (d.size() > 0 && pre[i] <= pre[d.getLast()])
                d.pollLast();
            d.addLast(i);
        }
        return res <= n ? res : -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {2,-1,2};
		int K = 3;
		System.out.println(new Solution().shortestSubarray(A, K));
	}
}
