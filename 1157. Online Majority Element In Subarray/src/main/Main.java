package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
// https://leetcode.com/problems/online-majority-element-in-subarray/discuss/356108/C%2B%2B-160-ms-frequency-%2B-binary-search
/* idea is to store all indices of numbers
 * check for number -> number of occurrences in query range
 * if found return candidate
 */
class MajorityChecker {
	
	// number -> list of indices
	Map<Integer, List<Integer>> map;
	Random random;
	int []a;
	// 0(N)
    public MajorityChecker(int[] arr) {
    	this.a = arr;
        map = new HashMap<>();
        random = new Random();
        for(int i = 0;i < arr.length;i++) {
        	if(!map.containsKey(arr[i]))
        		map.put(arr[i], new ArrayList<>());
        	map.get(arr[i]).add(i);
        }
    }
    // since majority element occurs more than n/2 times Probability of random picking index and it is majority > 50%
    // 0(lgN) depending on range
    public int query(int left, int right, int threshold) {
    	
    	for(int i = 0;i < 20;i++) {
    		int index = left + random.nextInt(right - left + 1);
        	int candidate = a[index];
        	List<Integer> list = map.get(candidate);
        	if(list.size() < threshold)
        		continue;
        	int l = Collections.binarySearch(list, left);
        	int r = Collections.binarySearch(list, right);
        	// if indices not found -> move it to point to index that is present in list
        	if(l < 0)
        		l = -l -1;
        	if(r < 0)
        		r = (-r - 1) - 1;
        	if(r - l + 1 >= threshold)
        		return candidate;
    	}
    	return -1;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */

public class Main {
	public static void main(String[] args) {
		int []arr = {1,1,2,2,1,1};
		MajorityChecker m = new MajorityChecker(arr);
		System.out.println(m.query(0,5,4)); // returns 1
		System.out.println(m.query(0,3,3)); // returns -1
		System.out.println(m.query(2,3,2));
	}
}
