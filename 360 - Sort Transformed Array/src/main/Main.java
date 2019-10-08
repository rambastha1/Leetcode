package main;

/* 
 * Given a sorted array of integers nums and integer values a, b and c. 
 * Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.
 * The returned array must be in sorted order.
 * Expected time complexity: O(n)
 * 
 * Example:
 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 * 
 * Result: [3, 9, 15, 33]
 * 
 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 * 
 * Result: [-23, -5, 1, 7]
 */

/*
 * But the requirements in the title let us implement in O(n), then we can only find another way. 
 * In fact, this question uses a lot of mathematics about parabola learned in high school. 
 * We know that for an equation f(x) = ax2 + bx + c, if a>0, the parabola opening is up, then two 
 * The value of the end is larger than the middle, and if a < 0, the parabola opening is facing downward, 
 * and the values ​​at both ends are smaller than the middle. When a=0, it is a straight line method, 
 * which is monotonically increasing or decreasing. Then we can use this property to solve the problem. 
 * The title shows that the given array nums is ordered. If it is not ordered, 
 * I think it is difficult to have O(n) solution. Just because the input array is ordered, 
 * we can discuss it according to a: When a>0, the value at both ends is larger than the value in 
 * the middle, then we fill in the number from the result res and use two pointers. 
 * Point to the beginning and end of the nums array respectively, the two numbers pointed to 
 * are the numbers at the ends of the parabola, the larger of them are first stored in the end of res, 
 * then the pointer moves to the middle, repeat the comparison process until the res are filled full. 
 * When a<0, indicating that the values ​​at both ends are smaller than the middle, 
 * then we fill in from the front of res, using two pointers to point to the beginning and end of 
 * the nums array. The two numbers pointed to are the numbers at both ends of the parabola. 
 * The smaller of the numbers are first stored in the beginning of res, then the pointer moves 
 * to the middle, repeating the comparison process until the res are filled. When a=0, the 
 * function is monotonically increasing or decreasing, then it can be filled in from the back to 
 * the back and from the back to the front. We can combine this with a>0.
 */

/*
 * if a > 0 parabola opens up max values at end thus put res from end
 * if a < 0 parabola opens down min values at end thus put res from beginning
 */

class Solution {
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		int []res = new int[nums.length];
		int index = (a >= 0)?nums.length-1:0;
		int l = 0, r = nums.length-1;
		while(l <= r) {
			if(a >= 0) {
				res[index--] = (getvalue(nums[l], a, b, c) >= getvalue(nums[r], a, b, c))?
						getvalue(nums[l++], a, b, c):getvalue(nums[r--], a, b, c);
			} else {
				res[index++] = (getvalue(nums[l], a, b, c) >= getvalue(nums[r], a, b, c))?
						getvalue(nums[r--], a, b, c):getvalue(nums[l++], a, b, c);
			}
		}	
		print(res);
		return res;
	}
	
	public int getvalue(int val, int a,int b, int c) {
		return a*val*val + b*val + c; 
	}
	
	void print(int []res) {
		for(int i : res)
			System.out.print(i + " ");
		System.out.println();
	}
}


public class Main {
	public static void main(String[] args) {
		int []nums = {-4, -2, 2, 4}; 
		int a = 1, b = 3, c = 5;
		new Solution().sortTransformedArray(nums, a, b, c);
	}
}