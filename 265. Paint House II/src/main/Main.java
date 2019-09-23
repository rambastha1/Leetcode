package main;

/*
 * There are a row of n houses, each house can be painted with one of the k colors. 
 * The cost of painting each house with a certain color is different. 
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. 
 * For example,costs[0][0] is the cost of painting house 0 with color 0; 
 * costs[1][2] is the cost of painting house 1 with color 2, and so on... 
 * Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */

class Solution {
    /*
     * Time 0(NK) Constant Space
     * 
     */
	public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        //last color is index of min cost color
        int minCost = 0, secondMinCost = 0, lastColor = -1;
        
        for (int[] cost : costs) {
            int curMin = Integer.MAX_VALUE, curSecondMin = Integer.MAX_VALUE, curColor = -1;
            for (int j = 0; j < cost.length; j++) {
                int curCost = cost[j] + (j == lastColor ? secondMinCost : minCost);
                if (curCost < curMin) {
                    curSecondMin = curMin;
                    curMin = curCost;
                    curColor = j;
                } else if (curCost < curSecondMin) {
                    curSecondMin = curCost;
                }
            }
            minCost = curMin;
            secondMinCost = curSecondMin;
            lastColor = curColor;
        }        
        return minCost;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}