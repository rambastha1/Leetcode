package main;

/* 
 * use same logic of snake and ladder to traverse
 * create string array and append characters to particular indices
 */

class Solution {
	
    public String convert(String s, int numRows) {
    	if(numRows == 1)
    		return s;
    	
    	String []rows = new String[numRows];
    	for(int i = 0;i < numRows;i++)
    		rows[i] = "";
    	
    	int index = 0, dir = 1;
    	for(int i = 0;i < s.length();i++) {
    		rows[index] += s.charAt(i);
    		if(index == numRows - 1)
    			dir = -1;
    		else if(index == 0)
    			dir = 1;
    		index += dir;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(String str : rows)
    		sb.append(str);
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		String s = "PAYPALISHIRING";
		int numRows = 4;
		System.out.println(new Solution().convert(s, numRows));
	}
}
