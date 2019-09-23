package main;

import java.util.Stack;

class Solution {
	
	/*
	 * 19ms solution
	 * Uses Stack
	 */
	
	public int calculate(String s) {
    	int len;
        if(s==null || (len = s.length())==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int curr = 0;
        char sign = '+';
        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                curr = curr*10+s.charAt(i)-'0';
            }
            if((!Character.isDigit(s.charAt(i)) && ' '!=s.charAt(i)) || i == len-1){
                if(sign=='-'){
                    stack.push(-curr);
                }
                if(sign=='+'){
                    stack.push(curr);
                }
                if(sign=='*'){
                    stack.push(stack.pop()*curr);
                }
                if(sign=='/'){
                    stack.push(stack.pop()/curr);
                }
                sign = s.charAt(i);
                curr = 0;
            }
        }

        int result = 0;
        for(int i:stack){
            result += i;
        }
        return result;
    }
	
	
	/*
	 * 4ms solution
	 * Time: 0(N) & Constant Space
	 * s.charAt can be used in place of char_arr
	 */
	/*public int calculate(String s) {
		char[] char_arr = s.toCharArray();
        char sign = '+';
        int prev = 0;
        int curr = 0;
        int result = 0;
        
        for(int i=0; i<=char_arr.length; i++){
            if(i < char_arr.length && char_arr[i] >= '0' && char_arr[i] <= '9'){
                curr = curr * 10 + char_arr[i] - '0';
                
            } else{
                if(i < char_arr.length && char_arr[i] == ' '){
                    continue;
                }
                //System.out.println(num + " " + i);
                if(sign == '+'){
                    result += curr;
                    prev = curr;
                }else if(sign == '-'){
                    result -= curr;
                    prev = -curr;
                }else if(sign == '*'){
                	//remove prev and add prev*curr as per order
                    result = result - prev + prev * curr;
                    prev = prev * curr;
                }else if(sign == '/'){
                	//remove prev and add prev/curr as per order
                    result = result - prev + prev / curr;
                    prev = prev / curr;
                }
                if(i < char_arr.length){
                    sign = char_arr[i];
                }
                curr = 0;
            }
        }
        return result;
    }*/
	
	
	
}

public class Main {
	public static void main(String[] args) {
		String s = "3+2*2";
		System.out.println(new Solution().calculate(s));
	}
}