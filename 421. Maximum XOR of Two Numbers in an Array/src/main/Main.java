package main;

/* https://threads-iiith.quora.com/Tutorial-on-Trie-and-example-problems
 * integers are 32 bits
 * why Loop from 31 to 0?
 * Since we want to maximize the xor. If two numbers have different bits at position 31, 
 * then their xor will be greater than numbers that have similar bits at position 31 but different bits
 * at position 0
 * 
 * at every bit we are trying to see if there is any flip in the bit in the other num as 0^1 = 1. 
 * As soon as we find a flip in the numbers we diverge the branch as we are moving from MSB to LSB to
 * maximize the sum. We are going here greedy to maximize the sum. 
 *   
 * So in the first nested for loops we created the trie of the bit representation of our input.
 * In the second nested for loop, we take each number in our input and go through the trie to find the
 * highest XOR we can make with this number. For each number in our input we are going through each of
 * its bits (curBit).
 * 
 * When you XOR two numbers you have to XOR each of their corresponding bits together. An XOR produces
 * 1 when the two bits are different and produces 0 when the bits are the same. 
 * if(curNode.children[curBit ^ 1] != null) is checking if the current node has a child with 
 * the opposite bit of curBit. If it doesn't then at
 * this current bit we cannot gain anything (as you can see in the else case curSum does not change). 
 * But if the current node has a child with the opposite bit, then it means that there exists at least
 * another number in our input array with a different corresponding bit. Therefore, our curSum can
 * increase by (1 << i) as we gain (1 << i) if we XOR the current number with that number.
 *   
 */

//Time 0(NlgN) Space(N*lgN*2)
class Solution {
	
	class TrieNode {
		TrieNode []children;
		int val;
		boolean isend;
		public TrieNode() {
			children = new TrieNode[2];
		}
	}
	
	public TrieNode root = new TrieNode();
	
	public int findMaximumXOR(int[] nums) {
    	//insert(nums);
    	if(nums == null || nums.length == 0)
    		return 0;
    	int max = 0;
    	for(int num : nums) {
    		TrieNode node = root, complement = root;
    		int sum = 0;
    		for(int i = 31;i >= 0;i--) {
    			//?
    			int bit = (num >>> i) & 1;
    			
    			//Insertion Part
    			if(node.children[bit] == null )
    				node.children[bit] = new TrieNode();
    			
    			node = node.children[bit];
    			
    			//Sum Part
    			if(complement.children[bit ^ 1] != null) {
    				complement = complement.children[bit ^ 1];
    				sum += (1 << i);
    			} else
    				complement = complement.children[bit];
    		}
    		max = Math.max(max, sum);
    	}
    	return max;
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {3, 10, 5, 25, 2, 8};
		System.out.println(new Solution().findMaximumXOR(nums));
	}
}