package com.leetcode;



public class Sort {
	/*
	 * Sort Colors
	 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
	 */
	public void sortColors(int[] A) {
		//双向指针，red（0），blue（2）		
		int len = A.length;
		if(len==1){
			return;
		}
		int red = 0;
		int blue = len-1;
		while(red<=blue && A[red]==0){
			red++;
		}
		while(blue>=red && A[blue]==2){
			blue--;
		}
		int i = red;
		while(i<=blue){
			if(A[i]==0 && i>red){
				int t = A[i];
				A[i] = A[red];
				A[red] = t;
				red++;
				continue;
			}
			if (A[i]==2) {
				int t = A[i];
				A[i] = A[blue];
				A[blue] = t;
				blue--;
				continue;
			}
			i++;
		}
	}
	
	
	
	
	/*
	 * First Missing Positive 
	 * Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

A[i] = A[A[i]-1]
如果不相等，则交换
	 */
	public int firstMissingPositive(int[] A) {
        int len = A.length;
        //1、桶排序
        bucketSort(A, len);
        for(int i=0; i<len; i++){
        	if(A[i]!=i+1){
        		return i+1;
        	}
        }
        return len+1;        
    }
	public void bucketSort(int[] A, int n){
		int i = 0;
		while(i<n){
        	if(A[i]>0 && A[i]!= i+1 && A[i]-1<n && A[A[i]-1]!= A[i]){
        		int tmp = A[A[i]-1];
        		A[A[i]-1] = A[i];
        		A[i] = tmp;
        	}else {
				i++;
			}
        }
	}
	
	
	public void qSort(int[] data, int low, int high){
		if(low<high){
			int pivotloc = partition(data, low, high);
			qSort(data, low, pivotloc-1);
			qSort(data, pivotloc+1, high);
		}
	}
	public void qSort2(int[] data, int low, int high){
		if(low<high){
			int pivotloc = partition2(data, low, high);
			qSort2(data, low, pivotloc-1);
			qSort2(data, pivotloc+1, high);
		}
	}
	public int partition(int[] data, int low, int high){
		int tmp = data[low];
		int pivotkey = data[low];
		while(low<high){
			while(low<high && data[high]>=pivotkey){
				high--;
			}
			data[low] = data[high]; 
			while(low<high && data[low]<pivotkey){
				low++;
			}
			data[high] = data[low];
		}
		data[low] = tmp;
		return low;
	}
	/*
	 * 改进！
	 * 若初始记录序列按关键字有序或基本有序时，如果还拿第一个作为快排将退化为冒泡排序
	 * 改进办法：
	 */
	public int partition2(int[] data, int low, int high){
		int m = low;
		int n = high;
		int tmp = data[low];
		int pivotkey = data[low];
		int count = 0;
		while(low<high){
			while(low<high && data[high]>=pivotkey){
				if(high<n){
					if(data[high]>data[high+1]){
						int t = data[high];
						data[high] = data[high+1];
						data[high+1] = t;
						count++;
					}
				}
				high--;
			}
			data[low] = data[high]; 
			while(low<high && data[low]<pivotkey){
				if(low>m){
					if(data[low]<data[low-1]){
						int t = data[low];
						data[low] = data[low-1];
						data[low-1] = t;
						count++;
					}
				}
				
				low++;
			}

			data[high] = data[low];
		}
		data[low] = tmp;
		return low;
	}
	
	
	
	
	
	
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
		int[] data = {1,4,2,0,3,0,6,5,7,9,8};
//		int[] data = {1,3,4,2,5};
//		int[] data = {1};
//		int[] data = {0,0,1,2,3,4,5,6,7,8,9};
//		sort.bubbleSort(data);
//		sort.show(data);
		//改进后的算法1并没有减少循环的次数
//		sort.bubbleSort1(data);
//		sort.show(data);
		//改进后的算法2最好！！因为减少了i的循环次数
//		sort.bubbleSort2(data);
//		sort.show(data);
//		sort.quickSort(data, data.length, 0, data.length-1);
		sort.qSort(data, 0, data.length-1);
		int[] data2 = {1,4,2,0,3,0,6,5,7,9,8};
		sort.qSort2(data, 0, data.length-1);
		sort.show(data);
//		char[] n = {'a','n','e','t','y','a','e','a'};
//		sort.countSort(n, 26);
	}
	public void show(int[] data){
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
		System.out.println();
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
		//统计小于某元素的个数
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
	
	/*冒泡排序(交换顺序)
	 * 基本思想：让较大的数往下沉，较小的数往上冒泡。
	 * 自上而下对相邻的两个数进行比较，每当比较的两个数与排序要求相反时，就呼唤位置。
	 */
	public void bubbleSort(int[] num){
		int len = num.length;
		int i = len-1;
		int count = 0;
		while(i>0){
			for(int j=0; j<i; j++){
				if(num[j]>num[j+1]){
					int tmp = num[j];
					num[j] = num[j+1];
					num[j+1] = tmp;
				}
				count++;
			}
			i--;
		}
		System.out.println("传统冒泡排序:"+count);
	}
	/*
	 * 传统冒泡排序中每一趟排序操作只能找到一个最大值或最小值,
	 * 我们考虑利用在每趟排序中进行正向和反向两遍冒泡的方法一次可以得到两个最终值(最大者和最小者) , 从而使排序趟数几乎减少了一半。
	 */
	public void bubbleSort1(int[] num){
		int len = num.length;
		int low = 0;
		int high = len-1;
		int count = 0;
		while(low<high){
			for(int j=low;j<high;j++){
				if(num[j]>num[j+1]){
					int tmp = num[j];
					num[j] = num[j+1];
					num[j+1] = tmp;
				}
				count++;
			}
			high--;
			for(int k=high; k>low; k--){
				if(num[k-1]>num[k]){
					int tmp = num[k];
					num[k] = num[k-1];
					num[k-1] = tmp;
				}
				count++;
			}
			low++;
		}
		System.out.println("改进后的算法1："+count);
	}
	
	
	/*
	 * 设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。
	 * 由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可
	 */
	public void bubbleSort2(int[] num){
		int len = num.length;
		int count = 0;
		int i = len-1;
		while(i>0){
			int pos = 0;
			for(int j=0; j<i; j++){				
				if(num[j]>num[j+1]){
					pos = j;
					int tmp = num[j];
					num[j] = num[j+1];
					num[j+1] = tmp;
//					System.out.println(num[j]+" change "+num[j+1]);					
				}
				count++;
			}
			i = pos;
		}
		System.out.println("改进的冒泡排序2："+count);
	}
}
