package main;

import java.util.Scanner;

/*
 * The (?=) part is a zero-width positive lookahead. 
 * Since [-,+] means - or +, the regex (?=[-,+]) means the next element is either - or +.
 * Since | is logical or operator, "/|(?=[-+])" means the element is "/", or the next element is either - or +. 
 * For example, when expression = "-1/2+1/2-1/3",
 * Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])")
 * generates [-1, 2, +1, 2, -1, 3 ]. Note that the signs - and + are preserved
 */

class Solution {
    public String fractionAddition(String expression) {
    	if(expression == null || expression.length() == 0)
    		return "";
    	int ans_num = 0, ans_deno = 1;
    	Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])");
    	while(sc.hasNext())	{
    		//System.out.print(sc.next() + " ");
    		int curr_num = sc.nextInt();
    		int curr_deno = sc.nextInt();
    		//Normal addition of two fractions
    		ans_num = curr_num * ans_deno + ans_num * curr_deno;
    		ans_deno = ans_deno * curr_deno;
    		
    		int gcd = gcd(ans_num, ans_deno);
    		ans_num /= gcd;
    		ans_deno /= gcd;
    	}
    	return ans_num + "/" + ans_deno;
    }    
    
    int gcd(int a, int b) {
    	while(a%b != 0) {
    		int temp = a;
    		a = b;
    		b = temp%b;    		
    	}
    	return Math.abs(b);
    }
}

public class Main {
	public static void main(String[] args) {
		String expression = "-1/2+1/2";
		System.out.println(new Solution().fractionAddition(expression));
	}
}