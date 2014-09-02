
package com.leetcode;


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
	public int search(int[] A, int target) {
        
    }
	
}
