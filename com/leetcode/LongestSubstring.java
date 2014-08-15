package com.leetcode;

import java.util.HashMap;

/**
 * Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc" which the length is 3. 
 * For "bbbbb" the longest substring is "b", with the length of 1.
 * @author nwj
 *
 */

public class LongestSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = new String("abbab");
		LongestSubstring ls = new LongestSubstring();
		int max = ls.lengthOfLongestSubstring(s);
		System.out.println(max);
	}
	
	/*用两个指针，一个指向当前子串的头，一个指向尾，尾指针不断往后扫描
	 * 当有字符前面出现过了，记录当前子串长度和最优解的比较结果。
	 * 然后头指针不断往后扫描，直到扫描到一个字符和尾指针相同，则尾指针继续扫描，
	 * 当尾指针到达字符串结尾，算法结束。复杂度O(n) + O(n) = O(n)
	 * 
	 * */
	public int lengthOfLongestSubstring(String s) {
		 //记录最大子串长度
		 int max = 0;
		 //i记录遍历的子串起始位置，j记录结束位置
		 int start=0;
		 HashMap<Character, Integer> map = new HashMap<Character,Integer>();
		 
		 for(int i=0;i<s.length();i++){
			 char c = s.charAt(i);
			 //如果当前子串中有该字符，则清空
			 //设置开始指针为重复字符或者当前字符的的下一个
			 //更新哈希表中该字符的最新下标
			 if(map.containsKey(c)){
				 for(int j=start;j<i;j++){
					 if(s.charAt(j) == c){
						 break;
					 }
					 map.remove(s.charAt(j));
				 }
				 start = map.get(c)+1;
				 map.put(c, i);
			 }else {
				//如果当前子串不包括该字符
				//把字符及下标放到hashmap
				//更新最大子串的长度
				map.put(c, i);
				if(i-start+1 > max){
					max = i-start+1;
				}					
			}
		 }
		 return max;
	 }
}
