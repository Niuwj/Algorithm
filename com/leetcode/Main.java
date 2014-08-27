package com.leetcode;

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
	}
}
