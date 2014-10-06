package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//对数组的一些操作
public class ArrayOperation {
	
	/*
	 * gas station
	 * 
	 * 分析：
	 * 我们模拟一下过程：

a. 最开始，站点0是始发站，假设车开出站点p后，油箱空了，假设sum1 = diff[0] +diff[1] + ... + diff[p]，可知sum1 < 0；

b. 根据上面的论述，我们将p+1作为始发站，开出q站后，油箱又空了，设sum2 = diff[p+1] +diff[p+2] + ... + diff[q]，可知sum2 < 0。

c. 将q+1作为始发站，假设一直开到了未循环的最末站，油箱没见底儿，设sum3 = diff[q+1] +diff[q+2] + ... + diff[size-1]，可知sum3 >= 0。

要想知道车能否开回 q 站，其实就是在sum3 的基础上，依次加上 diff[0] 到 diff[q]，看看sum3在这个过程中是否会小于0。但是我们之前已经知道 diff[0] 到 diff[p-1] 这段路，油箱能一直保持非负，因此我们只要算算sum3 + sum1是否 <0，就知道能不能开到 p+1站了。如果能从p+1站开出，只要算算sum3 + sum1 + sum2 是否 < 0，就知都能不能开回q站了。

因为 sum1, sum2 都 < 0，因此如果 sum3 + sum1 + sum2 >=0 那么 sum3 + sum1 必然 >= 0，也就是说，只要sum3 + sum1 + sum2 >=0，车必然能开回q站。而sum3 + sum1 + sum2 其实就是 diff数组的总和 Total，遍历完所有元素已经算出来了。因此 Total 能否 >= 0，就是是否存在这样的站点的 充分必要条件。

这样时间复杂度进一步从O(2n)降到了 O(n)。

基于这个思路，可以写出更加简洁的代码：
	 */	
	public int canCompleteCircuit(int[] gas, int[] cost) {
        int gs = gas.length;
        //int cs = cost.length;
        
        //total判断全程，sum判断当前是否站点是否可以
        int total = 0;
        int sum = 0;
        int start = 0;
        for(int i=0; i<gs; i++){
        	total += (gas[i] - cost[i]);
        	if(sum<0){//发现油箱空了
        		sum = gas[i]-cost[i];
        		start = i;
        	}else {
				sum += (gas[i]-cost[i]);
			}
        }
        return total<0?-1:start;
    }
	
	
	
	
	
	
	
	/*
	 * Longest Consecutive Sequence 
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
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
	
	
	/*
	 * Set Matrix Zeroes 
	 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
	 */
	public void setZeroes(int[][] matrix) {
        //用第0行和第0列来保存数据
		
		int row = matrix.length;
		if(row==0){
			return;
		}
		int col = matrix[0].length;
		if(col==0){
			return;
		}
						
		//1.store the 0 information in the first row and first col
		boolean r0 = false;
		boolean c0 = false;
		
		for(int i=0; i<col; i++){
			if(matrix[0][i]==0){
				r0 = true;
				break;
			}
		}
		
		for(int j=0; j<row; j++){
			if(matrix[j][0]==0){
				c0 = true;
				break;
			}
		}
		
		//2.store 0 into the first row and first col\
		for(int i=1; i<row; i++){
			for(int j=1; j<col; j++){
				if(matrix[i][j]==0){
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		//3.traverse 0 row and 0 col and set  0
		for(int i=1; i<col; i++){
			if(matrix[0][i]==0){
				for(int j=0;j<row;j++){
					matrix[j][i] = 0;
				}
			}
		}
		for(int i=1; i<row; i++){
			if(matrix[i][0]==0){
				for(int j=0; j<col; j++){
					matrix[i][j] = 0;
				}
			}
		}
		
		//4.the orgin 0 row and 0 col
		if(r0){
			for(int i=0;i<col;i++){
				matrix[0][i] = 0;
			}
		}
		if(c0){
			for(int i=0;i<row;i++){
				matrix[i][0] = 0;
			}
		}
		return;
    }
	
	
	
	
	/*
	 * climbing stairs
	 * You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	斐波那契数列：f(n)=f(n-1)+f(n-2)
	 */
	public int climbStairs(int n) {
        int pre = 0;
        int cur = 1;
        for(int i=1; i<=n; i++){
        	int tmp = cur;
        	cur = pre + cur;
        	pre = tmp;
        }
        return cur;
    }
	
	
	
	
	/*
	 * plus one
	 * Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
题目：
给定一个十进制数，用数组表示每一位，要求返回加一后的结果
思路：
从数组尾部到头部处理，用一个overflow flag来判断是否溢出，如果溢出则需要新开一个array
	 */
	public int[] plusOne(int[] digits) {
        int len = digits.length;
        int overflow = 0;
        int i = len-1;
        while(i>=0){
        	if(digits[i]+1>9){
        		digits[i] = 0;
        		overflow = 1;
        		i--;
        	}else {
				digits[i]++;
				return digits;
			}
        }
        
        //当前位数不够
        if(overflow > 0){
        	int[] newDigits = new int[len+1];
        	System.arraycopy(digits, 0, newDigits, 1, len);
        	newDigits[0] = 1;
        	//newDigits[1] = 0;
        	return newDigits;
        }
        return digits;
    }
	
	
	
	
	/*
	 * Remove Element
	 * Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	 */
	public int removeElement(int[] A, int elem) {
        int len = A.length;
        int index = 0;
        for(int i=0; i<len; i++){
        	if(A[i]!=elem){
        		A[index] = A[i];
        		index++;
        	}
        }
        return index;
    }
	/*
	 * Maximum Subarray 
	 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

	For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
	the contiguous subarray [4,−1,2,1] has the largest sum = 6.

	动态规划：如果A[i]前边的大于0，则加上，否则，取A[i]

	 */
	public int maxSubArray(int[] A) {
		int len = A.length;
		if(len<=0){
			return 0;
		}
		int maxSub = A[0];
		int t = A[0];
		for(int i =1; i<len; i++){	
			t = Math.max(A[i], A[i]+t);
			maxSub = Math.max(maxSub, t);			
		}
		return maxSub;
			
	}
	
	
	
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
				
				//特别注意这个地方，不能是前边的if语句的else分支！！！！
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
	/*
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 */
	public int threeSumClosest(int[] num, int target) {
        int len = num.length;
        if(len<3){
        	return 0;
        }
        
        int result = 0;
        int min_gap = Integer.MAX_VALUE;
        bubble_sort(num);
        for(int i=0; i<len-2; i++){
        	int j = i+1;
        	int k = len-1;
        	while(j<k){
        		int sum = num[i]+num[j]+num[k];
        		int gap = Math.abs(target-sum);
        		if(gap < min_gap){
        			result = sum;
        			min_gap = gap;
        		}
        		if(sum<target){
        			j++;
        		}else {
					k--;
				}
        	}
        }
        return result;
    }
	
	/*
	 * Permutation Sequence排列
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

1."123"
2."132"
3."213"
4."231"
5."312"
6."321"
Given n and k, return the kth permutation sequence.
	 */
	public String getPermutation(int n, int k) {
        int[] nf = new int[n];
        nf[0] = 1;
        //n阶乘
        for(int i=1; i<n; i++){
        	nf[i]=nf[i-1]*(i+1);
        }
        char[] result = new char[n];
        for(int i=0; i<n; i++){
        	result[i] = (char)(i+'1');
        }
        k--;
        for(int i=0; i<n; i++){
        	int m = k%nf[n-i-1];
        	int nk = k/nf[n-i-1];
        	if(m==0 && nk==0){
        		return new String(result);
        	}else {
				if(nk>0){
					for(int j=i-1+nk; j>i-1; j--){
						char tmp = result[j];
						result[j] = result[j-1];
						result[j-1] = tmp;
					}
					if(m==0){
						return new String(result);
					}
				}
				k = m;
			}
        }
        return new String(result);
    }
	
	/*顺时针旋转照片90°
	 * You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).
Follow up:
Could you do this in-place?
	 * 首先想到，纯模拟，从外到内一圈一圈的转，但这个方法太慢。
	 * 好方法：首先沿着副对角线翻转一次，然后沿着水平中线翻转一次。
	 * 或者，首先沿着水平中线翻转一次，然后沿着主对角线翻转一次。
	 */
	public void rotate(int[][] matrix) {
        int n = matrix.length;
        //int m = matrix[0].length;
        
        //首先沿着副对角线翻转一次
        for(int i=0; i<n; i++){
        	for(int j=0; j<n-i; j++){
        		int tmp = matrix[i][j];
        		matrix[i][j] = matrix[n-1-j][n-1-i];
        		matrix[n-1-j][n-1-i] = tmp;
        	}
        }
        
        //沿着中线翻转一次
        for(int i=0; i<n/2; i++){
        	for(int j=0; j<n; j++){
        		int tmp = matrix[i][j];
        		matrix[i][j] = matrix[n-1-i][j];
        		matrix[n-1-i][j] = tmp;
        	}
        }
    }
	
	
}
