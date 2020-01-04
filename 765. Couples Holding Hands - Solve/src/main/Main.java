package main;
/* https://leetcode.com/problems/couples-holding-hands/discuss/117520/Java-union-find-easy-to-understand-5-ms
 * https://leetcode.com/problems/couples-holding-hands/discuss/160104/Union-find-understand-in-60-seconds-beats-99.6
 * https://leetcode.com/problems/couples-holding-hands/discuss/113362/JavaC%2B%2B-O(N)-solution-using-cyclic-swapping
 * https://leetcode.com/problems/couples-holding-hands/discuss/113362/JavaC%2B%2B-O(N)-solution-using-cyclic-swapping
 * The greedy method derives from cycle of graph itself
 */
class Solution {
	
	int swap = 0;
    public int minSwapsCouples(int[] row) {
    	// Each couple is a node of graph thus total nodes = length/2;
    	int n = row.length/2;
    	int []parent = new int[n];
    	for(int i = 0;i < n;i++)
    		parent[i] = i;
    	
    	/* It unions only 1 time when they are two completely different groups thus proves minimum swap
    	 * ideally person 1 of group i should sit at 2*i and person 2 at 2*i+1
    	 * i.e next to each other
    	 * since we have n = row.length/2 nodes, divide by 2 to find parent
    	 */
    	for(int i = 0;i < n;i++) {
    		int person1 = find(parent, row[2*i]/2);
    		int person2 = find(parent, row[2*i+1]/2);
    		// This means row[2*i]/2 and row[2*i+1]/2 belongs to different couple
    		// thus swap
    		if(person1 != person2) {
    			union(parent, person1, person2);
    		}
    	}
    	return swap;
    }
    
    private void union(int []parent, int i, int j) {
    	int a = find(parent, i);
    	int b = find(parent, j);
    	if(a != b) {
    		parent[b] = a;
    		swap++;
    	}
    }
    
    private int find(int []parent, int i) {
    	if(parent[i] != i)
    		parent[i] = find(parent, parent[i]);
    	return parent[i];
    }
    
    // greedy method
    // https://leetcode.com/problems/couples-holding-hands/discuss/113359/Java-AC-O(n)-greedy-solution.
    public int minSwapsCouples1(int[] row) {
    	int n = row.length;
    	int []pos = new int[n];
    	for(int i = 0;i < n;i++)
    		pos[row[i]] = i;
    	
    	int swap = 0;
    	// because at i+1 patner should sit 
    	for(int i = 0;i < n;i+=2) {
    		// why?
    		int j = row[i]%2 == 0?row[i]+1:row[i]-1;
    		if(row[i+1] != j) {
    			swap(row, pos, i+1, pos[j]);
    			swap++;
    		}
    	}
    	return swap;
    }
    
    private void swap(int []row, int []pos, int i, int j) {
    	int temp = row[i];
    	pos[temp] = j;
    	pos[row[j]] = i;
    	row[i] = row[j];
    	row[j] = temp;
    }
}

public class Main {
	public static void main(String[] args) {
		int []row = {5,4,2,6,3,1,0,7};
		System.out.println(new Solution().minSwapsCouples(row));
		System.out.println(new Solution().minSwapsCouples1(row));
	}
}
