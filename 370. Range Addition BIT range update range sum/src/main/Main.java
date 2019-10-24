package main;

/*
 * Assume you have an array of length n initialized with all 0's and are given k update operations.
 * Each operation is represented as a triplet: 
 * [startIndex, endIndex, inc] which increments each element of subarray
 * A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 * Return the modified array after all k operations were executed.
 * 
 *  Given:
    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

	Output:

    [-2, 0, 3, 5, 3]
 */

/* https://kartikkukreja.wordpress.com/2013/12/02/range-updates-with-bit-fenwick-tree/
 * https://cs.stackexchange.com/questions/33014/range-update-range-query-with-binary-indexed-trees
 * Read comments as well
 * https://github.com/manoharreddyporeddy/math-advanced-data-structures-and-algorithms/blob/master/BIT_fenwick_tree_explanation.md
 */

/*
 * second bit is required because whenever range update is performed,
 * sum(int index) becomes point sum i.e bit[index]
 * it is no longer range sum i.e. sum of all elements from 0 to index (use pen and paper)
 * Thus second bit is required to get range sum i.e. sum of all elements from 0 to index
 * To remind sum(i,j) = sum(j) - sum(i) where sum(i) means sum of all elements from 0 to i
 * There are three cases for range sum (i,j):
 * a)index < i: this case there is no effect
 * b) i <= index <= j : sum(index) - sum(i-1)
 * c) j < index <= N sum(N) - sum(j)
 * 
 * The second part of each of above cases is stored in second bit
 * Thus bit2 is updated as
 * update(bit2, i, val * (i-1));
 * update(bit2, j+1, -val*j);
 * 
 * Since after range update sum(i) becomes point sum
 * Thus sum(i) = bit1[i]*i - bit2[i]
 * 
 * https://leetcode.com/problems/range-addition/discuss/84217/Java-O(K-%2B-N)time-complexity-Solution
 * https://leetcode.com/articles/range-addition/
 */

/* Formal Explanation

For each update query (start, end, val)(start,end,val) on the array arrarr, the goal is to achieve the result:

arr_i = arr_i + val forall  i \in [start, end]
Applying the final transformation, ensures two things:

1) It carries over the +val+val increment over to every element arr_i ∀ i ≥ start.
2) It carries over the -val−val increment (equivalently, a +val+val decrement) over to every element arr_j ∀ j > end.

The net result is that:

arr[i] = arr[i] +val ∀i∈[start,end] 
arr[j] = arr[j] + val - val = arr[j] ∀i∈(end,length)
 

which meets our end goal. It is easy to see that the updates over a range did not carry over beyond it due to the 
compensating effect of the -val−val increment over the +val+val increment.

It is good to note that this works for multiple update queries because the particular binary operations here 
(namely addition and subtraction):

are closed over the entire domain of Integers. (A counter example is division which is not closed over all Integers).

are complementary operations. (As a counter example multiplication and division are not always complimentary due to 
possible loss of precision when dividing Integers).

Final Transformation. The cumulative sum of the entire array is taken (0 - based indexing)

arr[i] = arr[i] + arr[i-1]  ∀ i ∈ [1,n)

Complexity Analysis

Time complexity : O(n + k)O(n+k). Each of the kk update operations is done in constant O(1)O(1) time. 
The final cumulative sum transformation takes O(n)O(n) time always.

Space complexity : O(1)O(1). No extra space required.
 * 
 */

class Solution {
	
	/* just like BIT, add arr[start] with val and arr[end+1] with -val
	 * at time to get array apply update and return array i.e lazy update
	 */
	
	public int[] getModifiedArray(int length, int[][] updates) {
		int []res = new int[length];
		for(int []u : updates) {
			int start = u[0];
			int end = u[1];
			int val = u[2];
			res[start] += val;
			
			if(end < length-1) {
				res[end+1] += -val;
			}
		}
		
		int sum = 0;
		for(int i = 0;i < length;i++) {
			sum += res[i];
			res[i] = sum;
		}
		return res;
	}
	
	
	
	// BIT Method
	int []bit1, bit2;
    public int[] getModifiedArray1(int length, int[][] updates) {
    	int []res = new int[length];
    	bit1 = new int[length+1];
    	bit2 = new int[length+1];
    	for(int i = 0;i < updates.length;i++) {
    		updaterange(updates[i][0], updates[i][1], updates[i][2]);
    		/*print(bit1);
    		print(bit2);*/
    	}
    	for(int i = 0;i < res.length;i++) 
    		res[i] = sumrange(i,i);
    	return res;
    }
    
    void updaterange(int i, int j, int val) {
    	update(bit1, i, val);
    	update(bit1, j+1, -val);
    	update(bit2, i, val * (i-1));
    	update(bit2, j+1, -val*j);
    }
    
    void update(int []bit, int i, int val) {
    	i++;
    	while(i < bit.length) {
    		bit[i] += val;
    		i += i & (-i); 
    	}
    }
    
    int sumrange(int i, int j) {
    	return sum(j) - sum(i-1);
    }
    
    int sum(int i) {
    	return getsum(bit1, i) * i - getsum(bit2, i);
    }
    
    int getsum(int[]bit, int i) {
    	i++;
    	int sum = 0;
    	while(i > 0) {
    		sum += bit[i];
    		i -= i & (-i); 
    	}
    	return sum;
    }
    
    void print(int []res) {
    	for(int i : res)
    		System.out.print(i + " ");
    	System.out.println();
    }
    
}

public class Main {
	public static void main(String[] args) {
		int [][] updates = {{1,  3,  2}, {2,  4,  3}, {0,  2, -2}};
		int length = 5;
		int []res = new Solution().getModifiedArray(length, updates);
		for(int i = 0;i < res.length;i++)
			System.out.print(res[i] + " ");
		System.out.println();
	}
}