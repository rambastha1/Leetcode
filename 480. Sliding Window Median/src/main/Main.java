package main;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.TreeSet;
/* brute force 0(nk) for each window find len/2 smallest number using quick select for odd and two for even length window
 * we could use 2 pq as in 295 but pq.remove is 0(k) thus overall complexity is 0(nk)
 * use 2 treeset for small and large treeset remove is 0(lgn) thus overall complexity is 0(nlgk) 
 * add to large/small -> balance -> after window passes remove from small/large -> balance
 * median is similar peek of small or both/2
 * 
 * we need minimum of numbers larger than num and maximum of numbers smaller than num for median
 * https://leetcode.com/problems/sliding-window-median/discuss/96346/Java-using-two-Tree-Sets-O(n-logk)
 */
class Solution {
    static class myInteger{
        int val;
        int index;
        myInteger(int val,int index){
            this.val = val;
            this.index = index;
        }
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        TreeSet<myInteger> minheap = new TreeSet<>(new Comparator<myInteger>(){
               public int compare(myInteger a,myInteger b){
                   if(a.val!=b.val){
                       if(a.val<b.val){
                           return -1;
                       }else{
                           return 1;
                       }
                   }else{
                       return a.index-b.index;
                   }
               }
        });
        TreeSet<myInteger> maxheap = new TreeSet<>(new Comparator<myInteger>(){
               public int compare(myInteger a,myInteger b){
                     if(a.val!=b.val){
                          if(a.val<b.val){
                              return -1;
                          }else{
                              return 1;
                          }
                     }else{
                         return a.index - b.index;
                     }
               }
        });
        
        Deque<myInteger> deque  = new ArrayDeque<>();
        double[] res = new double[nums.length-k+1];
        for(int i=0;i<k;i++){
            myInteger temp = new myInteger(nums[i],i);
            deque.offer(temp);
            maxheap.add(temp);
        }
        balance(minheap,maxheap);
        //System.out.println("size of minheap :" + minheap.size() + " and the size of maxheap is : " + maxheap.size());
        
        res[0] = getmedian(minheap,maxheap);
        int p=1;
        for(int i=k;i<nums.length;i++){
            myInteger removeEle = deque.pollFirst();
            if(minheap.contains(removeEle)){
                minheap.remove(removeEle);
            }else{
                maxheap.remove(removeEle);
            }
            myInteger newEle = new myInteger(nums[i],i);
            deque.offer(newEle);
            maxheap.add(newEle);
            minheap.add(maxheap.pollLast());
            balance(minheap,maxheap);
            res[p++] = getmedian(minheap,maxheap);
        }
        return res;
        
    }
    
    public double getmedian(TreeSet<myInteger> minHeap,TreeSet<myInteger> maxHeap){
        if(minHeap.size()>maxHeap.size()){
            return (double)minHeap.first().val;
        }
        return ((double)minHeap.first().val+(double)maxHeap.last().val)/2.0;
    }
    
    
    public void balance(TreeSet<myInteger> minHeap,TreeSet<myInteger> maxHeap){
          while(maxHeap.size()>minHeap.size()){
              minHeap.add(maxHeap.pollLast());
          }
          while(maxHeap.size()<minHeap.size()-1){
              maxHeap.add(minHeap.pollFirst());
          }
    }
}

public class Main {
	public static void main(String[] args) {
		int []nums = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		System.out.println(Arrays.toString(new Solution().medianSlidingWindow(nums, k)));
	}
}
