package com.leetcode;

import java.util.LinkedList;
import java.util.Stack;


public class StackOperation {

	/*
	 * Largest Rectangle in Histogram 
	 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
分析
简单的，类似于 Container With Most Water(§12.6)，对每个柱子，左右扩展，直到碰到比自己矮
的，计算这个矩形的面积，用一个变量记录最大的面积，复杂度 O(n2)，会超时。
如图 §4-2所示，从左到右处理直方，当 i = 4 时，小于当前栈顶（即直方 3），对于直方 3，无
论后面还是前面的直方，都不可能得到比目前栈顶元素更高的高度了，处理掉直方 3（计算从直方 3
到直方 4 之间的矩形的面积，然后从栈里弹出）；对于直方 2 也是如此；直到碰到比直方 4 更矮的直
方 1。
这就意味着，可以维护一个递增的栈，每次比较栈顶与当前元素。如果当前元素大于等于栈顶元素，
则入栈，否则合并现有栈，直至栈顶元素小于当前元素。结尾时入栈元素 0，重复合并一次
	 */
	public int largestRectangleArea (int[] height){
		LinkedList<Integer> st = new LinkedList<Integer>();
		int[] hplus = new int[height.length+1];
		for(int i=0; i<height.length;i++){
			hplus[i] = height[i];					
		}
		hplus[height.length] = 0;
		int result = 0,i =0;
		while(i<hplus.length){
			if(st.isEmpty() || hplus[i]>=hplus[st.peek()]){
				st.push(i);
				i++;
			}else {
				int tmp = st.pop();
				result = Math.max(result, hplus[tmp]*(st.isEmpty()?i:(i-st.peek()-1)));
			}
		}
		
		return result;		
	}
	
	/*
	 * Valid Parentheses 
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
	 */
	public boolean isValid(String s){
		LinkedList<Character> st = new LinkedList<Character>();
		int len = s.length();
		for(int i=0; i<len; i++){
			Character tmp = s.charAt(i);
			if(st.isEmpty() || tmp.equals('(')||tmp.equals('{') || tmp.equals('[')){
				st.push(tmp);
			}else {
				if(tmp.equals(')') && st.peek().equals('(') ||
						tmp.equals('}') && st.peek().equals('{') ||
						tmp.equals(']') && st.peek().equals('[')){
					st.pop();
				}else {
					return false;
				}
			}
		}
		return st.isEmpty();
		
	}
	
	/*
	 * Longest Valid Parentheses
	 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
	 * 
	 */
	
	public int longestValidParentheses(String s) {
		LinkedList<Integer> st = new LinkedList<Integer>();
		int len = s.length();
		if(len==0){
			return 0;
		}
		int result = 0;
		//找到最长的起始位置
		int last = -1;
		for(int i=0; i<len; i++){
			Character tmp = s.charAt(i);
			if(tmp.equals('(')){
				st.push(i);
			}else {
				if(st.isEmpty()){
					last = i;
				}else {
					st.pop();
					if(st.isEmpty()){
						result = Math.max(result, i-last);
					}else {
						result = Math.max(result, i-st.peek());
					}
				}
			}
		}		
		return result;
    }
	
}
