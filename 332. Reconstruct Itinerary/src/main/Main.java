package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
    	List<String> res = new ArrayList<>();
    	if(tickets == null)
    		return res;
    	// PQ will keep dest sorted
    	Map<String, PriorityQueue<String>> map = new HashMap<>();
    	for(List<String> edge : tickets) {
    		if(!map.containsKey(edge.get(0)))
    			map.put(edge.get(0), new PriorityQueue<>());
    		map.get(edge.get(0)).add(edge.get(1));
    	}
    	
    	dfs(res, map, "JFK");
    	return res;
    }
    
    void dfs(List<String> res, Map<String, PriorityQueue<String>> map, String src) {
    	while(map.containsKey(src) && !map.get(src).isEmpty()) {
    		PriorityQueue<String> pq = map.get(src);
    		String dest = pq.poll();
    		dfs(res, map, dest);
    	}
    	// important generate everything then add src at index 0
    	res.add(0, src);
    }
}

public class Main {
	public static void main(String[] args) {
		/*List<List<String>> tickets = Arrays.asList(Arrays.asList("JFK","SFO"), Arrays.asList("JFK","ATL"), 
				Arrays.asList("SFO","ATL"), Arrays.asList("ATL","JFK"), Arrays.asList("ATL","SFO"));*/
		
		/*List<List<String>> tickets = Arrays.asList(Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"), 
				Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"));*/
		
		List<List<String>> tickets = Arrays.asList(Arrays.asList("JFK","KUL"), Arrays.asList("JFK","NRT"), 
				Arrays.asList("NRT","JFK"));
		
		System.out.println(new Solution().findItinerary(tickets));
	}
}
