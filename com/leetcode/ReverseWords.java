package com.leetcode;

/**
 * Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
 * @author nwj
 *
 */




public class ReverseWords {
	 public String reverseWords(String s) {
	     StringBuffer result = new StringBuffer("");
	     if(s.length() == 0){
	    	 return new String("");
	     }
	     //从后往前遍历
	     for(int i = s.length()-1;i>=0;i--){
	    	//1.忽略空格
	    	while(i>=0 && s.charAt(i)==' '){
	    		i--;
	    	}
	    	//2.判断是否到首部
	    	if(i<0){
	    		break;
	    	}
	    	StringBuffer str = new StringBuffer();
	    	//3.找最后一个单词，把单词添加到str，此时读到的是blue的逆序，eulb
	    	while(i>=0 && s.charAt(i)!=' '){
	    		str.append(s.charAt(i));
	    		i--;
	    	}
	    	//4.逆序str，并添加空格
	    	str.reverse();
	    	str = str.append(" ");
	    	//5.将单词添加到result
	    	result.append(str);	    	
	     }
	     if(result.length() == 0){
	    	 return new String("");
	     }
	     //6.删除最后一个单词的空格
	     return new String(result.deleteCharAt(result.length()-1));
	 }
	 
	 
	 public static void main(String[] args){
		 ReverseWords m = new ReverseWords();
//		 System.out.println(m.reverseWords(""));
//			System.out.println(m.reverseWords(" "));
			System.out.println(m.reverseWords2("  .          /  "));
//			System.out.println(m.reverseWords("a"));
//			System.out.println(m.reverseWords(" a"));
//			System.out.println(m.reverseWords("  a"));
//			System.out.println(m.reverseWords("a "));
//			System.out.println(m.reverseWords("a  "));
//			System.out.println(m.reverseWords(" a "));
//			System.out.println(m.reverseWords(" a  "));
			System.out.println(m.reverseWords2("a     		 "));
//			System.out.println(m.reverseWords("  a  "));
//			System.out.println(m.reverseWords("  aa   b  "));
			System.out.println(m.reverseWords2(" 	 aa   bbb  cccc d	 "));
		 	
			
			//空字符串split之后是长度为1的数组，而空格字符串split之后是长度为0的数组
			String string = new String("");
			String[] s = string.split("\\s");
			int i=0;
			System.out.println(s.length);
			while(i<s.length){
				System.out.println(s[i]);
				i++;
			}
			//不支持其他标点符号
			//System.out.println(m.reverseWords("i am a leader."));
	 }
	 
	 //正则表达式方法
	 public String reverseWords2(String s) {
		 if(s.length() == 0){
	    	 return new String("");
	     }
		 //匹配所有的空白字符串
			String[] ss = s.split("\\s+");
			System.out.println(ss.length);
		//判断是否为空格字符串（空格字符串split之后是长度为0的数组）
			if(ss.length == 0)
				return "";
		//添加最后一个word，这样可以省掉删除空格的操作
			StringBuilder sb = new StringBuilder();
			sb.append(ss[ss.length - 1]);
			//遍历数组，如果数组元素内容不为空，则添加  空格+word
			for (int i = ss.length - 2; i >= 0; --i) {
				if(!ss[i].isEmpty()) {
					sb.append(" ").append(ss[i]);
				}
			}
			return sb.toString();
		}
	 
}
