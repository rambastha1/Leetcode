package main;

/* https://www.youtube.com/watch?v=LPFhl65R7ww&feature=youtu.be
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java
 * Time 0(lg(min(m,n))
 */

class Solution {
	public double findMedianSortedArrays(int nums1[], int nums2[]) {
        //if input1 length is greater than switch them so that input1 is smaller than input2.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;

        int l = 0;
        int r = m;
        while (l <= r) {
            int partitionX = (l + r)/2;
            int partitionY = (m + n + 1)/2 - partitionX;

            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if ((m + n) % 2 == 0) {
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                r = partitionX - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                l = partitionX + 1;
            }
        }

        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        return -1;
    }
}

public class Main {
	public static void main(String[] args) {
		int[] nums1 = {1, 3, 8, 9, 15};
        int[] nums2 = {7, 11, 19, 21, 23, 25};
        System.out.println(new Solution().findMedianSortedArrays(nums1, nums2));
	}
}