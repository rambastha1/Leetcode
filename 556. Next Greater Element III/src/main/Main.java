package main;

// same problem as 31. Next Permutation
class Solution {
    public int nextGreaterElement(int n) {
    	String str = String.valueOf(n);
    	int []arr = new int[str.length()];
    	int temp = n;
    	for(int i = str.length()-1;i >= 0;i--) {
    		arr[i] = n%10;
    		n /= 10;
    	}
    	
    	int right = str.length()-2;
    	while(right >= 0 && arr[right] >= arr[right+1])
    		right--;
    	if(right >= 0) {
    		int i = str.length()-1;
        	while(i >= 0 && arr[i] <= arr[right])
        		i--;
        	swap(arr, right, i);
    	}
    	reverse(arr, right+1, str.length()-1);
    	
    	int ans = 0, mul = 1;
    	for(int i = str.length()-1;i >= 0;i--) {
    		ans += arr[i]*mul;
    		mul *= 10;
    	}
    	
    	return ans<=temp?-1:ans;
    }
    
    private void swap(int []arr, int l, int r) {
    	int temp = arr[l];
    	arr[l] = arr[r];
    	arr[r] = temp;
    }
    
    private void reverse(int []arr, int l, int r) {
    	while(l < r)
    		swap(arr, l++, r--);
    }
}

public class Main {
	public static void main(String[] args) {
		int n = 101;
		System.out.println(new Solution().nextGreaterElement(n));
	}
}
