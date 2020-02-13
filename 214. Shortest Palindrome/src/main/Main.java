package main;
/* https://www.geeksforgeeks.org/minimum-characters-added-front-make-string-palindrome/?
 * Such algorithm returns the maximum length of the palindrome string centered at i, so we just need to find the maximum 
 * length palindrome string with the left end point at 0.
 * reverse the right side string and add at the front
 */
class Solution {
    public String shortestPalindrome(String s) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(manacher(s));
    	int index = sb.length();
    	String str = s.substring(index);
    	sb.append(str);
    	StringBuilder temp = new StringBuilder(str).reverse();
    	sb.insert(0, temp.toString());
    	return sb.toString();
    }
    
    String manacher(String s) {
    	char []arr = preprocess(s);
    	int n = arr.length;
    	int []p = new int[n];
    	int center = 0, right = 0;
    	int maxlen = 0; 
    	for(int i = 1;i < n-1;i++) {
    		int mirror = 2*center - i;
    		if(i < right)
    			p[i] = Math.min(p[mirror], right-i);
    		while(arr[i - (1+p[i])] == arr[i + (1+p[i])])
    			p[i]++;
    		maxlen = Math.max(maxlen, p[i]);
    		if(i + p[i] > right) {
    			center = i;
    			right = i + p[i];
    		}
    	}
    	int max = 0, index = 0;
    	for(int i = 0;i < p.length;i++) {
    		if(p[i] >= max && (i - p[i] - 1)/2 == 0) {
    			max = p[i];
    			index = i;
    		}
    	}
    	return s.substring(0, (index+max-1)/2);
    	//return longest(s, p);
    }
    
    char []preprocess(String s) {
    	char []arr = new char[s.length()*2+ 3];
    	arr[0] = '@';
    	arr[2*s.length()+2] = '!';
    	for(int i = 0;i < s.length();i++) {
    		arr[2*i+1] = '#';
    		arr[2*i+2] = s.charAt(i);
    	}
    	arr[2*s.length()+1] = '#';
    	return arr;
    }
    
    String longest(String s, int []p) {
    	String str = "";
    	int max = 0, index = 0;
    	for(int i = 0;i < p.length;i++) {
    		if(p[i] > max) {
    			max = p[i];
    			index = i;
    		}
    	}
    	return s.substring((index - max-1)/2, (index+max-1)/2);
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "aacecaaa";
		//System.out.println(new Solution().manacher(s));
		System.out.println(new Solution().shortestPalindrome(s));
	}
}
