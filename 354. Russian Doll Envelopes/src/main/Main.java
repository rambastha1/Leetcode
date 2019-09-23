package main;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
    	if(envelopes == null || envelopes.length <= 1 || envelopes[0].length == 0)
    		return 0;
    	Arrays.sort(envelopes, new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
			}
		});
    	
    	int ans = 1, w = envelopes[0][0], h = envelopes[0][1];
    	for(int i = 1;i < envelopes.length;i++) {
    		if(w < envelopes[i][0] && h < envelopes[i][1]) {
    			ans++;
    			w = envelopes[i][0];
    			h = envelopes[i][1];
    		}
    	}
    	return ans==1?0:ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
		int[][] envelopes = {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
		System.out.println(new Solution().maxEnvelopes(envelopes));
	}
}