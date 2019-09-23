package main;

import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/problems/fraction-to-recurring-decimal/discuss/51106/My-clean-Java-solution
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
    	if(numerator == 0)
    		return "0";
    	
    	StringBuilder sb = new StringBuilder();
    	// true ^ false -> true
    	sb.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
    	
    	long num = Math.abs((long)numerator);
    	long deno = Math.abs((long) denominator);
    	sb.append(num/deno);
    	num %= deno;
    	if(num == 0)
    		return sb.toString();
    	sb.append(".");
    	
    	// remainder -> index
    	Map<Long, Integer> map = new HashMap<>();
    	map.put(num, sb.length());
    	// for overflow and underflow
    	while(num != 0) {
    		num *= 10;
    		sb.append(num/deno);
    		// num becomes remainder
    		num %= deno;
    		if(map.containsKey(num)) {
    			int index = map.get(num);
    			sb.insert(index, "(");
    			sb.append(")");
    			break;
    		} else
    			map.put(num, sb.length());
    	}
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		//int numerator = 1, denominator = 2;
		int numerator = 2, denominator = 3;
		System.out.println(new Solution().fractionToDecimal(numerator, denominator));
	}
}
