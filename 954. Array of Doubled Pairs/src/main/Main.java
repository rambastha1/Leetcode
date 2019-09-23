package main;

import java.util.TreeMap;

class Solution {
	// Time 0(N) Space 0(N)
    public boolean canReorderDoubled(int[] A) {
    	if(A == null || A.length == 0)
    		return true;
    	// num -> count
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	for(int a : A)
    		map.put(a, map.getOrDefault(a, 0)+1);
    	int index = 0, n = A.length;
    	while(map.size() > 0) {
    		int num = map.firstKey();
    		map.put(num, map.get(num)-1);
    		if(map.get(num) == 0)
    			map.remove(num);
    		index++;
    		int want = num < 0? num/2:2*num;
			if(!map.containsKey(want))
    			return false;
    		map.put(want, map.getOrDefault(want, 0)-1);
    		if(map.get(want) == 0)
    			map.remove(want);
    		index++;
    	}
    	return index==n;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []A = {3,1,3,6};
		//int []A = {4,-2,2,-4};
		int []A = {1,2,4,16,8,4};
		System.out.println(new Solution().canReorderDoubled(A));
	}
}
