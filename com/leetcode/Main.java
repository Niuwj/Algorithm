package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode result;
//		ListNode l1 = new ListNode(2);
//		ListNode x2 = new ListNode(3);
//		ListNode x3 = new ListNode(4);
//		l1.next = x2;
//		//x2.next = l1;
//		//x3.next = x2;
//		
		ListNode l2 = new ListNode(1);
		ListNode y2 = new ListNode(2);
		ListNode y3 = new ListNode(3);
		ListNode y4 = new ListNode(4);
		ListNode y5 = new ListNode(5);
		l2.next = y2;
		y2.next =y3;
		y3.next = y4;
//		y4.next = y5;

//		//result = AddTwoNumbers.addTwoNumbers(l1,l2);
//		MergeTwoSortedList mts = new MergeTwoSortedList();
//		//result = = mts.mergeTwoLists(l1, l2);
//		//result.print();
//		LinkedListCircle llc = new LinkedListCircle();
//		boolean hc = llc.hasCycle(l1);		
//		System.out.println(hc);
//		ListNode p = llc.detectCycle(l1);
//		System.out.println(p.val);
//		System.out.println(llc.detectC(l1).val);
		ListOpetation lo = new ListOpetation();
//		ListNode result = lo.insertionSortList(l2);
//		result.print();
//		result = lo.partition(l2, 6);
		l2.print();
//		result = lo.reverseList(l2);
//		result = lo.reverseBetween(l2, 2, 5);
//		result.print();
//		ReverseInt rInt = new ReverseInt();
//		System.out.println(rInt.reverse(1000034));
		lo.reorderList(l2);
		l2.print();
		
//		LRUCache lru = new LRUCache(1);
//		lru.set(2, 1);
//		System.out.println(lru.get(2));
//		lru.set(3, 2);
//		System.out.println(lru.get(2));
//		System.out.println(lru.get(3));
		String s = new String("21");
		int x = Integer.parseInt(s);
		int i = -1;
		
		System.out.println(~0);
		
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
}
