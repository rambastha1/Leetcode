package main;

/* Assumption string a[i] is less than INT_Max 
 * ask interviewer
 */
class Solution {
	// Time 0(N*maxlen of a[i])
    public int compareVersion(String version1, String version2) {
    	String []a1 = version1.split("\\."), a2 = version2.split("\\.");
    	int n = Math.max(a1.length, a2.length);
    	for(int i = 0;i < n;i++) {
    		Integer i1 = i < a1.length?Integer.parseInt(a1[i]):0;
    		Integer i2 = i < a2.length?Integer.parseInt(a2[i]):0;
    		int compare = i1.compareTo(i2);
    		if(compare != 0)
    			return compare;
    	}
    	return 0;
    }
}

public class Main {
	public static void main(String[] args) {
		String version1 = "1.01", version2 = "1.001";
		System.out.println(new Solution().compareVersion(version1, version2));
	}
}
