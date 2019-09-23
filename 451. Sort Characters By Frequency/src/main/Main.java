package main;

import java.util.Arrays;
import java.util.Comparator;

import javafx.util.Pair;

class Solution {
	
	//My method good solution kudos 6ms
		class Node {
			char c;
			int count;
			public Node(char c, int count) {
				this.c = c;
				this.count = count;
			}
		}
		
		public String frequencySort(String s) {
	    	Node []count = new Node[256];
	    	for(int i = 0;i < count.length;i++)
	    		count[i] = new Node('a',0);
	    	for(char c : s.toCharArray())
	    		count[c] = new Node(c, ++count[c].count);
	    	
	    	Arrays.sort(count, new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o2.count-o1.count;
				}
			});
	    	StringBuilder sb = new StringBuilder();
	    	for(Node node : count) {
	    		for(int i = 0;i < node.count;i++)
	    			sb.append(node.c);
	    	}
	    	return sb.toString();
	    }
	
	
	// Very Smart but not interview type loops over and over again to find max
	// time 0(summation of all freq * 256)
	/*public String frequencySort(String s) {
		int []count = new int[256];
		for(char c : s.toCharArray())
			count[c]++;
		StringBuilder sb = new StringBuilder();
		int num, index;
		while(true) {
			num = 0;
			index = -1;
			for(int i = 0;i < count.length;i++) {
				if(count[i] > num) {
					index = i;
					num = count[i];
				}
			}
			
			if(num == 0 && index == -1)
				return sb.toString();
			else {
				count[index] = 0;
				for(int i = 0;i < num;i++)
					sb.append((char)index);
			}
		}
	}*/    
}

public class Main {
	public static void main(String[] args) {
		String s = "aaaccc";
		System.out.println(new Solution().frequencySort(s));
	}
}