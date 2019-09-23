package main;

class NumArray {
	
	int []bit, nums;
    public NumArray(int[] nums) {
        this.nums = nums;
        bit = new int[nums.length+1];
        for(int i = 0;i < nums.length;i++)
        	update(i, nums[i]);
    }
    
    public void update(int i, int val) {
    	i++;
    	while(i < bit.length) {
    		bit[i] += val;
    		i += i & (-i);
    	}
    }
    
    public int sumRange(int i, int j) {
    	if(i == 0)
    		return sum(j);
    	return sum(j) - sum(i-1);
    }
    
    public int sum(int i) {
    	i++;
    	int sum = 0;
    	while(i > 0) {
    		sum += bit[i];
    		i -= i & (-i);
    	}
    	return sum;
    }
}

public class Main {
	public static void main(String[] args) {

	}
}