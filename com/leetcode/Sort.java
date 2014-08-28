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
		int[] data = {1,4,2,3,6,5,7,9,0,8};
		sort.quickSort(data, data.length, 0, data.length-1);
		for(int i=0;i<data.length;i++){
			System.out.println(data[i]);
		}
	}
}
