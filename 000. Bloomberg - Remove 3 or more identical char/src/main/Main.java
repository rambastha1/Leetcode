package main;

import java.util.Stack;

// similar to 000. Twitter - Remove Substring Recursively
/* Given a string, reduce the string by removing 3 or more consecutive identical characters. 
 * You should greedily remove characters from left to right.

Example 1:

Input: "aaabbbc"
Output: "c"
Explanation:
1. Remove 3 'a': "aaabbbc" => "bbbc"
2. Remove 3 'b': "bbbc" => "c"
Example 2:

Input: "aabbbacd"
Output: "cd"
Explanation:
1. Remove 3 'b': "aabbbacd" => "aaacd"
2. Remove 3 'a': "aaacd" => "cd"
Example 3:

Input: "aabbccddeeedcba"
Output: ""
Explanation:
1. Remove 3 'e': "aabbccddeeedcba" => "aabbccdddcba"
2. Remove 3 'd': "aabbccdddcba" => "aabbcccba"
3. Remove 3 'c': "aabbcccba" => "aabbba"
4. Remove 3 'b': "aabbba" => "aaa"
5. Remove 3 'a': "aaa" => ""
Example 4:

Input: "aaabbbacd"
Output: "acd"
Explanation:
1. Remove 3 'a': "aaabbbacd" => "bbbacd"
2. Remove 3 'b': "bbbacd" => "acd"
 * 
 */

/* keep pushing char to stack till top three of stack is same character, start popping then
 * 
 */
class Solution {
	
	class Node {
		char val;
		int occurence;
		public Node(char c) {
			this.val = c;
			this.occurence = 1;
		}
	}
	
	public String smallest(String str) {
		Stack<Node> stack = new Stack<>();
		if(str.length() < 3)
			return str;
		stack.push(new Node(str.charAt(0)));
		int n = str.length();
		for(int i = 1;i < n;i++) {
			char prev = stack.isEmpty()?' ':stack.peek().val;
			char curr = str.charAt(i);
			char next = i == n-1?' ':str.charAt(i+1);
			
			Node node = new Node(curr);
			node.occurence = prev==curr?stack.peek().occurence+1:1;
			
			if(node.occurence >= 3 && curr != next) {
				while(!stack.isEmpty() && stack.peek().val == curr) {
					stack.pop();
				}
			} else {
				stack.push(node);
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty())
			sb.append(stack.pop().val);
		return sb.reverse().toString();
	}
}

public class Main {
	public static void main(String[] args) {
		//String str = "aaaaaaaaaabbbbbbbbbbbbbbbbbacd";
		String str = "aabbccddeeedcba";
		System.out.println(new Solution().smallest(str));
	}
}
