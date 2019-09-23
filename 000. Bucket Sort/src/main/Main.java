package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	void bucketsort(double []a) {
		int n = a.length;
		List<Double> []list = new ArrayList[n];
		for(int i = 0;i < n;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0;i < n;i++) {
			int index = n*(int)a[i];
			list[index].add(a[i]);
		}
		
		for(int i = 0;i < n;i++)
			Collections.sort(list[i]);
		
		int index = 0;
		for(int i = 0;i < n;i++) {
			for(int j = 0;j < list[i].size();j++) {
				a[index++] = list[i].get(j);
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		double a[] = {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};
		for(double i : a)
			System.out.print(i + " ");
		System.out.println();
		new Solution().bucketsort(a);
		for(double i : a)
			System.out.print(i + " ");
		System.out.println();
	}
}