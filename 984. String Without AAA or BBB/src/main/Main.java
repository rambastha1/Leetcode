package main;

class Solution {
	
	// don't add two at a time, adding one at a time is also fine
    public String strWithout3a3b(int A, int B) {
    	if(A == 0 && B == 0)
    		return "";
    	StringBuilder sb = new StringBuilder();
    	char a = 'a', b = 'b';
    	int i = A, j = B;
    	if(B > A) {
    		i = B;j = A;
    		a = 'b';b = 'a';
    	}
    	
    	while(i > 0) {
    		sb.append(a);
    		i--;
    		if(i > j) {
    			sb.append(a);
    			i--;
    		}
    		if(j > 0) { 
    			sb.append(b);
    			j--;
    		}
    	}
    	return sb.toString();
    }
}

public class Main {
	public static void main(String[] args) {
		//int A = 4, B = 1;
		int A = 1, B = 3;
		System.out.println(new Solution().strWithout3a3b(A, B));
	}
}
