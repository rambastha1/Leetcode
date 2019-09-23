package main;

class Solution {
	/*
	 * Binary Search
	 */
	public int shipWithinDays(int[] weights, int D) {
		int max = 0, sum = 0;
		for(int w : weights) {
			max = Math.max(max, w);
			sum += w; 
		}
        //Same as finding floor
        max--; // handle arr[0] == key
		while(sum - max > 1) {
			int mid = max + (sum-max)/2;
			int days = 1, currsum = 0;
			for(int w : weights) {
				if(w + currsum > mid) {
					days += 1;
					currsum = 0;
				}
				currsum += w;
			}
			if(days > D) 
				max = mid;
			else {
				sum = mid;
			}
		}
		return sum;
	}
	
	/*
	 * My Solution working Time 0(N*(sum-avg))
	 */
	
    /*public int shipWithinDays(int[] weights, int D) {
    	int sum = getsum(weights);
    	int avg = (int) sum/D;
    	int l = avg, r = sum;
    	
    	for(int i = avg;i < sum;i++) {
    		if(ispossible(weights, sum, D, i))
    			return i;
    	}
    	return sum;
    }
    
    boolean ispossible(int []weights, int sum, int D, int capacity) {
    	int i = 0, curr = 0, days = 0;
    	while(i < weights.length) {
    		curr = 0;
    		while(curr < capacity && i < weights.length) {
    			curr += weights[i++];
    		}
    		
    		if(curr > capacity) {
    			curr -= weights[--i];
    		}
    		days++;
    		if(days > D)
    			return false;
    	}
    	return true;
    }
    
    int getsum(int []weights) {
    	int sum = 0;
    	for(int i = 0;i < weights.length;i++)
    		sum += weights[i];
    	return sum;
    }*/
}

public class Main {
	public static void main(String[] args) {
		/*int []weights = {1,2,3,4,5,6,7,8,9,10}; 
		int D = 5;*/
		
		int []weights = {3,2,2,4,1,4}; 
		int D = 3;
		
		/*int []weights = {1,2,3,1,1}; 
		int D = 4;*/
		
		System.out.println(new Solution().shipWithinDays(weights, D));
	}
}