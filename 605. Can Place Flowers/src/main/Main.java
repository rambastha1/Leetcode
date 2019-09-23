package main;

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
    	int i = 0;
    	for(int j = 0;j < flowerbed.length;j ++) {
    		if(flowerbed[j]==0&&(j==0||flowerbed[j-1]==0)&&(j==flowerbed.length-1||flowerbed[j+1] == 0)) {
    			flowerbed[j] = 1;
    			n--;
    		}
    	}
    	return n<=0;
    }
}

public class Main {
	public static void main(String[] args) {
		int []flowerbed = {1,0,0,0,0,1};
		int n = 2;
		System.out.println(new Solution().canPlaceFlowers(flowerbed, n));
	}
}
