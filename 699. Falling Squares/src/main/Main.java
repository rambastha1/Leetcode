package main;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
// https://www.geeksforgeeks.org/interval-tree/
// https://leetcode.com/problems/falling-squares/discuss/108772/Java-TreeMap-solution.
// https://leetcode.com/problems/falling-squares/discuss/108791/C%2B%2B-map-with-explanation-O(nlogn)-solution-36ms
class Solution {
    public static class Metadata {
        public int right, height;
        public Metadata(int right, int height) {
            this.right = right;
            this.height = height;
        }
    }
    TreeMap<Integer, Metadata> map = new TreeMap<Integer, Metadata>(); //left as key, "right + height" as value
    
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> result = new LinkedList<Integer>();
        Integer globalMax = 0;
        for(int[] sq: positions) {
            int left = sq[0];
            int right = left + sq[1];
            int maxHeight = 0;   //temporary max height for those intervals that intersect with the new falling square.
            Integer key = map.lowerKey(right);
            // key is left index of square
            //walk through  intervals that overlap with current falling square 
            while (key != null && !(left >= map.get(key).right || key >= right)) { 
                Metadata original = map.get(key);
                maxHeight = Math.max(maxHeight, original.height); // update the temporary maxHeight.
                map.remove(key);                       // remove the original entry.
                /* this is like l1, r1, left, right, l2, r2
                 * we need to create overlap of l1 to r2
                 * if key i.e l1 < left create l1-left
                 * at the end of while loop we add left-right
                 */
                // left part is beyond the current falling square, insert a new one
                if(key < left) {                       
                    map.put(key, new Metadata(left, original.height));
                }
                // right part is beyond the current falling square, insert a new one
                if(original.right > right) {           
                    map.put(right, original);
                }
                key = map.lowerKey(key);
            }
            
            map.put(left, new Metadata(right, maxHeight + right - left));  // insert the interval for the current falling square.
            globalMax = Math.max(globalMax, maxHeight + right - left);     // update the global maxHeight.
            result.add(globalMax);
        }
        return result;
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] positions = {{1,2}, {2,3}, {6,1}};
		System.out.println(new Solution().fallingSquares(positions));
	}
}
