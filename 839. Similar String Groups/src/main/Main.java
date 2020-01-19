package main;

class Solution {
    int count;
    // Time 0(n^2) 
    public int numSimilarGroups(String[] A) {
    	int n = A.length;
    	this.count = n;
    	
    	int []parent = new int[n], rank = new int[n];
    	for(int i = 0;i < n;i++) {
    		parent[i] = i;
    		rank[i] = 1;
    	}
    	
    	for(int i = 0;i < n-1;i++) {
    		for(int j = i+1;j < n;j++) {
    			if(parent[i] == parent[j])
    				continue;
    			// this part is amortized 0(1)
    			if(isvalid(A[i], A[j])) {
    				union(A, i, j, parent, rank);
    			}
    		}
    	}
    	return this.count;
    }
    
    private boolean isvalid(String a, String b) {
    	int count = 0;
    	for(int i = 0;i < a.length();i++) {
    		if(a.charAt(i) != b.charAt(i))
    			count++;
    		if(count > 2)
    			return false;
    	}
    	return true;
    }
    
    private int find(String[] A, int []parent, int i) {
    	if(parent[i] != i)
    		parent[i] = find(A, parent, parent[i]);
    	return parent[i];
    }
    
    private void union(String[] A, int a, int b, int []parent, int []rank) {
    	int x = find(A, parent, a);
    	int y = find(A, parent, b);
    	
        if(x == y)
    		return;
    	if(rank[x] < rank[y]) {
    		parent[x] = y;
    	} else if(rank[x] > rank[y]) {
    		parent[y] = x;
    	} else {
    		parent[y] = x;
    		rank[x]++;
    	}
    	this.count--;
    }
}

public class Main {
	public static void main(String[] args) {
		String []A = {"tars","rats","arts","star"};
		System.out.println(new Solution().numSimilarGroups(A));
	}
}
