package com.leetcode;

public class TreeLinkNode {
	int val;
	TreeLinkNode left;
	TreeLinkNode right;
	TreeLinkNode next;
	public TreeLinkNode(int val){
		this(val, null, null, null);
	}
	
	public TreeLinkNode(int val ,TreeLinkNode left, TreeLinkNode right, TreeLinkNode next){
		this.val = val;
		this.left = left;
		this.right = right;
		this.next = next;
	}	
}
