package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
	public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Item dependency graph creation.
        Map<Integer,Set<Integer>> itemGraph = new HashMap<>();
        Map<Integer,Integer> itemInDegree = new HashMap<>();

        for (int i=0;i<n;i++) {
            itemGraph.putIfAbsent(i,new HashSet<>());
        }

        // Group dependency graph creation
        Map<Integer,Set<Integer>> groupGraph = new HashMap<>();
        Map<Integer,Integer> groupInDegree = new HashMap<>();

        for (int g : group) {
            groupGraph.putIfAbsent(g,new HashSet<>());
        }
        
        // add dependencies on items and group
        for (int i=0;i<beforeItems.size();i++) {
            List<Integer> list = beforeItems.get(i);
            if(list.size()!=0) {
                for (int val : list) {
                    itemGraph.get(val).add(i);
                    itemInDegree.put(i,itemInDegree.getOrDefault(i,0)+1);
                    
                    // If an item(i1) is dependent on another(i2) then its group(g1) should also be dependent on (g2)
                    int g1 = group[val];
                    int g2 = group[i];
                    if (g1 != g2 && groupGraph.get(g1).add(g2)) {
                        groupInDegree.put(g2,groupInDegree.getOrDefault(g2,0)+1);
                    }
                }
            }
        }
        
        // sort both items and groups
        List<Integer> itemOrdering = topologicalSort(itemGraph, itemInDegree, n);
        List<Integer> groupOrdering = topologicalSort(groupGraph, groupInDegree, groupGraph.size());

        // In case we find a cycle.
        if(itemOrdering.size()==0 || groupOrdering.size()==0) {
            return new int[0];
        }
        
        // final map
        Map<Integer,List<Integer>> map = new HashMap<>();

        // get items, add to group. This order will be sorted one given by kahn algorithm
        // Put items in respective buckets.
        for (int item : itemOrdering) {
            int g = group[item];
            map.putIfAbsent(g,new ArrayList<>());
            map.get(g).add(item);
        }

        int[] result = new int[n];
        int i=0;

        // add to result these items according to groups
        // Get result, by looping over group ordering.
        for (int g : groupOrdering) {
            List<Integer> list = map.get(g);
            for (int item : list) {
                result[i] = item;
                i++;
            }
        }

        return result;

    }

    // Kahn’s algorithm
    private List<Integer> topologicalSort(Map<Integer,Set<Integer>> graph,
                                          Map<Integer,Integer> inDegree,int count) {

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int key : graph.keySet()) {
            if(inDegree.getOrDefault(key,0)==0) {
                queue.add(key);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            count--;
            result.add(node);
            for (int next : graph.get(node)) {
                int val = inDegree.get(next);
                inDegree.put(next,val-1);
                if(inDegree.get(next) ==0) {
                    queue.add(next);
                }
            }
        }
        return count==0 ? result : new ArrayList<>();

    }
}

public class Main {
	public static void main(String[] args) {
		int n = 8, m = 2;
		int []group = {-1,-1,1,0,0,1,0,-1};
		List<List<Integer>> beforeItems = Arrays.asList(Arrays.asList(),Arrays.asList(6),Arrays.asList(5),
				Arrays.asList(6),Arrays.asList(3,6),Arrays.asList(),Arrays.asList(),Arrays.asList());
		System.out.println(Arrays.toString(new Solution().sortItems(n, m, group, beforeItems)));
	}
}
