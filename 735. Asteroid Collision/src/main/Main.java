package main;

import java.util.Stack;
// extension of 838. Push Dominoes

class Solution {
	
	// Two pointer Time 0(N) Space 0(1)
	public int[] asteroidCollision(int[] asteroids) {
		if(asteroids == null || asteroids.length <= 1)
			return asteroids;
		// starting from second element
		int prev = 0, curr = 1, n = asteroids.length;
		
		while(curr < n) {
			/* when current goes left and all prev going right
			 * current is largest of all, will nullify all of them
			 * at this stage current should be the first element
			 */
			if(prev == -1) {
				prev++;
				asteroids[prev] = asteroids[curr];
				curr++;
				continue;
			} else {
				/* when current goes left and prev right stronger should persist 
				 */
				if(asteroids[curr] < 0 && asteroids[prev] > 0) {
					int a = Math.abs(asteroids[prev]);
					int b = Math.abs(asteroids[curr]);
					// current should should go left
					if(a < b)
						prev--;
					// prev stronger go right
					else if(a > b)
						curr++;
					// equal both null prev left and curr right
					else {
						prev--;
						curr++;
					}
				} else {
					/* for R-R, L-R and L-L
					 */
					prev++;
					asteroids[prev] = asteroids[curr];
					curr++;
				}
			}
		}
		int []res = new int[prev+1];
		for(int i = 0;i <= prev;i++)
			res[i] = asteroids[i];
		print(res);
		return res;
	}
	
	// Stack based solution Time 0(N) Space 0(N)
    public int[] asteroidCollision1(int[] asteroids) {
    	Stack<Integer> stack = new Stack<>();
    	int n = asteroids.length;
    	
    	for(int i = 0;i < n;i++) {
    		// if current going right push into stack
    		if(stack.isEmpty() || asteroids[i] > 0) {
    			stack.push(asteroids[i]);
    			continue;
    		}
    		// current element going left
    		while(true) {
    			int prev = stack.peek();
    			// prev going left
    			if(prev < 0) {
    				stack.push(asteroids[i]);
    				break;
    			}
    			// current going left and prev going right both equal value..null
    			if(prev == Math.abs(asteroids[i])) {
    				stack.pop();
    				break;
    			}
    			
    			// prev going right and more value..it will persist..current will be null
    			if(prev > Math.abs(asteroids[i]))
    				break;
    			// prev going right and lower value than current just pop prev will be null
    			stack.pop();
    			// if all prev get nullified
    			if(stack.isEmpty()) {
    				stack.push(asteroids[i]);
    				break;
    			}
    		}
    	}
    	int []res = new int[stack.size()];
    	for(int i = stack.size()-1;i >= 0;i--) {
    		res[i] = stack.pop();
    	}
    	return res;
    }
    
    void print(int []res) {
    	for(int i : res)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int []asteroids = {5,10,-5};
		new Solution().asteroidCollision(asteroids);
	}
}
