package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int[] num = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
//		int[] num = {-4,-6,-1,-2,-5,-4,2,4,0,2,1,1,1};
//		int[] num={0,0,0,0};
		ArrayOperation ao = new ArrayOperation();
		System.out.println(ao.countAndSay(10));
//		System.out.println(ao.getPermutation(4, 6));
		
//		ListNode result;
//		ListNode l1 = new ListNode(2);
//		ListNode x2 = new ListNode(3);
//		ListNode x3 = new ListNode(4);
//		l1.next = x2;
//		//x2.next = l1;
//		//x3.next = x2;
//		
//		ListNode l2 = new ListNode(1);
//		ListNode y2 = new ListNode(2);
//		ListNode y3 = new ListNode(3);
//		ListNode y4 = new ListNode(4);
//		ListNode y5 = new ListNode(5);
//		l2.next = y2;
//		y2.next =y3;
//		y3.next = y4;
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
//		ListOpetation lo = new ListOpetation();
//		ListNode result = lo.insertionSortList(l2);
//		result.print();
//		result = lo.partition(l2, 6);
//		l2.print();
//		result = lo.reverseList(l2);
//		result = lo.reverseBetween(l2, 2, 5);
//		result.print();
//		ReverseInt rInt = new ReverseInt();
//		System.out.println(rInt.reverse(1000034));
//		lo.reorderList(l2);
//		l2.print();
		
//		LRUCache lru = new LRUCache(1);
//		lru.set(2, 1);
//		System.out.println(lru.get(2));
//		lru.set(3, 2);
//		System.out.println(lru.get(2));
//		System.out.println(lru.get(3));
//		String s = new String("21");
//		int x = Integer.parseInt(s);
//		int i = -1;
//		
//		System.out.println(~0);
//		StringOperation sOperation = new StringOperation();
//		String[] strs = {"aa","a"};
//		System.out.println(sOperation.longestCommonPrefix(strs));
	
		TreeNode root = new TreeNode(1);
		TreeNode a = new TreeNode(2);
		TreeNode t = new TreeNode(1);
		TreeNode b = new TreeNode(3);
//		TreeNode c = new TreeNode(4);
//		TreeNode d = new TreeNode(5);
////		TreeNode e = new TreeNode(6);
//		TreeNode f = new TreeNode(7);
		root.left = a;
		a.left = t;
		root.right = b;
		
//		a.left = c;
//		a.right = d;
////		b.left = e;
//		b.right = f;
//		BTreeOperation bt = new BTreeOperation();
		
//		System.out.println(bt.pathSum(root, 4));
//		System.out.println(bt.zigzagLevelOrder(root));
//		Search search = new Search();
//		int[] A = {1,1,1};
//		int[] result = search.searchRange(A, 1);
//		System.out.println(result[0]+" "+result[1]);
//		StackOperation sOperation = new StackOperation();
//		System.out.println(sOperation.isValid("(([]){})"));
	}
}
