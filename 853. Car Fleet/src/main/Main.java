package main;

import java.util.Arrays;
import java.util.TreeMap;
// https://leetcode.com/problems/car-fleet/discuss/139850/C%2B%2BJavaPython-Straight-Forward

/* Calculate its time needed to arrive the target cur records the current biggest time (the slowest)
 * If another car needs less or equal time than cur, it can catch up this car fleet.
 * If another car needs more time, it will be the new slowest car, and becomes the new lead of a car fleet.
 */
class Solution {

	public int carFleet(int target, int[] pos, int[] speed) {
		int n = pos.length, res = 0;
		TreeMap<Integer, Double> map = new TreeMap<>();
		// key is -pos[i] because we need to process from back i.e most distant from target
		for(int i = 0;i < n;i++)
			map.put(-pos[i], (double)(target-pos[i])/speed[i]);
		
		double max = 0;
		for(Double time : map.values()) {
			if(time > max) {
				max = time;
				res++;
			}
		}
		return res;
	}
	
	public int carFleet1(int target, int[] pos, int[] speed) {
        int N = pos.length, res = 0;
        double[][] cars = new double[N][2];
        for (int i = 0; i < N; ++i)
            cars[i] = new double[] {pos[i], (double)(target - pos[i]) / speed[i]};
        Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));
        double cur = 0;
        for (int i = N - 1; i >= 0 ; --i) {
            if (cars[i][1] > cur) {
                cur = cars[i][1];
                res++;
            }
        }
        return res;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
