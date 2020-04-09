package main;
/* We need to count tripplets {arr[i] < arr[j] < arr[k]} and {arr[i] > arr[j] > arr[k]} where i<j<k.
So, let's find for every j count of all i and k, so that it will follow either of above 2 conditons and summarize the counts:

Example: [13, 3, 4, 10, 7, 8]
13:

for {arr[i] < arr[j] < arr[k]} tripplets, Nothing smaller from left side.
for {arr[i] > arr[j] > arr[k]} tripplets, Nothing larger from left side.
3:

for {arr[i] < arr[j] < arr[k]} tripplets, Nothing smaller from left side.
for {arr[i] > arr[j] > arr[k]} tripplets, Nothing smaller from right side.
4:

for {arr[i] < arr[j] < arr[k]} tripplets,1 number is smaller and 3 are larger, total = 1*3 = 3 tripplets => {3, 4, 10}, {3, 4, 7}, {3, 4, 8}
for {arr[i] > arr[j] > arr[k]} tripplets, Nothing smaller from right side.
10:

for {arr[i] < arr[j] < arr[k]} tripplets,Nothing larger from right side.
for {arr[i] > arr[j] > arr[k]} tripplets, 1 number is bigger and 2 numbers are smaller, total = 1*2 = 2 tripplets => {13, 10, 7}, {13, 10, 8}
7:

for {arr[i] < arr[j] < arr[k]} tripplets, 2 numbers are smaller and 1 is larger, total = 2*1 = 2 => {3, 7, 8}, {4, 7, 8}
for {arr[i] > arr[j] > arr[k]} tripplets, Nothing smaller from right side.
8:

for {arr[i] < arr[j] < arr[k]} tripplets, Nothing larger from right side.
for {arr[i] > arr[j] > arr[k]} tripplets, Nothing smaller from right side.
Total = 3 + 2 + 2 = 7 tripplets.
 * 
 */
class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int ans = 0;
        for(int i = 0;i < n;i++) {
        	int leftsmall = 0, leftlarge = 0;
        	int rightsmall = 0, rightlarge = 0;
        	for(int j = 0;j < i;j++) {
        		if(rating[j] > rating[i])
        			leftlarge++;
        		else if(rating[j] < rating[i])
        			leftsmall++;
        	}
        	
        	for(int j = i+1;j < n;j++) {
        		if(rating[j] < rating[i])
        			rightsmall++;
        		else if(rating[j] > rating[i])
        			rightlarge++;
        	}
        	ans += leftsmall*rightlarge + leftlarge*rightsmall;
        	
        }
        return ans;
    }
}

public class Main {
	public static void main(String[] args) {
		int []rating = {2,5,3,4,1};
		System.out.println(new Solution().numTeams(rating));
	}
}
