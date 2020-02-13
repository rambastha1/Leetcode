package main;

import java.util.HashMap;
import java.util.Map;

/* union via factors if two numbers have same factors - have edge union
 * map with key as factors and value as indices
 * https://leetcode.com/problems/largest-component-size-by-common-factor/discuss/200959/Simplest-Solution-(Union-Find-only)-No-Prime-Calculation
 * Time 0(N*sqrt(N)) Space 0(N)
 */
class Solution {
	int max = 1;
    public int largestComponentSize(int[] A) {
    	int n = A.length;
    	int []parent = new int[n];
    	int []size = new int[n];
    	for(int i = 0;i < n;i++) {
    		parent[i] = i;
    		size[i] = 1;
    	}
    	// factor -> index
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i = 0;i < n;i++) {
    		for(int j = 2;j*j <= A[i];j++) {
    			if(A[i]%j == 0) {
    				if(!map.containsKey(j))
    					map.put(j, i);
    				else
    					union(parent, size, map.get(j), i);
    				
    				if(!map.containsKey(A[i]/j))
    					map.put(A[i]/j, i);
    				else
    					union(parent, size, map.get(A[i]/j), i);
    			}
    		}
    		if(!map.containsKey(A[i]))
    			map.put(A[i], i);
    		else
    			union(parent, size, i, map.get(A[i]));
    	}
    	return this.max;
    }
    
    int find(int []parent, int i) {
    	if(parent[i] != i)
    		parent[i] = find(parent, parent[i]);
    	return parent[i];
    }
    
    void union(int []parent, int []size, int a, int b) {
    	int x = find(parent, a);
    	int y = find(parent, b);
    	if(x == y)
    		return;
    	if(size[x] >= size[y]) {
    		parent[y] = x;
    		size[x] += size[y];
    		this.max = Math.max(max, size[x]);
    	} else {
    		parent[x] = y;
    		size[y] += size[x];
    		this.max = Math.max(max, size[y]);
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		int []A = {2,3,6,7,4,12,21,39};
		System.out.println(new Solution().largestComponentSize(A));
	}
}
