package com.leetcode;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		ListNode x2 = new ListNode(3);
		ListNode x3 = new ListNode(4);
		l1.next = x2;
		x2.next = l1;
		x3.next = x2;
		
		ListNode l2 = new ListNode(0);
		ListNode y2 = new ListNode(6);
		ListNode y3 = new ListNode(6);
		ListNode y4 = new ListNode(7);
		l2.next = y2;
		y2.next =y3;
		y3.next = y4;
		ListNode result = null;
		//result = AddTwoNumbers.addTwoNumbers(l1,l2);
		MergeTwoSortedList mts = new MergeTwoSortedList();
		//result = = mts.mergeTwoLists(l1, l2);
		//result.print();
		LinkedListCircle llc = new LinkedListCircle();
		boolean hc = llc.hasCycle(l1);		
		System.out.println(hc);
		ListNode p = llc.detectCycle(l1);
		System.out.println(p.val);
		System.out.println(llc.detectC(l1).val);
		
	}
}
