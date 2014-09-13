package com.leetcode;


/*
 * 各种查找方法
 */
public class Search {
	
	/*
	 * Search for a Range 
	 * Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
二分查找法
	 */
	public int[] searchRange(int[] A, int target) {
		int len = A.length;
        int[] result = new int[2];
        if(len==1 && A[0]==target){
            return result;
        }
        result[0] = -1;
        result[1] = -1;
        int first = 0;
        int last = len;
        int low = lowwerBound(A, first, last, target);
        if(low==len || A[low]!=target){            
        	return result;
        }        
        int up = upperBound(A, low, last, target);
        result[0] = low;
        result[1] = up-1;        
        return result;
    }
	public int lowwerBound(int[] A, int first, int last, int target){
		while(first<last){
			int mid = (first+last)/2;
			if(A[mid]<target){
				first = mid+1;
			}else {
				last = mid;
			}
		}
		return first;
	}
	public int upperBound(int[] A, int first, int last, int target){
		while(first<last){
			int mid = (first+last)/2;
			if(A[mid]<=target){
				first = mid+1;
			}else {
				last = mid;
			}
		}
		return first;
	}
	
	/*
	 * Search Insert Position
	 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
	 */
	public int searchInsert(int[] A, int target) {
		int len = A.length;
        int low = lowwerBound(A, 0, len-1, target);
        if(target>A[low]){
            return low+1;
        }else{
            return low;
        }
    }
	
	
	/*
	 * Search a 2D Matrix
	 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
二分查找，只是需要将序号换成相应的矩阵的行列
	 */
	
	public boolean searchMatrix(int[][] matrix, int target){
		int m = matrix.length;
		int n = matrix[0].length;
		int low = 0;
		int high = m*n;
		while(low<high){
			int mid = (low + high)/2;
			int val = matrix[mid/n][mid%n];
			if(val == target){
				return true;
			}else if(val<target){
				low = mid + 1;
			}else{
				high = mid;
			}
		}
		return false;
    }
}
