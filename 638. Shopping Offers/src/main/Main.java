package main;

import java.util.Arrays;
import java.util.List;

class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    	int ans = 0;
    	for(int i = 0;i < price.size();i++)
    		ans+= needs.get(i)*price.get(i);
    	
    	for(int i = 0;i < special.size();i++) {
    		int cost = 0;
    		List<Integer> list = special.get(i);
    		
    		int min = Integer.MAX_VALUE;
    		for(int j = 0;j < list.size()-1;j++) {
    			if(needs.get(j) == 0)
    				continue;
    			min = Math.min(min, (list.get(j) == 0)?needs.get(j):needs.get(j)/list.get(j));
    		}
    		if(min == Integer.MAX_VALUE) {
    			cost = 0;
    			ans = Math.min(ans, cost);
    			break;
    		} else cost += min *list.get(list.size()-1);
    		
    		for(int j = 0;j < price.size();j++) {
        		cost += price.get(j) * ((list.get(j) == 0)?needs.get(j):needs.get(j)-(min*list.get(j))); 
        	}   
    	
    		ans = Math.min(ans, cost);
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		List<Integer> price = Arrays.asList(2,5);
		List<List<Integer>> special = Arrays.asList(Arrays.asList(3,0,5),Arrays.asList(1,2,10));
		List<Integer> needs = Arrays.asList(3,2);
		
		/*List<Integer> price = Arrays.asList(2,3,4);
		List<List<Integer>> special = Arrays.asList(Arrays.asList(1,1,0,4),Arrays.asList(2,2,1,9));
		List<Integer> needs = Arrays.asList(1,2,1);*/
		
		/*List<Integer> price = Arrays.asList(1,1,1);
		List<List<Integer>> special = Arrays.asList(Arrays.asList(1,1,0,0),Arrays.asList(2,2,1,9));
		List<Integer> needs = Arrays.asList(1,1,0);*/		
		System.out.println(new Solution().shoppingOffers(price, special, needs));
	}
}