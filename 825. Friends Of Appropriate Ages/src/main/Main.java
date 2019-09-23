package main;
// https://leetcode.com/problems/friends-of-appropriate-ages/discuss/127341/10ms-concise-Java-solution-O(n)-time-and-O(1)-space
class Solution {
    public int numFriendRequests(int[] ages) {
    	if(ages == null || ages.length <= 1)
    		return 0;
    	int n = ages.length, ans = 0;
    	/* count[i] is count of people with age i.
    	 * A will send request iff B>=0.5A+8 && B<=A
    	 */
    	int []count = new int[121], sum = new int[121];
    	
    	for(int i = 0;i < n;i++)
    		count[ages[i]]++;
    	for(int i = 1;i < 121;i++)
    		sum[i] = sum[i-1] + count[i];
    	    	
    	//  0.5 * i + 7 >= i when i < 15.
    	for(int i = 15;i < 121;i++) {
    		if(count[i] == 0)
    			continue;
    		// get count of people in appropriate age range
    		int val = sum[i] - sum[i/2+7];
    		//people will not friend request themselves, so  - count[i]
    		// person with age i will send request to val people
    		ans += val*count[i] - count[i];
    	}
    	return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		//int []ages = {16,16};
		//int []ages = {16,17,18};
		int []ages = {20,30,100,110,120};
		System.out.println(new Solution().numFriendRequests(ages));
	}
}
