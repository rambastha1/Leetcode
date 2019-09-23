package main;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/different-ways-to-add-parentheses/discuss/66333/Java-recursive-(9ms)-and-dp-(4ms)-solution

/*
 * Works like Matrix Chain Multiplication
 */

class Solution {
	
	public List<Integer> diffWaysToCompute(String input) {
    	List<Integer> res = new ArrayList<>();
    	if(input == null || input.length() == 0)
    		return res;
    	
    	List<String> list = new ArrayList<>();
    	for(int i = 0;i < input.length();i++) {
    		int j = i;
    		
    		//Adds Number
    		while(j != input.length() && Character.isDigit(input.charAt(j)))
    			j++;
    		String num = input.substring(i, j);
    		list.add(num);
    		
    		//Adds operator
    		if(j != input.length())
    			list.add(input.substring(j, j+1));
    		i = j;
    	}
    	//Number of integers
    	int n = (list.size()+1)/2;
    	ArrayList<Integer> [][]dp = new ArrayList[n][n];
    	
    	calculate(dp, list, n);    	
    	return dp[0][n-1];
    }
	
	void calculate(ArrayList [][]dp, List<String> list, int n) {
		for(int gap = 0;gap < n;gap++) {
			for(int i = 0,j = gap;j < n;i++,j++) {
				dp[i][j] = new ArrayList<>();
				if(j < i+1) {
					dp[i][i] = new ArrayList<>();
					dp[i][i].add(Integer.valueOf(list.get(2*i)));
					continue;
				}
				// normally its k = i+1 to k < j here its k = i to k < j
				for(int k = i;k < j;k++) {
					ArrayList<Integer> left = dp[i][k];
					ArrayList<Integer> right = dp[k+1][j];
					String operator = list.get(2*k+1);
					for(int l : left) {
						for(int r : right) {
							if(operator.compareTo("+") == 0)
    							dp[i][j].add(l+r);
    						else if(operator.compareTo("-") == 0)
    							dp[i][j].add(l-r);
    						else
    							dp[i][j].add(l*r);
						}
					}
				}
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		//String input = "2*3-4*5";
		String input = "2-1-1";
		System.out.println(new Solution().diffWaysToCompute(input));
	}
}