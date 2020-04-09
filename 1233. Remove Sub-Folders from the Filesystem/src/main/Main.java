package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> res = new ArrayList<>();
        String prev = folder[0];
        res.add(prev);
        for(int i = 1;i < folder.length;i++) {
        	String curr = folder[i];
        	if(!curr.startsWith(prev + "/")) {
        		res.add(curr);
        		prev = curr;
        	}
        }
        return res;
    }
}

public class Main {
	public static void main(String[] args) {
		String []folder = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
		System.out.println(new Solution().removeSubfolders(folder));
	}
}
