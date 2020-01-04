package main;

import java.util.Arrays;
import java.util.List;
// https://leetcode.com/articles/equal-rational-numbers/
// 0.5252 = 0.5200 + 0.005200 + .... = 0.52(1 + 0.01 + 0.0001 +... ) gp = 0.52 * 1/(1-0.01)
class Solution {
    public boolean isRationalEqual(String S, String T) {
    	return Math.abs(compute(S) - compute(T)) < 1e-9;
    }
    
    private double compute(String str) {
    	List<Double> ratios = Arrays.asList(1.0, 1.0/9, 1.0/99, 1.0/999, 1.0/9999);
    	if(str.indexOf('(') == -1)
    		return Double.valueOf(str);
    	
    	Double non_rep_val = Double.valueOf(str.substring(0, str.indexOf('(')));
    	int non_rep_length = str.indexOf('(') - str.indexOf('.') - 1;
     	int rep_len = str.indexOf(')') - str.indexOf('(') - 1;
    	int rep_val = Integer.parseInt(str.substring(str.indexOf('(') + 1, str.indexOf(')')));
    	return non_rep_val + rep_val * Math.pow(0.1, non_rep_length) * ratios.get(rep_len);
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "0.(52)", T = "0.5(25)";
		System.out.println(new Solution().isRationalEqual(S, T));
	}
}
