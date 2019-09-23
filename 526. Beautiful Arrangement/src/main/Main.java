package main;

class Solution {
	
	//DFS generic
	public int countArrangement(int N) {
    	if(N == 0) return 0;
    	dfs(N, 1, new boolean[N+1]);    	
    	return count;
    }
    //if use list instead of array !list.contains(i)
    void dfs(int N, int index, boolean []visited) {
    	if(index > N) {
    		count++;
    		return;
    	}
    	for(int i = 1;i <= N;i++) {
    		if(!visited[i] && (index%i == 0 || i%index == 0)) {
    			visited[i] = true;
    			dfs(N, index+1, visited);
				visited[i] = false;
    		}
    	}
    }
	
	//Swap method is better in permutation
	int count = 0;
	public int countArrangement1(int N) {
		if(N == 0)
			return 0;
		int []nums = new int[N+1];
		for(int i = 0;i <= N;i++)
			nums[i] = i;
		util(nums, N, N);
		return count;
	}
	
	void util(int []nums, int N, int index) {
		if(index == 0) {
			count++;
			return;
		}
		//When forward loop return 4 why?
		for(int i = index;i > 0;i--) {
			swap(nums, index, i);
			if(nums[index]%index == 0 || index%nums[index] == 0)
				util(nums, N, index-1);
			swap(nums, index, i);
		}
			
	}
	
	void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

public class Main {
	public static void main(String[] args) {
		int N = 3;
		System.out.println(new Solution().countArrangement(N));
	}
}