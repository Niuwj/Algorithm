package com.leetcode;



public class Sort {
	
	
	public void quickSort(int[] data, int length, int start, int end){
		if(start==end){
			return;
		}
		int index = partition(data,length,start,end);
		if(index>start){
			quickSort(data, length, start, index-1);
		}
		if(index<end){
			quickSort(data, length, index+1, end);
		}
	}
	public int partition(int[] data, int length, int start, int end){
		if(data==null||length<=0||start<0||end>=length){
			return -1;
		}
		int index = (int)Math.random()*(end-start)+start;
		int tmp = data[end];
		data[end] = data[index];
		data[index] = tmp;
		int small = start-1;
		for(index=start;index<end;index++){
			if(data[index]<data[end]){
				small++;
				if(small!=index){
					tmp = data[small];
					data[small] = data[index];
					data[index] = tmp; 
				}
			}
		}
		small++;
		tmp = data[small];
		data[small] = data[end];
		data[end] = tmp;
		return small;
	}
	
	public static void main(String[] args){
		Sort sort = new Sort();
//		int[] data = {1,4,2,3,6,5,7,9,0,0,8};
//		sort.quickSort(data, data.length, 0, data.length-1);
//		for(int i=0;i<data.length;i++){
//			System.out.println(data[i]);
//		}
		char[] n = {'a','n','e','t','y','a','e','a'};
		sort.countSort(n, 26);
	}
	
	
	/*
	 * 计数排序
	 * 计数排序的基本思想是：统计一个数序列中小于某个元素a的个数为n,则直接把该元素a放到第n+1个位置上。当然当过有几个元素相同时要做适当的调整，因为不能把所有的元素放到同一个位置上。
	 * 计数排序假设输入的元素都是0到k之间的整数。
	 */
	public void countSort(char[] n, int k){
		int[] A = new int[26];
//		int[] a = new int[26];
		
		for(int i=0; i<n.length; i++){
			A[i] = 0;
//			a[i] = 0;
		}
		//统计字母出现次数
		for(int i=0; i<n.length; i++){
			A[n[i]-97]++;
		}
		
		for(int i=0; i<A.length;i++){
			System.out.print(A[i]+" ");
		}
		System.out.println();
		//统计小雨某元素的个数
		for(int i=1; i<26; i++){
			A[i] += A[i-1];
		}
		for(int i=0; i<A.length;i++){
			System.out.print(A[i]+" ");
		}
		System.out.println();
		char[] result = new char[n.length];
		for(int i=n.length-1; i>=0; i--){
			char tmp = n[i];
			int pos = A[tmp-97]-1;
			System.out.println("tmp:"+tmp+" pos:"+pos);
			result[pos] = tmp;
			A[tmp-97]--;
		}
		for(int i=0; i<result.length; i++){
			System.out.print(result[i]+" ");
		}
	}
}
