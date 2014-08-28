package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
		
		//dp[i][j]=dp[i][k]&&dp[k+1][j]
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
		
		boolean flag = false;
		for(int i=0; i<size;i++){
			if(dp[i][size-1]){
				flag = true;
				break;
			}
		}
		List<String> res = new LinkedList<String>();
		if(flag == false){
			return res;
		}
		LinkedList<Integer> sp = new LinkedList<>();
		dfs(s, 0, sp, res, dict, dp);
		return res;
		
    }
	
	public void dfs(String s,int start, LinkedList<Integer> sp, List<String> res,Set<String> dict,boolean[][] dp){
		if(start>=s.length()){
			StringBuffer stbf = new StringBuffer(s);
			for(int i=0; i<sp.size()-1;i++){
				stbf.insert(sp.get(i)+i," ");
				System.out.println("3 "+stbf);
			}
			res.add(stbf.toString());
		}else {
			for(int j=0;j<dp[start].length;j++){
				if(dp[start][j]){
					//找到单词的下一个坐标
					System.out.println("1 "+start+"  "+j+" "+dp[start][j]);
					sp.add(j+1);
					System.out.println("2 "+sp);
					dfs(s, j+1, sp, res, dict, dp);	 				
					System.out.println("4 "+sp);
					sp.removeLast();//注意这一定是删除最后一个元素！！！
				}
				
			}
		}
	}
	
	public static void main(String[] args){
		WordBreak wBreak = new WordBreak();
		Set<String> dict = new HashSet<>();
		dict.add("aaaa");
		dict.add("aaa");
//		dict.add("b");
//		dict.add("cd");
		String s = "aaaaaaa";
		System.out.println(wBreak.wordBreak2(s, dict));
	}
}
