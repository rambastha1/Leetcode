package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// https://leetcode.com/problems/accounts-merge/discuss/109157/JavaC%2B%2B-Union-Find

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
    	// string -> its parent
    	Map<String, String> parent = new HashMap<>();
    	// string -> owner's name
    	Map<String, String> name = new HashMap<>();
    	// parent -> all child email 
    	Map<String, TreeSet<String>> union = new HashMap<>();
    	
    	// parent[i] = i
    	for(List<String> list : accounts) {
    		for(int i = 1;i < list.size();i++) {
    			parent.put(list.get(i), list.get(1));
    			name.put(list.get(i), list.get(0));
    		}
    	}
    	// for each list make parent of list.get(1) as parent of all list.get(i) 
    	// overall parent
    	for(List<String> list : accounts) {
    		String p = find(list.get(1), parent);
    		for(int i = 2;i < list.size();i++)
    			parent.put(find(list.get(i), parent), p);
    	}
    	
    	// union add child of overall parent
    	for(List<String> list : accounts) {
    		String p = find(list.get(1), parent);
    		if(!union.containsKey(p))
    			union.put(p, new TreeSet<>());
    		for(int i = 1;i < list.size();i++)
    			union.get(p).add(list.get(i));
    	}
    	
    	// iterate through overall parent, get each such parent get all its child 
    	// add parent at 0th index add to res
    	List<List<String>> res = new ArrayList<>();
    	for(String str : union.keySet()) {
    		List<String> emails = new ArrayList<>(union.get(str));
    		emails.add(0, name.get(str));
    		res.add(emails);
    	}
    	return res;
    }
    
    public String find(String email, Map<String, String> parent) {
    	if(parent.get(email) == email)
    		return email;
    	return find(parent.get(email), parent);
     }
}

public class Main {
	public static void main(String[] args) {
		List<String> l1 = Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com");
		List<String> l2 = Arrays.asList("John", "johnnybravo@mail.com");
		List<String> l3 = Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com");
		List<String> l4 = Arrays.asList("Mary", "mary@mail.com");
		List<List<String>> accounts = Arrays.asList(l1, l2, l3, l4);
		System.out.println(new Solution().accountsMerge(accounts));
	}
}
