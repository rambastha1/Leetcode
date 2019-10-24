package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. 
 * Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave 
 * person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), 
 * the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 * 
 * http://www.mathmeth.com/tom/files/settling-debts.pdf
 * https://leetcode.com/problems/optimal-account-balancing/discuss/95355/11-liner-9ms-DFS-solution-(detailed-explanation)
 */

/* With all the given transactions, in the end, each person with ID = id will have an overall balance bal[id]. 
 * Note that the id value or any person coincidentally with 0 balance is irrelevant to debt settling count, 
 * so we can simply use an array debt[] to store all non-zero balances, where

debt[i] > 0 means a person needs to pay $ debt[i] to other person(s);
debt[i] < 0 means a person needs to collect $ debt[i] back from other person(s).
Starting from first debt debt[0], we look for all other debts debt[i] (i>0) which have opposite sign to debt[0]. 
Then each such debt[i] can make one transaction debt[i] += debt[0] to clear the person with debt debt[0]. 
From now on, the person with debt debt[0] is dropped out of the problem and we recursively drop persons one by one 
until everyone's debt is cleared meanwhile updating the minimum number of transactions during DFS.
 * 
 */

class Solution {
	public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] t : transactions) {
            m.put(t[0], m.getOrDefault(t[0], 0) - t[2]);
            m.put(t[1], m.getOrDefault(t[1], 0) + t[2]);
        }
        return settle(0, new ArrayList<>(m.values()));
    }

    int settle(int start, List<Integer> debt) {
        while (start < debt.size() && debt.get(start) == 0)
            start++;
        if (start == debt.size()) 
        	return 0;
        
        int transactions = Integer.MAX_VALUE;
        
        for (int i = start + 1; i < debt.size(); i++) {
        	// means one is lender other is borrower
        	// borrower can give to anyone so find all permutations to get minimum transactions
            if (debt.get(start) * debt.get(i) < 0) {
            	
                debt.set(i, debt.get(i) + debt.get(start));
                transactions = Math.min(transactions, 1 + settle(start + 1, debt));
                debt.set(i, debt.get(i) - debt.get(start));
            }
        }
        return transactions;
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] transactions = {{0,1,10}, {2,0,5}};
		System.out.println(new Solution().minTransfers(transactions));
	}
}