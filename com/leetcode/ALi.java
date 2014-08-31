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
	public static void main(String[] args){
		TreeNode root = new TreeNode(3);
		TreeNode a = new TreeNode(15);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(1);
		TreeNode d = new TreeNode(3);
		TreeNode e = new TreeNode(15);
		
		root.left = a;
		root.right = b;
		a.left = c;
		c.left = d;
		b.right = e;
		System.out.println( "max"+maxMinus(root) );
	}
}
