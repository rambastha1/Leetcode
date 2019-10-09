package main;

import java.util.HashMap;
import java.util.LinkedList;
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
	Map<String, int[]> dir;

	private final int width;
	private final int height;
	private final int[][] food;

	private LinkedList<int[]> snake;
	private int score;
	// food index
	private int index;

	/**
	 * Initialize your data structure here.
	 * 
	 * @param width - screen width
	 * @param height - screen height
	 * @param food - A list of food positions E.g food = [[1,1], [1,0]] means the first food is
	 *        positioned at [1,1], the second is at [1,0].
	 */
	public SnakeGame(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;
		snake = new LinkedList<>();
		snake.add(new int[] {0, 0});
		score = 0;
		index = 0;
		dir = new HashMap<>();
		dir.put("U", new int[] {-1,0});
		dir.put("R", new int[] {0,1});
		dir.put("D", new int[] {1,0});
		dir.put("L", new int[] {0,-1});
	}

	/**
	 * Moves the snake.
	 * 
	 * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
	 * @return The game's score after the move. Return -1 if game over. Game over when snake crosses
	 *         the screen boundary or bites its body.
	 */
	public int move(String direction) {
		int[] head = snake.getFirst();
		int[] inc = dir.get(direction);
		int[] newHead = {head[0] + inc[0], head[1] + inc[1]};
		
		if(!isvalid(newHead)) {
			return -1;
		} else {
			if(index < food.length && newHead[0] == food[index][0] && newHead[1] == food[index][1]) {
				snake.addFirst(newHead);
				index++;
				return ++score;
			} else {
				snake.removeLast();
				for(int[] b: snake) {
					// check if crashes with itself
					if(newHead[0] == b[0] && newHead[1] == b[1]) {
						return -1;
					}
				}
				snake.addFirst(newHead);
			}
		}
		return score;
	}
	
	private boolean isvalid(int[] p) {
		return (p[0] >= 0 && p[0] < height && p[1] >= 0 && p[1] < width);
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
