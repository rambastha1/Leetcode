package main;

class Solution {
	public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = -1, num;
        for (String token: tokens) {
            switch(token) {
                case "+":
                    num = stack[top--];
                    stack[top] = stack[top] + num;
                    break;
                case "-":
                    num = stack[top--];
                    stack[top] = stack[top] - num;
                    break;
                case "*":
                    num = stack[top--];
                    stack[top] = stack[top] * num;
                    break;
                case "/":
                    num = stack[top--];
                    stack[top] = stack[top] / num;
                    break;
                default:
                    stack[++top] = Integer.parseInt(token);
            }
        }
        return stack[0];
    }
}

public class Main {
	public static void main(String[] args) {
		String []tokens = {"2", "1", "+", "3", "*"};
		System.out.println(new Solution().evalRPN(tokens));
	}
}