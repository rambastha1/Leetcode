package main;

import java.util.Arrays;

class Solution {
	
	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
		int root = -1, edges = 0;
		int []parent = new int[leftChild.length];
		Arrays.fill(parent, -1);
		for(int i = 0;i < leftChild.length;i++) {
			if(leftChild[i] != -1) {
				edges++;
				if(parent[leftChild[i]] != -1)
					return false;
				parent[leftChild[i]] = i;
			}
		}
		
		for(int i = 0;i < leftChild.length;i++) {
			if(rightChild[i] != -1) {
				edges++;
				if(parent[rightChild[i]] != -1 && parent[rightChild[i]] != i)
					return false;
				parent[rightChild[i]] = i;
			}
		}
		
		for(int i = 0;i < leftChild.length;i++) {
			if(parent[i] == -1) {
				if(root == -1)
					root = i;
				else
					return false;
			} 
		}
		
		if(root == -1)
			return false;
		
		return edges == leftChild.length-1;
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 4;
		int []leftChild = {1,-1,3,-1}, rightChild = {2,-1,-1,-1};
		System.out.println(new Solution().validateBinaryTreeNodes(n, leftChild, rightChild));
	}
}
