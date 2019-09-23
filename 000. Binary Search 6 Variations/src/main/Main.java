package main;

// Got concept from first link second link for competitive code
//https://www.geeksforgeeks.org/variants-of-binary-search/
//https://www.geeksforgeeks.org/the-ubiquitous-binary-search-set-1/

/* 
 * The key point for any binary search is to figure out the "Search Space". 
 * For me, I think there are two kind of "Search Space" -- index and range
 * (the range from the smallest number to the biggest number). 
 * Most usually, when the array is sorted in one direction, 
 * we can use index as "search space", when the array is unsorted and we are going to 
 * find a specific number, we can use "range".
 */

/* Why (low + high)/2 is bad
 * This is a famous bug in binary search. if the size of array are too large, 
 * equal or larger than the upper bound of int type, hi + lo may cause an overflow 
 * and become a negative number. It's ok to write (hi + lo) / 2 here, leetcode will 
 * not give you a very large array to test. But we'd better know this.
 */

class Solution {
	
	/* Variation 1 if array Contains key
	 * Invariant: A[l] <= key and A[r] > key
	 */
	public int contains(int []arr, int key) {
		if(arr == null || arr.length == 0)
			return -1;
		int l = -1, r = arr.length;
		while(r - l > 1) {
			int m = l + (r-l)/2;
			if(arr[m] <= key)
				l = m;
			else
				r = m;
		}
		if(arr[l] == key) return l;
		if(arr[r] == key) return r;
		return -1;
	}
	
	
	/* Variation 2 first or leftmost occurrence
	 * Invariant: A[r] >= key and A[l] > key
	 * This case has problem when key is at index 0. to do so start l = -1 
	 * Also requires check after while loop
	 */ 
	public int leftmostposition(int []arr, int key) {
		if(arr == null || arr.length == 0)
			return -1;
		
		if(arr[0] == key)
			return 0;
		int l = -1, r = arr.length -1;
		while(r - l > 1) {
			int m = l + (r-l)/2;
			if(arr[m] < key)
				l = m;
			else
				r = m;
		}
		
		if(arr[r] == key) return r;
		return -1;
	}
	
	
	/* Variation 3 Rightmost occurrence
	 * Invariant: A[l] <= key and A[r] > key
	 * Boundary Condition :
	 * 1. arr[n-1] == key to do so start r = arr.length
	 * 2. Also requires check after while loop if element is present in array
	 */
	
	public int rightmostindex(int []arr, int key) {
		if(arr == null || arr.length == 0)
			return -1;
		if(arr[arr.length-1] == key)
			return arr.length-1;
		int l = 0, r = arr.length;
		while(r - l > 1) {
			int m = l + (r-l)/2;
			if(arr[m] <= key)
				l = m;
			else
				r = m;
		}		
		if(arr[l] == key) return l;
		return -1;
	}
	
	/*
	 * Variation 4 Floor
	 * Invariant: A[l] <= key and A[r] > key
	 * Same invariant as Rightmost Value
	 * Problem :- Key or duplicate keys present in the array
	 * Here a value needs to be returned thus arr[l]
	 * In case of duplicates and key present in array l might point to key itself 
	 */
	public int floor(int []arr, int key) {
		if(arr == null || arr.length == 0)
			return -1;
		
		int l = -1, r = arr.length -1;
		while(r - l > 1) {
			int m = l + (r-l)/2;
			if(arr[m] <= key)
				l = m;
			else
				r = m;
		}
		//if(arr[l] < key)
		//In case of duplicates and key present in array l might point to key itself 
		while(l >= 0 && arr[l] == key)
			l--;
		if(l >= 0 && arr[l] < key)
			return arr[l];
		return -1;
	}
	
	/* Variation 5 Ceil Value
	 * Invariant: A[r] >= key and A[l] > key
	 * Same as Left Most index
	 * Here a value needs to be returned thus arr[r]
	 * Handle case :- Key present with or without duplicates
	 */
	
	public int ceil(int []arr, int key) {
		if(arr == null || arr.length == 0)
			return -1;
		
		int l = 0, r = arr.length -1;
		while(r - l > 1) {
			int m = l + (r-l)/2;
			if(arr[m] < key)
				l = m;
			else
				r = m;
		}
		while(r < arr.length && arr[r] == key)
			r++;
		if(r <= arr.length-1 && arr[r] > key) return arr[r];
		return -1;
	}
	
	/*int ceil(int []starts, int val) {
    	int l = 0, r = starts.length-1;
    	while(l < r) {
    		int m = l + (r-l)/2;
    		if(starts[m] < val)
    			l = m+1;
    		else
    			r = m;
    	}
		return starts[l];
    }*/
	
	
	/* Variation 6 
	 * 153. Find Minimum in Rotated Sorted Array
	 * 154. Find Minimum in Rotated Sorted Array II
	 * Minimum Element in sorted and rotated Array
	 * Element guaranteed to be present
	 * check 162. Find Peak Element 1D 2D method same logic compare with right element to mid
	 */
	public int min(int []arr) {
		if(arr == null || arr.length == 0)
			return -1;
		
		int l = 0, r = arr.length -1;
		while(l <= r) {
			if(l == r) return l;
			int m = l + (r-l)/2;
			if(arr[r] <= arr[m])
				l = m+1;
			else
				r = m;
		}
		return l;
	}
	
	// Min with duplicates worst case 0(N)
	public int findMin(int[] arr) {
		if(arr== null || arr.length == 0)
			return -1;
		int l = 0, r = arr.length-1;
		while(l <= r) {
			if(l == r) return arr[l];
			int m = (l+r)/2;
			if(arr[m] < arr[r])
				r = m;
			else if(arr[m] > arr[r])
				l = m+1;
			else r--;
		}
		return arr[l];
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		
		//Variation 1 Contains int array
		System.out.println("1: " + s.contains(new int[] { 2, 3, 3, 5, 5, 5, 6, 6 }, 2));
		System.out.println("2: " + s.contains(null, 5));
	    System.out.println("3: " + s.contains(new int[] {}, 5));
	    System.out.println("4: " + s.contains(new int[] {1}, 5));
	    System.out.println("5: " + s.contains(new int[] {7}, 5));
	    System.out.println("6: " + s.contains(new int[] {5}, 5));
	    System.out.println("7: " + s.contains(new int[] {5, 7, 9}, 5));
	    System.out.println("8: " + s.contains(new int[] {2, 3, 5}, 5));
	    System.out.println("9: " + s.contains(new int[] {5, 7}, 5));
	    System.out.println("10: " + s.contains(new int[] {2, 5}, 5));
	    // key in
	    System.out.println("11: " + s.contains(new int[] {1, 5, 7}, 5));
	    System.out.println("12: " + s.contains(new int[] {1, 2, 5, 7}, 5));
	    System.out.println("13: " + s.contains(new int[] {1, 5, 7, 9}, 5));
	    // key not in
	    System.out.println("14: " + s.contains(new int[] {2, 3, 7}, 5));
	    System.out.println("15: " + s.contains(new int[] {3, 7, 9}, 5));
	    System.out.println("16: " + s.contains(new int[] {1, 7}, 5));
	    
	    System.out.println();
	    System.out.println("#####################leftMostIndex###################");
	    System.out.println();
	    
	    //Variation 2 first or leftmost occurrence
	    
	    System.out.println("1: " + s.leftmostposition(null, 5));
	    System.out.println("2: " + s.leftmostposition(new int[] {}, 5));
	    // not in
	    System.out.println("3: " + s.leftmostposition(new int[] {1}, 5));
	    System.out.println("4: " + s.leftmostposition(new int[] {1, 2, 3}, 5));
	    System.out.println("5: " + s.leftmostposition(new int[] {6, 7, 8}, 5));
	    // in
	    System.out.println("6: " + s.leftmostposition(new int[] {5}, 5));
	    System.out.println("7: " + s.leftmostposition(new int[] {1, 2, 5}, 5));
	    System.out.println("8: " + s.leftmostposition(new int[] {5, 6, 7}, 5));
	    System.out.println("9: " + s.leftmostposition(new int[] {3, 5, 7}, 5));
	    // duplicated
	    System.out.println("10: " + s.leftmostposition(new int[] {5, 5, 9}, 5));
	    System.out.println("11: " + s.leftmostposition(new int[] {2, 5, 5}, 5));

	    System.out.println("12: " + s.leftmostposition(new int[] {1, 2, 5, 5, 5, 5, 5, 5}, 5));
	    System.out.println("13: " + s.leftmostposition(new int[] {5, 5, 5, 5, 5, 5, 7, 8}, 5));
	    System.out.println("14: " + s.leftmostposition(new int[] {1, 2, 5, 5, 5, 5, 5, 5, 7, 8}, 5));
	    // no loop
	    System.out.println("15: " + s.leftmostposition(new int[] {5, 9}, 5));
	    System.out.println("16: " + s.leftmostposition(new int[] {2, 5}, 5));
	    
	    
	    //Variation 3 Rightmost occurrence
	    System.out.println();
	    System.out.println("#####################RightMostIndex###################");
	    System.out.println();
	    
	    System.out.println("1: " + s.rightmostindex(null, 5));
	    System.out.println("2: " + s.rightmostindex(new int[] {}, 5));
	    // not in
	    System.out.println("3: " + s.rightmostindex(new int[] {1}, 5));
	    System.out.println("4: " + s.rightmostindex(new int[] {1, 2, 3}, 5));
	    System.out.println("5: " + s.rightmostindex(new int[] {6, 7, 8}, 5));
	    // in
	    System.out.println("6: " + s.rightmostindex(new int[] {5}, 5));
	    System.out.println("7: " + s.rightmostindex(new int[] {1, 2, 5}, 5));
	    System.out.println("8: " + s.rightmostindex(new int[] {5, 6, 7}, 5));
	    System.out.println("9: " + s.rightmostindex(new int[] {3, 5, 7}, 5));
	    // duplicated
	    System.out.println("10: " + s.rightmostindex(new int[] {5, 5, 9}, 5));
	    System.out.println("11: " + s.rightmostindex(new int[] {2, 5, 5}, 5));

	    System.out.println("12: " + s.rightmostindex(new int[] {1, 2, 5, 5, 5, 5, 5, 5}, 5));
	    System.out.println("13: " + s.rightmostindex(new int[] {5, 5, 5, 5, 5, 5, 7, 8}, 5));
	    System.out.println("14: " + s.rightmostindex(new int[] {1, 2, 5, 5, 5, 5, 5, 5, 7, 8}, 5));
	    // no loop
	    System.out.println("15: " + s.rightmostindex(new int[] {5, 9}, 5));
	    System.out.println("16: " + s.rightmostindex(new int[] {2, 5}, 5));
	    
	    
	    //Variation 4 Floor Value
	    System.out.println();
	    System.out.println("#####################Floor Value###################");
	    System.out.println();
	    
	    System.out.println("1: " + s.floor(null, 5));
	    System.out.println("2: " + s.floor(new int[] {}, 5));
	    System.out.println("3: " + s.floor(new int[] {1}, 5));
	    System.out.println("4: " + s.floor(new int[] {7}, 5));
	    System.out.println("5: " + s.floor(new int[] {5}, 5));
	    System.out.println("6: " + s.floor(new int[] {5, 7, 9}, 5));
	    System.out.println("7: " + s.floor(new int[] {2, 3, 5}, 5));
	    System.out.println("8: " + s.floor(new int[] {5, 7}, 5));
	    System.out.println("9: " + s.floor(new int[] {2, 5}, 5));
	    // key in
	    System.out.println("10: " + s.floor(new int[] {1, 5, 7}, 5));
	    System.out.println("11: " + s.floor(new int[] {1, 2, 5, 7}, 5));
	    System.out.println("12: " + s.floor(new int[] {1, 1, 5, 5, 7, 7, 9}, 5));
	    // key not in
	    System.out.println("13: " + s.floor(new int[] {2, 3, 7}, 5));
	    System.out.println("14: " + s.floor(new int[] {3, 7, 9}, 5));
	    System.out.println("15: " + s.floor(new int[] {1, 7}, 5));
	    
	    
	    //Variation 5 Ceil Value
	    System.out.println();
	    System.out.println("#####################Ceil Value###################");
	    System.out.println();
	    
	    System.out.println("1: " + s.ceil(null, 5));
	    System.out.println("2: " + s.ceil(new int[] {}, 5));
	    System.out.println("3: " + s.ceil(new int[] {1}, 5));
	    System.out.println("4: " + s.ceil(new int[] {7}, 5));
	    System.out.println("5: " + s.ceil(new int[] {5}, 5));
	    System.out.println("6: " + s.ceil(new int[] {5, 7, 9}, 5));
	    System.out.println("7: " + s.ceil(new int[] {2, 3, 5}, 5));
	    System.out.println("8: " + s.ceil(new int[] {5, 7}, 5));
	    System.out.println("9: " + s.ceil(new int[] {2, 5}, 5));
	    // key in
	    System.out.println("10: " + s.ceil(new int[] {1, 1, 5, 5, 7, 7}, 1));
	    System.out.println("11: " + s.ceil(new int[] {1, 2, 5, 7}, 5));
	    System.out.println("12: " + s.ceil(new int[] {1, 5, 7, 9}, 5));
	    // key not in
	    System.out.println("13: " + s.ceil(new int[] {2, 3, 7}, 5));
	    System.out.println("14: " + s.ceil(new int[] {3, 7, 9}, 5));
	    System.out.println("15: " + s.ceil(new int[] {1, 7}, 5));
	    
	    //Variation 6 Minimum in sorted and roatated array
	    System.out.println();
	    System.out.println("#####################Min Value###################");
	    System.out.println();
	    
	    System.out.println("1: " + s.min(null));
	    System.out.println("2: " + s.min(new int[] {}));
	    System.out.println("3: " + s.min(new int[] {1}));
	    System.out.println("4: " + s.min(new int[] {1, 2, 3}));

	    System.out.println("5: " + s.min(new int[] {7, 8, 6}));
	    System.out.println("6: " + s.min(new int[] {8, 6, 7}));
	    System.out.println("7: " + s.min(new int[] {2, 3, 5, 0, 1}));
	    System.out.println("8: " + s.min(new int[] {4, 5, 0, 1, 2}));
	    
	    System.out.println("###############Min Value with Duplicates############");
	    System.out.println("9:" + s.findMin(new int[] {2,2,2,0,1}));
	    System.out.println("10:" + s.findMin(new int[] {1,3,5}));
	    System.out.println("11:" + s.findMin(new int[] {1,3,3}));
	    System.out.println("12:" + s.findMin(new int[] {3,3,1,3}));
	    System.out.println("13:" + s.findMin(new int[] {2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 2}));
	    System.out.println("13:" + s.findMin(new int[] {2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2}));
	}
}