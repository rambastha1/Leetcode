package main;

class Solution {
    public int regionsBySlashes(String[] grid) {
    	int c1 = 0, c2 = 0;
    	char [] a = null;
    	for(String str : grid) {
    		a = str.toCharArray();
    		//System.out.println(a.length);
    		int i = a.length-1;
    		while(i >= 0) {
    			if(a[i] == '/')
    				c1++;
    			else if(a[i] == '\\')
    				c2++;
    			i--;	
    		}
    	}
    	
    	if(c1 == grid.length && c2 == grid.length)
    		return 4;
    	if(c1 == grid.length || c2 == grid.length)
    		return 2;
    	if(c1 == grid.length/2 && c2 == grid.length/2)
    		return (grid.length == 2)?4:2;
    	
    	return 1;
    }
    
    
}

public class Main {
	public static void main(String[] args) {
		String []grid = {"\\/", "/\\"};
		System.out.println(new Solution().regionsBySlashes(grid));
	}
}