package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/problems/grid-illumination/discuss/243076/Java-Clean-Code-O(N)-Time-and-O(N)-Space-Beats-100
// keep putting count in maps, in queries if found remove
class Solution {
	// MLE if array is used for row, col, diag and anti
	public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap(), col = new HashMap(), diag = new HashMap(), anti = new HashMap();      
        Map<Integer, Boolean> map = new HashMap();
        
        // increment counters while adding lamps
        for(int []l : lamps){
            int x = l[0], y = l[1];
            row.put(x, row.getOrDefault(x, 0) + 1);
            col.put(y, col.getOrDefault(y, 0) + 1);
            diag.put(x-y, diag.getOrDefault(x-y, 0) + 1);
            anti.put(x+y, anti.getOrDefault(x+y, 0) + 1);
            map.put(N*x + y, true);
        }

        int[] ans = new int[queries.length];
        // address queries
        for(int i = 0;i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1];
            
            ans[i] = (row.getOrDefault(x, 0) > 0 || col.getOrDefault(y, 0) > 0 || diag.getOrDefault(x-y, 0) > 0 || 
            		anti.getOrDefault(x+y, 0) > 0) ? 1 : 0;
            
            // switch off the lamps, if any
            
            for(int x1 = Math.max(0, x-1);x1 <= Math.min(N-1, x+1);x1++) {
            	for(int y1 = Math.max(0, y-1);y1 <= Math.min(N-1, y+1);y1++) {
            		if(map.containsKey(N*x1 + y1) && map.get(N*x1 + y1)){
                        // the lamp is on, turn it off, so decrement the count of the lamps
                        row.put(x1, row.getOrDefault(x1, 1) - 1);
                        col.put(y1, col.getOrDefault(y1, 1) - 1);
                        diag.put(x1 - y1, diag.getOrDefault(x1 - y1, 1) - 1);
                        anti.put(x1 + y1, anti.getOrDefault(x1 + y1, 1) - 1);
                        map.put(N*x1+y1, false);
                    }
            	}
            }
        }
        return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 5;
		int [][]lamps = {{0,0},{4,4}}, queries = {{1,1},{1,0}};
		System.out.println(Arrays.toString(new Solution().gridIllumination(N, lamps, queries)));
	}
}
