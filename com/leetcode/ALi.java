package com.leetcode;

import java.util.LinkedList;
import java.util.List;

public class ALi {

	/*
	 * 写一个函数，输入一个二叉树，树中每个节点存放了一个整数值，
	 * 函数返回这棵二叉树中相差最大的两个节点间的差值绝对值。请注意程序效率。
	 */
	
	public static int maxMinus(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();
		if(root==null){
        	return 0;
        }
		int max = root.val;
		int min = root.val;
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		
		st.push(root);
		while(!st.isEmpty()){
			TreeNode tmp = st.pop();
			if(max<tmp.val){
				max = tmp.val;
			}
			if(min>tmp.val){
				min = tmp.val;
			}
			result.add(tmp.val);
			if(tmp.right!=null){
				st.push(tmp.right);
			}
			if(tmp.left!=null){
				st.push(tmp.left);
			}			
		}
		return (max-min);		
    }
	
	/*
	 * 给定一个query和一个text，均由小写字母组成。
	 * 要求在text中找出以同样的顺序连续出现在query中的最长连续字母序列的长度
	 */
	public int getMaxCommonLen(String text, String query) {
	      String max = "";
	      for (int i = 0; i < text.length(); i++) {
	          for (int j = i; j < text.length(); j++) {
	              String sub = text.substring(i, j);
	              if ((query.indexOf(sub) != -1) && sub.length() > max.length()) {
	                  max = sub;
	              }
	          }
	      }
	      return max.length();
	 } 
	
	/*
	 * 动态规划求最长公共子序列
	 * 		  | 0			if i==0 or j==0
	 * a[i][j]| a[i-1][j-1]+1		if s1[i]==s2[j],i!=0&&j!=0
	 * 		  | max(a[i-1][j],a[i][j-1]		if s1[i]!=s2[j],i!=0&&j!=0
	 */
	public static int getLCS(String s1,String s2){
		int len1 = s1.length();
		int len2 = s2.length();
		int[][] a = new int[len1][len2];
		
		//最有子结构，递归方程
		for(int i=0; i<len1; i++){
			for(int j=0; j<len2; j++){
				Character c1 = s1.charAt(i);
				if(c1.equals(s2.charAt(j))){
					if(i==0||j==0){
						a[i][j] = 1; 
					}else {
						a[i][j] = a[i-1][j-1]+1;
					}					
				}else {
					if(i!=0 && j!=0){
						a[i][j] = Math.max(a[i-1][j], a[i][j-1]); 
					}
				}
			}
		}
		return a[len1-1][len2-1];
	}
	
	
	public static void main(String[] args){
//		TreeNode root = new TreeNode(1);
//		TreeNode a = new TreeNode(2);
//		TreeNode b = new TreeNode(3);
//		TreeNode c = new TreeNode(4);
//		TreeNode d = new TreeNode(5);
//		TreeNode e = new TreeNode(6);
//		TreeNode f = new TreeNode(7);
//		root.left = a;
//		root.right = b;
//		a.left = c;
//		a.right = d;
//		b.left = e;
//		b.right = f;
//		System.out.println( "max"+maxMinus(root) );
		String s1 = "abcba";
		String s2 = "abcabcd";
		System.out.println(getLCS(s1, s2));
		
	}
}
