package main;

class Solution {
    public boolean lemonadeChange(int[] bills) {
    	int n = bills.length;
    	int []arr = new int[3];
    	
    	for(int bill : bills) {
    		if(bill == 5) {
    			arr[0]++;
    		} else {
    			int change = bill - 5;
    			if(change == 5) {
	    			if(arr[0] == 0)
	    				return false;
	    			else
	    				arr[0]--;
	    			arr[1]++;
    			} else {
	    			if(arr[0] == 0 || (arr[0] < 3 && arr[1] == 0))
	    					return false;
	    			else {
	    				if(arr[1] != 0) {
	    					arr[1]--;
	    					arr[0]--;
	    				} else 
	    					arr[0] = arr[0]-3;
	    			}
	    			arr[2]++;
    			}
    		}
    	}
    	return true;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []bills = {5,5,5,10,20};
		int []bills = {5,5,10,10,20};
		System.out.println(new Solution().lemonadeChange(bills));
	}
}