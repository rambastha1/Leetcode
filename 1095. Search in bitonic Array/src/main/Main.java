package main;

class  MountainArray {
	int []A = {1,2,3,4,5,3,1};
	public int get(int index) {
		return A[index];
	}
	
	public int length() {
		return A.length;
	}
}
 
class Solution {
    public int findInMountainArray(int target, MountainArray a) {
    	a = new MountainArray();
    	int l = 0, r = a.length() - 1;
    	int peak = findpeak(a, l, r);
    	
    	int index = searchleft(a, 0, peak, target);
    	if(index == -1)
    		index = searchright(a, peak, a.length()-1, target);
    	return index;
    }
    
    private int searchleft(MountainArray a, int l, int r, int target) {
    	while(l <= r) {
    		int m = l + (r-l)/2;
    		int M = a.get(m);
    		if(M == target)
    			return m;
    		else if (M < target)
    			l = m+1;
    		else
    			r = m-1;
    	}
    	return -1;
    }
    
    private int searchright(MountainArray mountainArr, int l, int r, int target) {
    	while(l <= r) {
    		int m = l + (r-l)/2;
    		int M = mountainArr.get(m);
    		if(M == target)
    			return m;
    		else if(M < target)
    			r = m-1;
    		else
    			l = m+1;
    	}
    	return -1;
    }
    
    private int findpeak(MountainArray a, int l, int r) {
    	while(l < r) {
    		int m = l + (r-l)/2;
    		if(a.get(m) < a.get(m+1))
    			l = m+1;
    		else
    			r = m;
    	}
    	return l;
    }
}

public class Main {
	public static void main(String[] args) {
		int []array = {1,2,3,4,5,3,1};
		int target = 3;
		System.out.println(new Solution().findInMountainArray(target, null));
	}
}
