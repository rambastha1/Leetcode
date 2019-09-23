package main;

import java.util.Random;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */

// https://leetcode.com/problems/implement-rand10-using-rand7/discuss/150301/Three-line-Java-solution-the-idea-can-be-generalized-to-%22Implement-RandM()-Using-RandN()%22
/* 
 * The total number of iterations follows geometric distribution. For each iteration in the while loop,
 * the probabilty of exiting the loop is p = 40/49. So the average time complexity 
 * T(n) = O(1/p) = O(49/40) = O(1).
 * 7 * (rand7() - 1) + (rand7() - 1) calls rand7() twice, it generates a uniform random integer 
 * in range of [0, 48], which is rand49() - 1.
 */
class SolBase {
	public int rand7() {
		return new Random().nextInt(8);
	}
}

class Solution extends SolBase {
    public int rand10() {
    	int res = 45;
    	while(res >= 40) {
    		res = 7 *(rand7()-1) + (rand7()-1);
    	}
        return res%10+1;
    }
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		int n = 3;
		for(int i = 0;i < n;i++) {
			System.out.print(s.rand10() + " ");
		}
		System.out.println();
	}
}
