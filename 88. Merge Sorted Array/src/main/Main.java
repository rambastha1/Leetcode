package main;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	if(nums1 == null || m == 0 || nums2 == null || n == 0 || m < nums1.length + nums2.length)
    		return;
    	for(int i = n-1;i >= 0;i--) {
    		int num = nums1[m+n-1];
    		int j = m+n-2;
    		while(j >= 0 && nums1[j] > nums2[i]) {
    			nums1[j+1] = nums1[j];
    			j--;
    		}
    		    		
    		if(j!=m-2 || num > nums2[i]) {
    			int temp = nums1[j+1];
    			nums1[j+1] = nums2[i];
    			nums2[i] = temp;
    		}
    	}
    	print(nums1);
    }
    
    void print(int []nums1) {
    	for(int i : nums1)
    		System.out.print(i + " ");
    	System.out.println();
    }
}

public class Main {
	public static void main(String[] args) {
		int [] nums1 = {1,2,3,1000,1000,1000}, nums2 = {2,5,6};
		int m = 3, n = 3;
		new Solution().merge(nums1, m, nums2, n);
	}
}