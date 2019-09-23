package main;
import java.util.*;

class Solution {
    
	//Time 0(2^N * N^2)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    	List<List<Integer>> res = new ArrayList<>();
        int n = graph.length;
        dfs(graph, 0, n-1, res, new ArrayList<Integer>());
        return res;
    }
    /*
     * It starts with 0, adds node to path. If path == graph.length -1 -> add to result
     * else recursively call dfs with neighboring node.
     * path.remove(path.size() - 1) works as follows:
     * once it reaches len-1, it adds to result and removes len-1 from path
     * it goes back to parent and removes it from path as well or goes to next element in parent list
     */
    void dfs(int [][]graph, int curr, int dest, List<List<Integer>> res, List<Integer> path) {
        path.add(curr);
        if (curr == dest) {
            res.add(new ArrayList<>(path));
        } else {
            for (int next : graph[curr]) {
                dfs(graph, next, dest, res, path);
            }
        }
        path.remove(path.size() - 1);
    }
}

public class Main {
	public static void main(String[] args) {
		//int [][]graph = {{1,2}, {3}, {3}, {}};
		int [][]graph = {{4,3,1}, {3,2,4}, {3}, {4}, {}};
		//int [][]graph = {{3,1}, {4,6,7,2,5}, {4,6,3}, {6,4}, {7,6,5}, {6}, {7}, {}};
		List<List<Integer>> res = new Solution().allPathsSourceTarget(graph);
		for(List<Integer> list : res)
			System.out.println(list);
	}
}