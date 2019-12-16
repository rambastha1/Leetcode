package main;

class Solution {
	/* move from both ends, find smallest i to match string, if match 
	 * then do same for string from s-e
	 * at any time we are considering string from s-e
	 */
	// Time 0(N^2)
    public int longestDecomposition(String text) {
    	int n = text.length();
    	int ans = 0;
    	int i = 0, j = n-1;
    	int s = 0, e = n;
    	while(i < j) {
    		// 0(N) operation
    		String s1 =text.substring(s, i+1);
    		String s2 = text.substring(j, e);
    		if(s1.equals(s2)) {
    			ans += 2;
    			s = i+1;
    			e = j;
    		}
    		i++;
    		j--;
    	}
    	if(s < e)
    		ans++;
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		String text = "ghiabcdefhelloadamhelloabcdefghi";
		System.out.println(new Solution().longestDecomposition(text));
	}
}
