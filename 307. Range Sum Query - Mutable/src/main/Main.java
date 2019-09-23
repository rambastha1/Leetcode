package main;

class NumArray {

	int []bit, nums;
    public NumArray(int[] nums) {
    	this.nums = nums;
        bit = new int[nums.length+1];
        for(int i = 0;i < nums.length;i++)
        	updateUtil(i, nums[i]);
    }
    
    public void update(int i, int val) {
        updateUtil(i, val - nums[i]);
        nums[i] = val;
    }
    
    public void updateUtil(int i, int val) {
    	i++;
    	while(i < bit.length) {
    		bit[i] += val;
    		i += i & (-i);
    	}
    }
    
    public int sumRange(int i, int j) {
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
		int []nums = {1,3,5};
		NumArray obj = new NumArray(nums);
		System.out.println(obj.sumRange(0, 2));
		obj.update(1, 2);
		System.out.println(obj.sumRange(0, 2));
	}
}