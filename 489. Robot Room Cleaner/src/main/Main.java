package main;

import java.util.HashSet;
import java.util.Set;

/* Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot's position internally. 
You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, 
without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
 * 
 */


interface Robot {
	// Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();
 
    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();
 
    // Clean the current cell.
    public void clean();
}

/* whatever the current index is, start from (0,0) 
 * go back is turning 180 and move and then turn 180 to face current direction
 * When implementing the robot API, there are only two void methods to operate the robot to turn internally - turnLeft() and turnRight(). 
 * So the dirs here in our method is to only use as reference to track the cells in the map but not the robot itself. 
 * When the robot finds itself nowhere to go, we have to implement its internal command to ask it to turn left or right so the 
 * robot can turn. And the reason why we need (dir+1)%4 is to virtually guide ourselves on the cell map that after the robot turns right, 
 * the natural direction will be the original (direction +1) % 4 as the moving forward direction.
 * 
 * https://leetcode.com/problems/robot-room-cleaner/discuss/139057/Very-easy-to-understand-Java-solution
 */

class Solution {
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        int [][]dirs = {{0,-1}, {-1,0}, {0,1}, {1,0}};
        backtrack(robot, visited, dirs, 0, 0, 0);
    }
    
    private void backtrack(Robot robot, Set<String> visited, int [][]dirs, int x, int y, int dir) {
    	String path = x + "-" + y;
    	if(visited.contains(path))
    		return;
    	visited.add(path);
    	robot.clean();
    	
    	for(int i = 0;i < 4;i++) {
    		if(robot.move()) {
    			// if i used insted dor stack overflow exception why?
    			int X = x + dirs[dir][0];
    			int Y = y + dirs[dir][1];
    			backtrack(robot, visited, dirs, X, Y, dir);
    			goBack(robot);
    		}
    		// Do it for all direction
    		robot.turnRight();
    		// marks current facing direction, start from this one itself rather than 0-3
    		// very important to know the direction else stack overflow issue
    		// dir for robot moves using this
    		dir = (dir+1)%4;
    	}
    }
    
    public void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}

public class Main {
	public static void main(String[] args) {

	}
}