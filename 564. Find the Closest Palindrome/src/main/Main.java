package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// https://leetcode.com/problems/find-the-closest-palindrome/discuss/102391/Python-Simple-with-Explanation
class Solution {
	public String nearestPalindromic(String n) {
        if(n.length()==1) {
             return String.valueOf(Integer.parseInt(n)-1);
        }
        int halflen = (n.length()+1)/2;
        long half = Long.parseLong(n.substring(0,halflen));
        
        /* eg. n = 99999 in this case closest would be 100001 larger in length
         * if n = 100023 closest = 99999 smaller in length
         * if no change in length find first half and take reverse
         */
        List<Long> candidates = new ArrayList<>();
        candidates.add(getAllNine(n.length()));
        candidates.add(getAllNine(n.length()-1));
        candidates.add(getOneZero(n.length()));
        candidates.add(getOneZero(n.length()+1));
       
        getcandidates(candidates,half,n.length());
        long dif = Long.MAX_VALUE;
        long r = Long.parseLong(n);
        String res = "";
        //return smaller value if there is a tie
        Collections.sort(candidates);
        for(Long ele:candidates){
            if(ele == r){
                continue;
            }
            if(dif>Math.abs(ele-r)){
                dif = Math.abs(ele-r);
                res = String.valueOf(ele);
            }
        }
        return res;
   }
   
   public void getcandidates(List<Long> ans,long m,int len){
       List<Long> reg = new ArrayList<>();
       reg.add(m);
       reg.add(m-1);
       reg.add(m+1);
       for(Long ele : reg){
           if(len%2==0){
             String str = String.valueOf(ele);
             str+=new StringBuilder(str).reverse().toString();
             ans.add(Long.parseLong(str));
          }else{
              String str = String.valueOf(ele);
              StringBuilder sb = new StringBuilder(str.substring(0,str.length()-1));
              str = str + sb.reverse().toString();
              ans.add(Long.parseLong(str));
           }
       }
   }
   
   public Long getAllNine(int n){
       String str="";
       for(int i=0;i<n;i++){
           str+='9';
       }
       return Long.parseLong(str);
   }
   
   public Long getOneZero(int n){
       return (long)Math.pow(10,n-1)+1;
   }
}

public class Main {
	public static void main(String[] args) {
		String n = "23456";
		System.out.println(new Solution().nearestPalindromic(n));
	}
}
