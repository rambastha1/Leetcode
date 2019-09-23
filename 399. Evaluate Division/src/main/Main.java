package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    	double []res = new double[queries.size()];
    	if(equations == null || equations.size() == 0 || values == null || values.length == 0 ||
    			queries == null || queries.size() == 0)
    		return res;
    	Map<String, Map<String, Double>> map = new HashMap<>();
    	for(int i = 0;i < equations.size();i++) {
    		List<String> eqList = equations.get(i);
    		if(!map.containsKey(eqList.get(0)))
    			map.put(eqList.get(0), new HashMap<>());
    		map.get(eqList.get(0)).put(eqList.get(1), values[i]);
    		if(!map.containsKey(eqList.get(1)))
    			map.put(eqList.get(1), new HashMap<>());
    		map.get(eqList.get(1)).put(eqList.get(0), 1/values[i]);
    	}
    	System.out.println(map);
    	
    	for(int i = 0;i < queries.size();i++) {
    		res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, map, new HashSet<>());
    	}
    	print(res);
    	return res;
    }
    
    double dfs(String src,String dest,double weight,Map<String, Map<String, Double>> map,Set<String> seen) {
    	
    	if(!map.containsKey(src) || seen.contains(src))
    		return -1;
    	seen.add(src);
    	if(src.compareTo(dest) == 0)
    		return weight;
    	
    	Map<String, Double> next = map.get(src);
    	for(String c : next.keySet()) {
    		double result = dfs(c, dest, weight*next.get(c), map, seen);
    		if(result != -1)
    			return result;
    	}    	
    	return -1;
    }
    
    double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    
    void print(double []res) {
    	for(double d : res)
    		System.out.print(d + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
		double[] values = {2.0, 3.0};
		List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), 
				Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));
		new Solution().calcEquation(equations, values, queries);
	}
}