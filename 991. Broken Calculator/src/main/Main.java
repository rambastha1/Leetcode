package main;

// Must read
// https://leetcode.com/articles/broken-calculator/

class Solution {
	
	// Time 0(lgY)
	public int brokenCalc(int X, int Y) {
    	if(X == Y)
    		return 0;
    	int ans = 0;
    	while(Y > X) {
    		ans++;
    		if(Y%2==0)
    			Y /= 2;
    		else
    			Y++;
    	}
    	return ans + X - Y;
    }
	
	public int brokenCalc1(int X, int Y) {
        int multiple = 1, res = 0;
        while (X * multiple < Y) {
            multiple <<= 1;
            res++;
        }
        int diff = X * multiple - Y;
        while (diff > 0) {  
            res += diff / multiple;
            // diff should decrease multiple times
            diff -= diff / multiple * multiple;
            multiple >>= 1;
        }
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		int X = 5, Y = 8;
		//int X = 1024, Y = 1;
		System.out.println(new Solution().brokenCalc(X, Y));
	}
}
