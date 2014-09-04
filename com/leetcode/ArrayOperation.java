package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//对数组的一些操作
public class ArrayOperation {
	
	
//Given two sorted integer arrays A and B, merge B into A as one sorted array.
	public void merge(int A[], int m, int B[], int n) {
		//算法：从后往前合并
		if( A==null || B==null || m+n<=0){
			return;
		}
		
		int pa = m-1, pb = n-1;
		
		
		int pnew = m+n-1;
		
		while(pa>=0 && pb>=0){
			if(A[pa]>B[pb]){
				A[pnew] = A[pa];
				pa--;
				pnew--;
			}else {
				A[pnew] = B[pb];
				pb--;
				pnew--;
			}
		}
		while(pa>=0){
			A[pnew] = A[pa];
			pa--;
			pnew--;
		}
		while(pb>=0){
			A[pnew] = B[pb];
			pb--;
			pnew--;
		}
		return ;
    }
	
	//Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
	public TreeNode sortedArrayToBST(int[] num) {
        if(num==null || num.length==0){
        	return null;
        }
        return a2bst(num,0,num.length-1);       
    }
	
	public TreeNode a2bst(int[] num, int st, int ed){
		if(st>ed){
			return null;
		}
		int mid = (st+ed)/2;
		TreeNode parent = new TreeNode(num[mid]);
		if(st != ed){
			parent.left = a2bst(num, st, mid-1);
			parent.right = a2bst(num, mid+1, ed);
		}	
		return parent;
	}	
	
	//Search in Rotated Sorted Array
	/*
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
	 */
	//分析：二分查找，难度主要在于左右边界的确定
	//时间复杂度：o(logn),空间复杂度：o（1）
	public int search(int[] A, int target) {
		int len = A.length;
        int low = 0;
        int high = len-1;
        int mid;
        while(low<=high){
        	mid = (low+high)/2;
        	if(A[mid]==target){
        		return mid;
        	}
        	if(A[low]>A[mid]){
        		if(A[mid]<target && target<=A[high]){
        			low = mid + 1;
        		}else {
					high = mid - 1;
				}
        	}else {
				if(A[low]<=target && target<A[mid]){
					high = mid - 1;
				}else {
					low = mid + 1;
				}
			}
        }
        return -1;        
    }
	
	/*
	 * Search in Rotated Sorted Array II 
	 * Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
	 */
	//分析：单独处理A[low]==A[mid]的情况，让low指针向后移
	public boolean search2(int[] A, int target) {
		int len = A.length;
        int low = 0;
        int high = len-1;
        int mid;
        while(low<=high){
        	mid = (low+high)/2;
        	if(A[mid]==target){
        		return true;
        	}
        	if(A[low]==A[mid]){
        	    low++;
        	}else if(A[low]>A[mid]){
        		if(A[mid]<target && target<=A[high]){
        			low = mid + 1;
        		}else {
					high = mid - 1;
				}
        	}else {
				if(A[low]<=target && target<A[mid]){
					high = mid - 1;
				}else {
					low = mid + 1;
				}
			}
        }
        return false; 
    }
	
	
	/*
	 * Remove Duplicates from Sorted Array
	 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
	 */
	//分析：用后边的数覆盖前边重复的数值
	public int removeDuplicates(int[] A) {
        int len = A.length;
        if(len==0){
            return 0;
        }
        int index = 0;
        for(int i=1; i<len; i++){
            if(A[index]!=A[i]){
                A[++index] = A[i];
            }
        }
        return index+1;
    }
	
	/*
	 * Remove Duplicates from Sorted Array II 
	 * Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
	 */
	//分析：添加计数的变量
	public int removeDuplicates2(int[] A) {
        int len = A.length;
        if(len==0){
            return 0;
        }
        int index = 0;
        int count = 1;
        for(int i=1; i<len; i++){
        	if(A[index] == A[i]){
        		if(count<2){
                    A[++index] = A[i];
                    count++;
                }
        	}else{
        		A[++index] = A[i];
                count = 1;
            }
        }
        return index+1;
    }
	
	
	public List<List<Integer>> threeSum(int[] num) {
		int len = num.length;
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		if(len<3){
			return result;
		}
		int target = 0;
		bubble_sort(num);
		boolean f1 = false;
		for(int i=0; i<len-2; i++){
			if(f1 && num[i]==num[i-1]){
				continue;
			}
			f1 = true;
			int j=i+1;
			int k =len-1;
			boolean f2=false,f3=false;
			while(j!=k){
				if(f2 && num[j] == num[j-1]){
					j++;
//					System.out.println(j);
					continue;
					
				}				
				if(f3 && num[k] == num[k+1]){
					k--;
//					System.out.println(k);
					continue;
					
				}					
				
				if(num[i]+num[j]+num[k] == target){
					List<Integer> ls = new LinkedList<Integer>();
					ls.add(num[i]);
					ls.add(num[j]);
					ls.add(num[k]);
					result.add(ls);
				}
				
				if (num[i]+num[j]+num[k] > target) {
					k--;
					f3 = true;
				}else {
					j++;
					f2 = true;
				}				
			}			
		}
		return result;
	}
	//二分查找
	public int binarySearch2(int[] num, int start, int target){
		int len = num.length;
		if(len==0){
			return -1;
		}	
		
		int low = start;
		int high = len-1;
		
//		int count=0;
		while(low<=high){
			int mid = (low+high)/2;
			if(num[mid]==target){
				return mid;
			}else if(num[mid]<target){
				low = mid + 1;
			}else {
				high = mid - 1;
			}
//			System.out.println(++count);
		}
		return -1;
	}	
	//二分查找
	public int binarySearch(int[] num, int target){
		int len = num.length;
		if(len==0){
			return -1;
		}
		
		
		int low = 0;
		int high = len-1;
		
//		int count=0;
		while(low<=high){
			int mid = (low+high)/2;
			if(num[mid]==target){
				return mid;
			}else if(num[mid]<target){
				low = mid + 1;
			}else {
				high = mid - 1;
			}
//			System.out.println(++count);
		}
		return -1;
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
	
	public void bubble_sort(int[] a){
		//将数组a中排列成从小到大的有序的整数序列
		for(int i=a.length-1;i>0;i--){
			for(int j=0;j<i;j++){
				if(a[j]>a[j+1]){
//					System.out.println("change"+a[j]+"  "+a[j+1]);
					int temp;
					temp=a[j+1];
					a[j+1]=a[j];
					a[j]=temp;					
				}
			}
		}
	}
}
