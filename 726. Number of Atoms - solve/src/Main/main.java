package Main;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

// https://leetcode.com/problems/number-of-atoms/discuss/109345/Java-Solution-using-Stack-and-Map

class Solution {
    public String countOfAtoms(String formula) {
    	TreeMap<String, Integer> map = new TreeMap<>();
    	Stack<TreeMap<String, Integer>> stack = new Stack<>();
    	
    	int n = formula.length();
    	int i = 0;
    	while(i < n) {
    		if(formula.charAt(i) == '(') {
    			stack.push(map);
    			map = new TreeMap<>();
    			i++;
    		} else if(formula.charAt(i) == ')') {
    			i++;
    			int val = 0;
    			while(i < n && Character.isDigit(formula.charAt(i))) {
    				val = val*10 + formula.charAt(i)-'0';
    				i++;
    			}
    			// base caase check if bracket value is 0
    			val = val==0?1:val;
    			
    			// notice pop just once
    			Map<String, Integer> temp = map;
    			map = stack.pop();
    			
    			for(String atom : temp.keySet()) {
    				map.put(atom, temp.get(atom)*val + map.getOrDefault(atom, 0));
    			}
    		} else {
    			// For atom like Mg, j is set from i+1
    			int j = i+1;
    			while(j < n && Character.isLowerCase(formula.charAt(j)))
    				j++;
    			String atom = formula.substring(i, j);
    			int val = 0;
    			
    			while(j < n && Character.isDigit(formula.charAt(j))) {
    				val = val*10 + formula.charAt(j)-'0';
    				j++;
    			}
    			
    			val = val==0?1:val;
    			map.put(atom, map.getOrDefault(atom, 0) + val);
    			i = j;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(String atom : map.keySet()) {
    		sb.append(atom);
    		sb.append(map.get(atom) == 1?"":map.get(atom));
    	}
    	return sb.toString();
    }
}

public class main {
	public static void main(String[] args) {
		String formula = "K4(MgN(SMg3)2)2";
		System.out.println(new Solution().countOfAtoms(formula));
	}
}
