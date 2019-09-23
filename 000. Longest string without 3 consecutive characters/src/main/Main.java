package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/* https://leetcode.com/discuss/interview-question/330356
 * Given A, B, C, find any string of maximum length that can be created such that no 3 consecutive characters are same. 
 * There can be at max A 'a', B 'b' and C 'c'.
 * Example 1:
 * Input: A = 1, B = 1, C = 6
 * Output: "ccbccacc"
 * Example 2:
 * 
 * Input: A = 1, B = 2, C = 3
 * Output: "acbcbc"
 */
// similar to 358

class Solution {
	public String generateString(Map<Character, Integer> map) {
		
		PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
		});
		pq.addAll(map.entrySet());
		
		Queue<Map.Entry<Character, Integer>> wait = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()) {
			
			Map.Entry<Character, Integer> entry = pq.poll();
						
			int len = sb.length();
			// characters are added to wait queue this should never happen
			if(sb.length() >= 2 && sb.charAt(len-1) == sb.charAt(len-2) && sb.charAt(len-1) == entry.getKey()) {
				return "Impossible";
			}
			
			if(entry.getValue() >= 2) {
				sb.append(entry.getKey());
				sb.append(entry.getKey());
				entry.setValue(entry.getValue()-2);
			} else {
				sb.append(entry.getKey());
				entry.setValue(entry.getValue()-1);
			}
			
			if(entry.getValue() > 0)
				wait.offer(entry);
			
			if(pq.isEmpty() || sb.length()%3 == 0) {
				if(!wait.isEmpty()) {
					Map.Entry<Character, Integer> w = wait.poll();
					pq.offer(w);
				}
			}
		}
		return sb.toString();
	}
}

public class Main {
	public static void main(String[] args) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 1);
        map.put('B', 1);
        map.put('C', 6);
        System.out.println(new Solution().generateString(map));
	}
}
