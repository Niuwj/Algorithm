package com.leetcode;

public class DynaimicP {
	
	/*
	 * Minimum Path Sum
	 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
	 */
	
	
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
