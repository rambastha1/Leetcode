package main;

class Solution {
	/*
	 * Time 0(T)
	 * Space 0(T)
	 */
    public String customSortString(String S, String T) {
    	StringBuilder sb = new StringBuilder();
    	int []count = new int[26];
    	for(char c:T.toCharArray())
    		count[c-'a']++;
    	//it also sorts sb according to S
    	for(char c : S.toCharArray()) {
    		while(count[c-'a']-- > 0)
    			sb.append(c);
    	}
    	/* add the rest of T element to the ans
    	 * the order of rest of element(after S) doesn't matter
    	 * which means there can be multiple correct ans if T.length > S.length
    	 * we are returning one in lexicographical order
    	 */
    	for(char c = 'a';c <= 'z';c++) {
    		while(count[c-'a']-- > 0)
    			sb.append(c);
    	}    		
    	
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "cbafg";//"cba";
		String T = "abcd";//"abcd";
		System.out.println(new Solution().customSortString(S, T));
	}
}