package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
/* For the current approach, the idea goes as follows. As discussed in the previous approaches, 
 * we need to sort the given coursescourses array based on the end days. Thus, we consider the courses in the ascending order of 
 * their end days. We keep a track of the current time in a timetime variable. Along with this, we also keep a track of the 
 * number of courses taken till now in countcount variable.

For each course being considered currently(let's say ith course), we try to take this course. 
But, to be able to do so, the current course should end before its corresponding end day  i.e. 𝑡𝑖𝑚𝑒+𝑑𝑢𝑟𝑎𝑡𝑖𝑜𝑛𝑖≤𝑒𝑛𝑑\day𝑖. Here, duration_iduration 
i refers to the duration of the ith course and end\_day_iend_day refers to the end day of the ith course.
If this course can be taken, we update the current time to time + duration_itime+duration i
and also increment the current countcount value to indicate that one more course has been taken.
But, if we aren't able to take the current course i.e. time + duration_i > end\_day_itime+duration i >end_day i
, we can try to take this course by removing some other course from amongst the courses that have already been taken. 
But, the current course can fit in by removing some other course, only if the duration of the course(jth) being removed duration_jduration j
is larger than the current course's duration, duration_iduration i i.e. duration_j > duration_iduration j >duration i
We are sure of the fact that by removing the jth course, we can fit in the current course, because, course_jcourse j
was already fitting in the duration available till now. Since, duration_i < duration_jduration i <duration  j
, the current course can surely take its place. Thus, we look for a course from amongst the taken courses having a 
duration larger than the current course.

But why are we doing this replacement? The answer to this question is as follows. By replacing the jth course, with the ith
course of a relatively smaller duration, we can increase the time available for upcoming courses to be taken. 
An extra duration_j - duration_iduration j−duration i time can be made available by doing so.
Now, for this saving in time to be maximum, the course taken for the replacement should be the one with the maximum duration. 
Thus, from amongst the courses that have been taken till now, we find the course having the maximum duration which should be 
more than the duration of the current course(which can't be taken).
 * In order to get maximum duration_j pq can be used
 * https://leetcode.com/articles/course-schedule-iii/
 */
class Solution {
	// time 0(nlgn) space 0(n)
    public int scheduleCourse(int[][] courses) {
    	Arrays.sort(courses, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
    	int n = courses.length, time = 0, count = 0;
    	PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    	for(int i = 0;i < n;i++) {
    		if(count == 0 || time + courses[i][0] <= courses[i][1]) {
    			count++;
    			time += courses[i][0];
    			pq.offer(courses[i][0]);
    		} else if(!pq.isEmpty() && pq.peek() > courses[i][0]) {
    			time = time - pq.poll() + courses[i][0];
    			pq.offer(courses[i][0]);
    		}
    	}
    	return count;
    }
}

public class Main {
	public static void main(String[] args) {
		int[][] courses = {{7,16},{2,3},{3,12},{3,14},{10,19},{10,16},{6,8},{6,11},{3,13},{6,16}};
		System.out.println(new Solution().scheduleCourse(courses));
	}
}
