package com.leetcode;
/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 * @author nwj
 *
 */


public class LongestPalindromic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string = "abbbbbbbbacdcbaaaaa";
		LongestPalindromic lp = new LongestPalindromic();
		System.out.println(lp.longestPalindrome(string));

	}
	
	//动态规划方法
	/*
	 * 思路1. 动态规划
	这里动态规划的思路是 dp[i][j] 表示的是 从i 到 j 的字串，是否是回文串。
	则根据回文的规则我们可以知道：
	如果s[i] == s[j] 那么是否是回文决定于 dp[i+1][ j - 1]
	当 s[i] != s[j] 的时候， dp[i][j] 直接就是 false。
	动态规划的进行是按照字符串的长度从1 到 n推进的。
	代码很明晰：给出java代码，时间复杂度和空间复杂度都是 O(n^2)
	 * 
	 */
	
	boolean[][] dp;
	public String longestPalindrome(String s) {
		if(s.length() == 0){
			return "";
		}
		if(s.length() == 1){
			return s;
		}
		
		//初始化dp
		dp = new boolean[s.length()][s.length()];
		
		int i , j;
		//初始化dp[][]
		for( i =0; i<s.length(); i++){
			for( j=0; j<s.length(); j++){				
				//i = j ，只有一个字符的字符串，是回文
				//i > j ，认为是空串，也是回文
				if( i >= j){
					dp[i][j] = true;
				}else {
					dp[i][j] = false;
				}
			}
		}
			
		//i<j时，判断dp[i][j]是否是回文	
		int k;//字符下标差 j-i，如长度为6的字符串，最长子串是s[0...6],k最大等于6，子串长度为k+1
		int maxlen = 1; //回文最大长度
		int rf = 0, rt = 0; //最长回文子串下标位置
		//从下标差为1的子串到下标差为s.length()-1的子串
		for( k=1 ; k < s.length() ; k++){
			for(i = 0; k+i <s.length(); i++){
				j = i+k;
				//对字符串 s[i...j]
				//如果s[i]!=s[j]，那么不是回文
				if(s.charAt(i) != s.charAt(j)){
					dp[i][j] = false;
				}else {
					//如果s[i]==s[j],那么判断dp[i+1][j-1]
					dp[i][j] = dp[i+1][j-1];
					if(dp[i][j]) {
						if( k+1 > maxlen){
							maxlen = k+1;
							rf = i;
							rt = j;
						}//endif
					}//endif
				}//endif
			}//endfor
		}//endfor
		return s.substring(rf, rt+1);
    }
	
	public String longestP2(String s ){
		
		return null;
	}
}
