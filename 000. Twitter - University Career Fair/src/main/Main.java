package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/discuss/interview-question/374846/Twitter-or-OA-2019-or-University-Career-Fair
class Solution {
	public int maxevent(int []arrival, int []duration) {
		List<int []> list = new ArrayList<int[]>();
		int n = arrival.length;
		for(int i = 0;i < n;i++)
			list.add(new int[] {arrival[i], duration[i]});
		// sort based on when leaving so that maximum slot is given to an event
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]+o1[1]-o2[0]-o2[1];
			}
		});
		int ans = 0, end = 0;
		
		for(int i = 0;i < n;i++) {
			if(list.get(i)[0] >= end) {
				ans++;
				end = list.get(i)[0] + list.get(i)[1];
			}
		}
		return ans;
	}
}

public class Main {
	public static void main(String[] args) {
		int []arrival = {1, 3, 3, 5, 7}, duration = {2, 2, 1, 2, 1};
		System.out.println(new Solution().maxevent(arrival, duration));
	}
}