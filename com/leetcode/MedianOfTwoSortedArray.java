package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * There are two sorted arrays A and B of size m and n respectively. 
  * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 求中位数：总个数是偶数的，中位数是位于中间位置两数的平均数；总个数为奇数，中位数为中间位置的数。
 * @author nwj
 *
 */


public class MedianOfTwoSortedArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = {2};
		int[] B = {2,3,4};
		MedianOfTwoSortedArray mts = new MedianOfTwoSortedArray();
		double m = mts.findMedianSortedArrays(A, B);
		System.out.println(m);
	}
	
	 public double findMedianSortedArrays(int A[], int B[]) {
	        int k = A.length + B.length;
	        if(k%2 == 0){
	        	return (findK(A,0,A.length-1,B,0,B.length-1,k/2+1) + findK(A,0,A.length-1,B,0,B.length-1,k/2)) / 2;
	        }else {
				return findK(A,0,A.length-1,B,0,B.length-1,k/2+1);
			}
	 }
	 //返回两个数组中第k大的元素。
	 /*
	  * 这个findK()函数写的非常经典，思路如下：
		1. 保持A是短的那一个数组，B是长的
		2. 平分k, 一半在A，一半在B （如果A的长度不足K/2,那就pa就指到最后一个）
		3. 如果pa的值 < pb的值，那证明第K个数肯定不会出现在pa之前，递归，把A数组pa之前的砍掉，同理递归砍B数组。
		4. 递归到 m == 0 （短的数组用完了） 就返回 B[k - 1], 或者k == 1（找第一个数）就返回min(A第一个数，B第一个数）
	  */

		/*	a. 如果A[n/2] == B[m/2]，那么很显然，我们的讨论结束了。A[n/2]就已经是中位数，这个和他们各自的长度是奇数或者偶数无关。
			b. 如果A[n/2] <   B[m/2]，那么，我们可以知道这个中位数肯定不在[A[0],A[n/2])这个区间内，同时也不在[B[m/2],B[m]]这个区间里面。这个时候，我们不能冲动地把[A[0],A[n/2])和[B[m/2],B[m]]全部扔掉。我们只需要把[B[m-n/2],B[m]]和[A[0],A[n/2])扔掉就可以了。（如图所示的红色线框），这样我们就把我们的问题成功转换成了如何在A[n/2]->A[n]这个长度为n/2的数组和B[1]-B[m-n/2]这个长度为m-n/2的数组里面找中位数了。问题复杂度即可下降了。
			c. 只剩下A[n/2] > B[m/2]，和b类似的，我们可以把A[n/2]->A[n]这块以及B[1]->B[n/2]这块扔掉了就行,然后继续递归。
		 */
	 public double findK(int a[], int s1, int e1, int b[], int s2, int e2, int k) {
	        int m = e1 - s1 + 1;
	        int n = e2 - s2 + 1;
	        //1.保持a的短的那个数组，b是长的
	        if (m > n)
	        	return findK(b, s2, e2, a, s1, e1, k); //a的长度比b的小。
	        //递归到m==0（短的数组用完了，就返回b[s2+k-1]）
	        if (s1 > e1) 
	        	return b[s2 + k - 1]; 
	        if (s2 > e2) 
	        	return a[s1 + k - 1];
	        //k==1，找第一个数，返回min(a[s1],b[s2])
	        if (k == 1) 
	        	return Math.min(a[s1], b[s2]);
	        //2.平分k，一半在a，一半在b，如果a的长度不足k/2，那么midA就指向最后一个
	        int midA = Math.min(k/2, m), midB = k - midA; 
	        //如果a的第midA大的元素比b的第midB大的元素小，
	        //那么删掉a的前midA个元素，在剩余的数中找第k-midA大的。
	        if (a[s1 + midA - 1] < b[s2 + midB - 1]) 
	            return findK(a, s1 + midA, e1, b, s2, e2, k - midA);
	        else if (a[s1 + midA - 1] > b[s2 + midB - 1]) 
	            return findK(a, s1, e1, b, s2 + midB, e2, k - midB);
	        else
	            return a[s1 + midA - 1];
	    }
	/*
	 * Longest Consecutive Sequence 
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

分析：找出集合中最长连续数字序列
如果排序的话，时间复杂度是O(nlogn)
只能牺牲空间复杂度：用hashmap，查询O(1)
每扫描一个数字，看+1，-1是否在hashmap中
	 */
	 public int longestConsecutive(int[] num) {
		 int len = num.length;
		 if(len==0){
			 return 0;
		 }
		 int i;
		 Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		 for(i=0; i<len; i++){
			 map.put(num[i], false);
		 }
		 
		 int longest = 0;
		 i = 0; 
		 while(i<len){			 
			 int l = 1;			 
			 map.remove(num[i]);
			 int tmp = num[i]+1;
			 while(map.containsKey(tmp)){				 
				 map.remove(tmp);
				 l++;
				 tmp++;
			 }
			 tmp = num[i]-1;
			 while(map.containsKey(tmp)){
				 map.remove(tmp);
				 l++;
				 tmp--;
			 }
			 if(l>longest){
				 longest = l;
			 }
			 i++;
		 }
		 return longest;
	 }
	
	
}
