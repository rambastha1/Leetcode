package main;

class Solution {
	
	// two possible scenarios y4 > y2 (overlap at top right) 
	// && y4 < y2 (overlap at bottom right)
	// in either case bottom left x of overlap area = max(x1, x3) bottom left y = max(y1,y3)
	// top tight x = min(x2,x4) and y = min(y2,y4)
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    	int abcd = (C-A)*(D-B);
    	int efgh = (G-E)*(H-F);
    	int common = overlap(A, C, E, G) * overlap(B, D, F, H);
    	return abcd + efgh - common;
    }
    
    int overlap(int A, int C, int E, int G) {
    	// non overlapping
    	if(A > G || E > C)
    		return 0;
    	return Math.min(C, G) - Math.max(A, E);
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
