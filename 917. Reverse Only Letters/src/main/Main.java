package main;

class Solution {
    public String reverseOnlyLetters(String S) {
    	StringBuilder sb = new StringBuilder(S);
    	int i = 0, j = S.length()-1;
    	while(i < j) {
    		if(!Character.isLetter(sb.charAt(i)))
    			i++;
    		else if(!Character.isLetter(sb.charAt(j)))
    			j--;
    		else {
    			char temp = sb.charAt(i);
    			sb.setCharAt(i, sb.charAt(j));
    			sb.setCharAt(j, temp);
    			i++;
    			j--;
    		}
    	}
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String S = "a-bC-dEf-ghIj";
		System.out.println(new Solution().reverseOnlyLetters(S));
	}
}