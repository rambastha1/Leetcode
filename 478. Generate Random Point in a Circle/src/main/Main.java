package main;

import java.util.Random;
// https://meyavuz.wordpress.com/2018/11/15/generate-uniform-random-points-within-a-circle/
// https://leetcode.com/problems/generate-random-point-in-a-circle/discuss/154037/Polar-Coordinates-10-lines
class Solution {
    double radius, x_center, y_center;
    public Solution(double radius, double x_center, double y_center) {
        this.radius=radius;
        this.x_center=x_center;
        this.y_center=y_center;
    }
    
    public double[] randPoint() {
    	// Math.random returns double from 0.0 to 1.0
    	// sqrt for CDF check link - didn't understand
        double len= Math.sqrt(Math.random())*radius;
        // degree from 0 to 2*PI
        double deg= Math.random()*2*Math.PI;
        double x= x_center+len*Math.cos(deg);
        double y= y_center+len*Math.sin(deg);
        return new double[]{x,y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */

public class Main {
	public static void main(String[] args) {

	}
}
