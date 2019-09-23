package main;

class Solution {
	// Time 0(N)
    public int maximumSwap(int num) {
    	if(num < 10)
    		return num;
    	char []a = String.valueOf(num).toCharArray();
    	int n = a.length;
    	int x = 0, y = 0, maxindex = n-1;
    	for(int i = n-2;i >= 0;i--) {
    		if(a[i] == a[maxindex])
    			continue;
    		else if(a[i] > a[maxindex])
    			maxindex = i;
    		else {
    			x = maxindex;
    			y = i;
    		}
    	}
    	char temp = a[x];
    	a[x] = a[y];
    	a[y] = temp;
    	return Integer.parseInt(new String(a));
    }
}

public class Main {
	public static void main(String[] args) {
		int num = 10909091;
		System.out.println(new Solution().maximumSwap(num));
	}
}
