package main;

import java.util.Comparator;
import java.util.PriorityQueue;

/* On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000
Note:

stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.
 * 
 */

/* mid here is actually a guess of the result, for example, we guess the Max Distance is 1.3.
 * count+= Math.ceil((st[i + 1] - st[i]) / mid)
 * original gap / this guess = number of houses that can be added in this gap
 * total total such houses. if count > k -> guess too low l = mid else r = mid
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/discuss/113633/C%2B%2BJavaPython-Binary-Search
 */

class Solution {
    public double minmaxGasDist(int[] stations, int K) {
    	int count, N = stations.length;
        double l = 0, r = stations[N - 1] - stations[0], mid;

        while (l +1e-6 < r) {
            mid = (l + r) / 2;
            count = 0;
            for (int i = 0; i < N - 1; ++i)
                count += Math.floor((stations[i + 1] - stations[i]) / mid) ;
            if (count > K) l = mid;
            else r = mid;
        }
        return r;
    }
}

public class Main {
	public static void main(String[] args) {
		/*int []stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int K = 9;*/
		int []stations = {10,19,25,27,56,63,70,87,96,97};
		int K = 3;
		System.out.println(new Solution().minmaxGasDist(stations, K));
	}
}