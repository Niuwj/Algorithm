package com.leetcode;

public class DynaimicP {

	/*
	 * Triangle
	 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
        
    }
	
	
	
	/*
	 * Minimum Path Sum
	 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
	 *Note: You can only move either down or right at any point in time.
	 * 设状态为 f[i][j] ，表示从起点 (0 , 0) 到达 ( i, j ) 的最小路径和，则状态转移方程为：
f[i][j]=min(f[i-1][j], f[i][j-1])+grid[i][j]
	 */
	public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(m==0 || n==0){
        	return 0;
        }
        int[][] dm = new int[m][n];
        dm[0][0] = grid[0][0];
        for(int i=1;i<m;i++){
        	dm[i][0] = dm[i-1][0]+grid[i][0];
        }
        for(int i=1;i<n;i++){
        	dm[0][i] = dm[0][i-1]+grid[0][i];
        }
        for(int i=1;i<m;i++){
        	for(int j=1;j<n;j++){
        		dm[i][j] = Math.min(dm[i-1][j], dm[i][j-1]) + grid[i][j];
        	}
        }
        return dm[m-1][n-1];
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
	
	
	
	/*
	 * Interleaving String 
	 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

 设状态 f[i][j]，表示 s1[0,i] 和 s2[0,j]，匹配 s3[0, i+j]。如果 s1 的最后一个字符等
于 s3 的最后一个字符，则 f[i][j]=f[i-1][j]；如果 s2 的最后一个字符等于 s3 的最后一个字符，
则 f[i][j]=f[i][j-1]。因此状态转移方程如下：
f[i][j] = (s1[i - 1] == s3 [i + j - 1] && f[i - 1][j])
|| (s2[j - 1] == s3 [i + j - 1] && f[i][j - 1]);
	 */
	public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if(len1+len2>len3){
        	return false;
        }
        boolean[][] dm = new boolean[len1+1][len2+1];
        for(int i=0;i<=len1;i++){
            for(int j=0;j<=len2;j++){
                dm[i][j] = true;
            }
        }
        for(int i=1;i<=len1;i++){
        	dm[i][0] = dm[i-1][0] && (s1.charAt(i-1)==s3.charAt(i-1));
        }
        for(int j=1;j<len2;j++){
        	dm[0][j] = dm[0][j-1] && (s2.charAt(j-1)==s3.charAt(j-1));
        }
        
        for(int i=1;i<=len1;i++){
        	for(int j=1;j<len2;j++){
        		dm[i][j] = (s1.charAt(i-1)==s3.charAt(i+j-1) && dm[i-1][j]) || (s2.charAt(j-1)==s3.charAt(i+j-1)&&dm[i][j-1]);
        	}
        }
        return dm[len1][len2];
    }
	
	
	
}
