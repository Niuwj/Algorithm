package com.leetcode;

import java.util.Set;

public class WordBreak {
	
	/*
	 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
	 */
	public boolean wordBreak(String s, Set<String> dict) {
        if(s.isEmpty()){
        	return false;
        }
		int size = s.length();
		boolean[][] dp = new boolean[size][size];
		
		//初始化dp
		for(int i=0; i<size; i++){
			for(int j=i;j<size;j++){
				if(dict.contains(s.substring(i, j+1))){
					dp[i][j] = true;
				}else {
					dp[i][j] = false;
				}
			}
		}
		
		//dp[i][j]=dp[i][k]&&dp[k][j]
		for(int i=0; i<size; i++){
			for(int j=i; j<size; j++){
				for(int k=i; k<j; k++){
					if(!dp[i][j]){
						dp[i][j] = dp[i][k] && dp[k+1][j];
					}
				}
			}
		}
		
		return dp[0][size-1];
    }
	/*
	 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
	 */
	public List<String> wordBreak2(String s, Set<String> dict) {
        List<String> result;
		
    }
}
