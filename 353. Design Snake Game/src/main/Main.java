package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Design a Snake game that is played on a device with screen size = width x height. 
 * Play the game online if you are not familiar with the game.
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's 
 * score both increase by 1.
 * Each food appears one by one on the screen. For example, the second food will not appear until the 
 * first food was eaten by the snake.
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 * Example:
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * 
 * Snake snake = new Snake(width, height, food);
 * 
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * 
 * |S| | |
 * | | |F|
 * 
 * snake.move("R"); -> Returns 0
 * 
 * | |S| |
 * | | |F|
 * 
 * snake.move("D"); -> Returns 0
 * 
 * | | | |
 * | |S|F|
 * 
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 * 
 * | |F| |
 * | |S|S|
 * 
 * snake.move("U"); -> Returns 1
 * 
 * | |F|S|
 * | | |S|
 * 
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * 
 * | |S|S|
 * | | |S|
 * 
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */

class SnakeGame {
	
	int width, height, index;
	int [][]food;
	List<int []>snake;
	Map<String, int []> map;
	
	public SnakeGame(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;
		this.index = 0;
		snake = new ArrayList<>();
		snake.add(new int[] {0,0});
		map = new HashMap<>();
		map.put("R", new int[] {0,1});
		map.put("D", new int[] {1,0});
		map.put("L", new int[] {0,-1});
		map.put("U", new int[] {-1,0});
	}
	
	public int move(String direction) {
		int []pos = snake.get(snake.size()-1);
		int []dir = map.get(direction);
		int x = pos[0] + dir[0], y = pos[1] + dir[1];
		if(x < 0 || x >= height || y < 0 || y >= width)
			return -1;
		snake.add(new int[] {x,y});
		if(x == food[index][0] && y == food[index][1]) {
			index++;
			return index;
		} else {
			snake.remove(0);
		}
		return 0;
	}
}


public class Main {
	public static void main(String[] args) {
		int width = 3, height = 2;
		int [][]food = {{1,2}, {0,1}};
		SnakeGame s = new SnakeGame(width, height, food);
		System.out.println(s.move("R"));
		System.out.println(s.move("D"));
		System.out.println(s.move("R"));
		System.out.println(s.move("U"));
		System.out.println(s.move("L"));
		System.out.println(s.move("U"));
	}
}
