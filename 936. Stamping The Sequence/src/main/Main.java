package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/stamping-the-sequence/discuss/201546/12ms-Java-Solution-Beats-100
class Solution {
	
	/* make target all *
	 * find stamp in target make *
	 * 0(n(m-n)) time
	 */
	public int[] movesToStamp(String stamp, String target) {
		List<Integer> res = new ArrayList<>();
        char []s = stamp.toCharArray(), t = target.toCharArray();
        int star = 0, n = target.length();
        boolean []visited = new boolean[n];
        
        while(star < n) {
        	// i + s.length() <= t.length()
        	boolean replacedall = false;
        	for(int i = 0;i + s.length <= target.length();i++) {
        		if(!visited[i] && canreplace(s, t, i)) {
        			star += replace(s, t, i);
        			visited[i] = true;
        			replacedall = true;
        			res.add(i);
        			if(star == n)
            			break;
        		}
        		
        	}
        	if(!replacedall)
        		return new int[0];
        }
        // since we doing reverse res should be in reverse order
        int []ans = new int[res.size()];
        for(int i = 0;i < ans.length;i++)
        	ans[i] = res.get(res.size() - i - 1);
        return ans;
    }
	
	private boolean canreplace(char []s, char []t, int offset) {
		// it will match s[i] with t[index] where index = i + offset
		for(int i = 0;i < s.length;i++) {
			if(t[i+offset] != '*' && t[i+offset] != s[i])
				return false;
		}
		return true;
	}
	
	private int replace(char []s, char []t, int offset) {
		int count = 0; 
		for(int i = 0;i < s.length;i++) {
			if(t[i + offset] != '*') {
				t[i + offset] = '*';
				count++;
			}
		}
		return count;
	}
}

public class Main {
	public static void main(String[] args) {
		String stamp = "abc", target = "ababc";
		System.out.println(Arrays.toString(new Solution().movesToStamp(stamp, target)));
	}
}
