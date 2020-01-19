package main;
import java.util.ArrayList;
import java.util.List;
// https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
class Solution {
    public List<String> addOperators(String num, int target) {
    	List<String> res = new ArrayList<>();
    	if(num.length() == 1) {
    		if(Integer.parseInt(num) != target)
    			return res;
    		res.add(num);
    		return res;
    	}
    	
    	dfs(res, num, new StringBuilder(), target, 0, 0, 0);
    	return res;
    }
    
    // keep track of running sum and last value to handle operator priority
    // no need to handle at end
    void dfs(List<String> res, String num, StringBuilder sb, int target, int index, long sum, long lastval) {
    	if(index == num.length()) {
    		if(sum == target)
    			res.add(sb.toString());
    		return;
    	}
    	
    	for(int i = index;i < num.length();i++) {
    		// values with leading zeroes not allowed
    		if(num.charAt(index) == '0' && i!= index)
    			break;
    		long curr = Long.parseLong(num.substring(index, i+1));
    		// len is used because it is used in multiple places and backtracking using sb.len() was difficult
    		int len = sb.length();
    		if(index == 0) {
    			dfs(res, num, sb.append(curr), target, i+1, curr, curr);
    			sb.setLength(len);
    		} else {
    			// sum
    			dfs(res, num, sb.append('+').append(curr), target, i+1, sum + curr, curr);
    			sb.setLength(len);
    			
    			// subtraction
    			dfs(res, num, sb.append('-').append(curr), target, i+1, sum - curr, -curr);
    			sb.setLength(len);
    			
    			//multiplication
    			dfs(res, num, sb.append('*').append(curr), target, i+1, sum - lastval + lastval*curr, lastval*curr);
    			sb.setLength(len);
    		}
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		String num = "105";
		int target = 5;
		System.out.println(new Solution().addOperators(num, target));
	}
}
