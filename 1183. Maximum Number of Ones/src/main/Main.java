package main;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/maximum-number-of-ones/discuss/377033/C%2B%2B-O(1)-solution-with-explanation-Totally-intuitive
// https://leetcode.com/problems/maximum-number-of-ones/discuss/377313/Java-Easy-Solution-with-Video
class Solution {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        if(width == 1 || height == 1 || sideLength == 1 && maxOnes > 1 || maxOnes == 0 || maxOnes > sideLength*sideLength)
        	return 0;
        
    	int [][]dp = new int[sideLength][sideLength];
    	for(int i = 0;i < width;i++) {
    		for(int j = 0;j < height;j++) {
    			dp[i%sideLength][j%sideLength]++;
    		}
    	}
    	
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
    	
    	for(int i = 0;i < sideLength;i++) {
    		for(int j = 0;j < sideLength;j++) {
    			pq.offer(dp[i][j]);
    		}
    	}
    	
    	int ans = 0;
    	for(int i = 0;i < maxOnes;i++) {
    		ans += pq.poll();
    	}
    	return ans;
    }
    
    // 0(1) but understand first
    public int maximumNumberOfOnes1(int width, int height, int sideLength, int maxOnes) {
        // 
        int nw = width / sideLength, nh = height / sideLength;
        int mw = width % sideLength, mh = height % sideLength;
        
        int base = nw * nh * maxOnes;
        int area1 = mw * mh, area2 = mh * (sideLength - mw), area3 = mw * (sideLength - mh);

        if (maxOnes < area1) return base + (nw + nh + 1) * maxOnes;
        if (maxOnes > area1 + area2 + area3) return base + mw * height + mh * width - mw * mh;
        
        base = base + (nw + nh + 1) * area1;
        int d = 0;
        for (int i = 0; i <= area2 && i + area1 <= maxOnes; i++) {
            int j = maxOnes - area1 - i;
            if (j > area3) continue;
            d = Math.max(d, nw* i + nh * j);
        }
        return base + d;
        
    }
}

public class Main {
	public static void main(String[] args) {
		//int width = 3, height = 3, sideLength = 2, maxOnes = 1;
		int width = 3, height = 3, sideLength = 2, maxOnes = 2;
		System.out.println(new Solution().maximumNumberOfOnes(width, height, sideLength, maxOnes));
	}
}