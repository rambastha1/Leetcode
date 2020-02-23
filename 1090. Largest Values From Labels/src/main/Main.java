package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
    	int n = values.length;
    	List<int[]> list = new ArrayList<>();
    	for(int i = 0;i < n;i++)
    		list.add(new int[] {values[i], labels[i]});
    	
    	Collections.sort(list, new Comparator<int[]>() {
    		@Override
    		public int compare(int []a, int []b) {
    			return a[0]==b[0]?a[1]-b[1]:b[0]-a[0];
    		}
		});
    	
    	int []count = new int[20001];
    	int ans = 0, num = 0;
    	for(int []arr : list) {
    		if(num == num_wanted)
    			break;
    		int val = arr[0], label = arr[1];
    		if(count[label] < use_limit) {
    			num++;
    			count[label]++;
    			ans += val;
    		}
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []values = {5,4,3,2,1}, labels = {1,1,2,2,3};
		int num_wanted = 3, use_limit = 1;
		System.out.println(new Solution().largestValsFromLabels(values, labels, num_wanted, use_limit));
	}
}
