package main;

class Solution {
    public int monotoneIncreasingDigits(int N) {
    	if(N == 0)
    		return 0;
    	char []arr = Integer.toString(N).toCharArray();
    	int islower = arr.length;
    	for(int i = arr.length-1;i >0;i--) {
    		if(arr[i] < arr[i-1]) {
    			arr[i-1] -= 1;
    			islower = i;
    		}
    	}
    	
    	for(int i = islower;i < arr.length;i++)
    		arr[i] = '9';
    	
    	return Integer.parseInt(new String(arr));
    }
}

public class Main {
	public static void main(String[] args) {
		//int N = 10;
		//int N = 1234;
		//int N = 332;
		int N = 102;
		System.out.println(new Solution().monotoneIncreasingDigits(N));
	}
}
