package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/smallest-sufficient-team/discuss/334669/Java-DFS-search-with-Pruning-using-Bitwise-tricks-4ms-beating-98
class Solution {
	
	List<Integer> res = new ArrayList<>();
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
    	// skill -> index
    	Map<String, Integer> map = new HashMap<>();
    	int n = 0;
    	for(String str : req_skills)
    		map.put(str, n++);
    	
    	int []person_skill = new int[people.size()];
    	for(int i = 0;i < people.size();i++) {
    		for(String skill : people.get(i)) {
    			int index = map.get(skill);
    			// represent person skill as summation of skills
    			person_skill[i] += 1<<index;
    		}
    	}
    	
    	// total subset of skills = 2^(req_skills.length)
    	dfs(new ArrayList<>(), person_skill, n, 0);
    	int []ans = new int[res.size()];
    	for(int i = 0;i < res.size();i++)
    		ans[i] = res.get(i);
    	return ans;
    }
    
    private void dfs(List<Integer> curr, int []person_skill, int n, int count) {
    	// if all the skills have been covered
    	if(count == (1<<n) - 1) {
    		if(res.size() == 0 || curr.size() < res.size()) {
    			res = new ArrayList<>(curr);
    		}
    		return;
    	}
    	
    	// if current size of team is larger than res size return
    	if(res.size() != 0 && curr.size() >= res.size())
    		return;
    	
    	// find the next required skills to be covered
    	int skill = 0;
    	// If count has this skill bit set means this current group has already covered this skill
    	while(((count>>skill) & 1) == 1)
    		skill++;
    	// check for each who has this required skill and check for smallest length 
    	for(int i = 0;i < person_skill.length;i++) {
    		int person = person_skill[i];
    		// this person has this skill
    		if(((person>>skill) & 1) == 1) {
    			curr.add(i);
    			// count|person will contain all the skills of running count + this person
    			dfs(curr, person_skill, n, count|person);
    			curr.remove(curr.size()-1);
    		}
    	}
    }
}

public class Main {
	public static void main(String[] args) {
		String []req_skills = {"algorithms","math","java","reactjs","csharp","aws"};
		List<List<String>> people = Arrays.asList(Arrays.asList("algorithms","math","java"),
				Arrays.asList("algorithms","math","reactjs"),Arrays.asList("java","csharp","aws"),
				Arrays.asList("reactjs","csharp"),Arrays.asList("csharp","math"),Arrays.asList("aws","java"));
		System.out.println(Arrays.toString(new Solution().smallestSufficientTeam(req_skills, people)));
		
	}
}
