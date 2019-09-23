package main;
import java.util.Stack;
import javafx.util.Pair;

/* Every time a smaller element comes ans is 1
 * No need to update stack if element a[i] is outdated i.e. outside of 7 days window
 * because it is larger than element.. had it been smaller it would already been popped out
 * 
 * If greater value comes ans is 1 + previous value for eg. for 95 it is weight of 85 + weight of 95
 * i.e weighted sum of stack
 */

class StockSpanner {
	Stack<Pair<Integer, Integer>> stack;
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
    	int res = 1;
    	while(!stack.isEmpty() && stack.peek().getKey() <= price) {
    		res += stack.peek().getValue();
    		stack.pop();    		
    	}
    	stack.push(new Pair<Integer, Integer>(price, res));
    	return res;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

public class Main {
	public static void main(String[] args) {
		StockSpanner S = new StockSpanner();
		System.out.println(S.next(90));
		System.out.println(S.next(80));
		System.out.println(S.next(60));
		System.out.println(S.next(70));
		System.out.println(S.next(60));
		System.out.println(S.next(75));
		System.out.println(S.next(85));
		System.out.println(S.next(95));
	}
}